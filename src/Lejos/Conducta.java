package Lejos;

import Herramientas.TablaSimbolos;

import java.util.*;
import java.lang.Integer;
import java.lang.Double;
public class Conducta extends Thread
{
	Agente agente;
	String directorio;
	int entrada;
	int idCond;
	int ii;
	ArchivoObjeto in;
	int init;
	int[] inn;
	String nombre;
	Oraculo oraculo;
	int tempTipoSensor = 0;
	Stack pilaNum;
	Stack pilaOper;
	Stack pilaOperL;
	Stack pilaValorL;
	TablaSimbolos tablaConductas;
	TablaSimbolos tablaEDGlobal;
	TablaSimbolos tablaEDLocal;
	TablaSimbolos tablaAcciones;
	Accion accion;

	//variables para generacion código Lejos
	String condicion ="";
	String condicion2 = "";
	String conducta = "";
	String conductas ="";
	String acciones = "";
	String variablesGlobales="";
	String variablesLocales="";
	boolean evaluaSensor = false;


 
	public Conducta(Agente agente, String nombre, String directorio, Oraculo oraculo, TablaSimbolos tablaEDGlobal, TablaSimbolos tablaConductas)
	{
		super(nombre);
     this.agente = agente;
     this.nombre = nombre;
     this.directorio = directorio;
     init = (entrada = 0);
     this.oraculo = oraculo;
     this.tablaEDGlobal = tablaEDGlobal;
     this.tablaConductas = tablaConductas;
     this.tablaAcciones = oraculo.tablaAcciones;
     tablaEDLocal = new TablaSimbolos();
     pilaNum = new Stack();
     pilaOper = new Stack();
     pilaOperL = new Stack();
     pilaValorL = new Stack();
     inn = new int[400];
     abrirArchivo();
     initTablaEDLocal();
     in.cierra_archivo();
     accion = new Accion();
     idCond = tablaConductas.getIndex(nombre);
     ii = 0;
	}
/*     */   
/*     */   private void abrirArchivo() {
/*  50 */     in = new ArchivoObjeto(directorio + nombre + ".Age2000.obj");
/*     */   }
/*     */   
/*     */   private void instrucciones() {
/*  64 */     while ((entrada != -1) && (ii < 200)) {
/*  65 */       switchInstrucciones();
/*  67 */       entrada = inn[(ii++)];
				if(entrada == 0)
					entrada = -1;
/*     */     }
/*     */   }
/*     */   
/*     */   private void switchInstrucciones() {
/*     */     int caracter;
/*     */     int numInstr;
/*     */     boolean eval;
			System.out.println("entrada dentro de switchInstrucciones: "+entrada);
/*  75 */     switch (entrada) {

/*  76 */     case 42:  // * solicita acción
				int id = inn[(ii++)];
				String nombreAccion = "";
//				//Buscar la acción para inicializar tanto el método, así como la llamada a ésta.
				int temp;
				for(int i = 1; i <= tablaAcciones.count(); i++)
				{
						temp = tablaAcciones.getIdIndice(i);
						System.out.println("Temp: "+temp);
						System.out.println("Id: "+id);
						//System.out.println(tablaAcciones.getUso(i));
						if(temp == id )
							nombreAccion = tablaAcciones.getNombre(i);
				}
				
					condicion += "\t\t"+nombreAccion+"(); \n";
			break;
			
		case 61:  // = es una asignación
					//if (((goto 1024)) && (inn[(ii++)] == 36)) { //$
			if ( (inn[(ii++)] == 36)) { //$ es una variable
	/*  82 */         int idVar = inn[(ii++)];
					  
	/*  83 */         double resultado = operacion();
	/*  84 */         if (tablaEDLocal.buscar_Id(idVar)) {
	/*  85 */           tablaEDLocal.actualiza_Id_valor2(idVar, resultado);
						
						variablesLocales+=resultado+"\n";
	/*     */ 
	/*     */         }
	/*  88 */         else if (tablaEDGlobal.buscar_Id(idVar)) {
	/*  89 */           tablaEDGlobal.actualiza_Id_valor2(idVar, resultado);
						variablesGlobales+=resultado+"\n";
	/*     */         }
/*     */     }
			break;

			/********condición repite********/
			case 22: //repite
				condicion += "for( ";
/*  94 */       caracter = inn[(ii++)];
/*  95 */       double ciclo = 0.0D;
/*  96 */       numInstr = 0;
/*  97 */       if (caracter == 91) { //[
/*  98 */         ciclo = operacion();
System.out.println("OPERACIONCICLO22: "+ciclo);
/*     */       }
				condicion += "int i = 0; i < "+ciclo+"; i++){\n";
/* 100 */       if (inn[(ii++)] == 123) {//{
/* 101 */         numInstr = inn[(ii++)];
/* 102 */         if (ciclo > 0.0D) {
					
/* 103 */           //for (int i = 0; i < ciclo; i++) {
/*     */             do {
/* 105 */               entrada = inn[(ii++)];
/* 106 */               switchInstrucciones();
/* 107 */             } while (entrada != 125);//}
					  //cierra el for
						condicion += "\n\t}";
/* 108 */             //if (i + 1 < ciclo) { 
						//	ii -= numInstr;//recalcular el ii para realizar otro ciclo
/*     */             //}
/*     */           //}
/*     */         } else {
/* 112 */           for (int y = 0; y < numInstr; y++)
/* 113 */             ii += 1;
/*     */         }
/*     */       }
/* 116 */       break;


			/********condición si********/
/*     */     case 23: //si
				condicion += "if( ";
/* 118 */       caracter = inn[(ii++)];
/* 119 */       numInstr = 0;
/* 120 */       eval = true;
				String condElse = "";
		       if (caracter == 91) { //91  -> [ //llave para la condicional(....&&.  ||..)
		         eval = EvalCondicion();
		       }
		       
		       //abre llave para acciones
		       if (inn[(ii++)] == 123) { //123  -> {
				 condicion += "{\n";
		         numInstr = inn[(ii++)];
		           entrada = inn[(ii++)];
				   System.out.println("ENTRada dentro de llave acciones "+entrada);
		           do {
		             switchInstrucciones();
		             entrada = inn[(ii++)];
		           } while (entrada != 125); // 125 -> }
		           condicion +="\t\t} ";
		           //caso de else
		           if (inn[(ii++)] == 24) {//se encuentra un else(otro) para evaluarlo
		        	   //condElse += "else {\n";
		        	   condicion+="else {\n";
		             if (inn[(ii++)] == 123) {
		               numInstr = inn[(ii++)];
		               entrada = inn[(ii++)];
						System.out.println("ENTRada dentro de llave acciones "+entrada);
		               do {
		                 switchInstrucciones();
		                 entrada = inn[(ii++)];
		               } while (entrada != 125);
		               //condElse += "\t} ";
		               condicion += "\t\t}";
		             }
		           }
		           else 
		        	   ii -= 1; //regresa el valor de ii antes de verificar si existe else
						
		       }
		       //condicion += "\t"+condElse; //agregar el else por si existe a la condición al final de la llave del if
		       //System.out.println(condicion);
/* 162 */       break;
/*     */     
/*     */     case 21: //mientras
/* 165 */       caracter = inn[(ii++)];
/* 166 */       numInstr = 0;
/* 167 */       eval = true;
				condicion+= "while( ";
/* 168 */       if (caracter == 91) {//[
/* 169 */         eval = EvalCondicion();
/*     */       }//]
/* 171 */       if (inn[(ii++)] == 123) {//{
					condicion +="{\n";
/* 172 */         numInstr = inn[(ii++)];
/* 173 */         //if (eval) {
/* 174 */           entrada = inn[(ii++)];
/*     */           do {
					System.out.println("Se queda ciclado acá???");
/* 176 */             switchInstrucciones();
/* 177 */             entrada = inn[(ii++)];
/* 178 */           } while (entrada != 125);
System.out.println("Se queda ciclado acá???");

/* 179 */           int num = inn[(ii++)];
System.out.println("num"+num);

/* 180 */           //ii = (ii - num - 1);
/*     */         //}
/*     */         //else {
/* 183 */           //for (int y = 0; y < numInstr + 1; y++) {
/* 184 */            // ii += 1;
/*     */          // }
/*     */         //}
				condicion += "\n}";
/*     */       }
/*     */       break;
/*     */     }
/*     */     
/*     */   }


public  String enviarAcciones(){
	 return accion.getAccionRobot();
}

public String getCondicion(){
	System.out.println("CONDICION((((((((((((((("+condicion);
	if(tempTipoSensor == 5)
		condicion+="\n\t\tLCD.drawInt(sensorLuz.getLightValue(),0,6);";
	return condicion;
}

/*     */   public void run()
/*     */   {
/*     */     //for (;;)
/*     */     //{
/*  57 */       ii = 0;
/*  58 */       entrada = inn[(ii++)];
				
/*  59 */       instrucciones();
/*     */     //}
/*     */   }
/*     */   
/*     */   private void condicion()
/*     */   {
/* 195 */     double x = operacion();
/* 196 */     double y; 
System.out.println("ENTRADA DENTRO DE CONDICIÓN: "+entrada);
			switch (entrada) {
/*     */     case 6: 
				
/* 198 */       y = operacion();
				if(evaluaSensor)
				{
					if(y >= 1)
					condicion += "== true";
					else
						condicion += "== false";
				}
				else
				condicion += "< "+(int)y;
				evaluaSensor = false;
/* 199 */       if (x < y) pilaValorL.push(new Boolean(true)); else
/* 200 */         pilaValorL.push(new Boolean(false));
/* 201 */       break;
/*     */     case 7:
				
				y = operacion();
				if(evaluaSensor)
				{
					if(y >= 1)
					condicion += "== true";
					else
						condicion += "== false";
				}
				else
					condicion += "> "+(int)y;
				evaluaSensor = false;
/* 204 */       if (x > y) pilaValorL.push(new Boolean(true)); else
/* 205 */         pilaValorL.push(new Boolean(false));
/* 206 */       break;
/*     */     case 8: 
				y = operacion();
				if(evaluaSensor)
				{
					if(y >= 1)
					condicion += "== true";
					else
						condicion += "== false";
				}
				else
					condicion += "<= "+(int)y;
				evaluaSensor = false;
/* 209 */       if (x <= y) pilaValorL.push(new Boolean(true)); else
/* 210 */         pilaValorL.push(new Boolean(false));
/* 211 */       break;
/*     */     case 9:
				y = operacion();
				if(evaluaSensor)
				{
					if(y >= 1)
					condicion += "== true";
					else
						condicion += "== false";
				}
				else
					condicion += ">= "+(int)y;
				evaluaSensor = false;
/* 214 */       if (x >= y) pilaValorL.push(new Boolean(true)); else
/* 215 */         pilaValorL.push(new Boolean(false));
/* 216 */       break;
/*     */     case 11: 
				y = operacion();
				if(evaluaSensor)
				{
					if(y >= 1)
					condicion += "== true";
					else
						condicion += "== false";
				}
				else
					condicion += "== "+(int)y;
				evaluaSensor = false;
/* 219 */       if (x == y) pilaValorL.push(new Boolean(true)); else
/* 220 */         pilaValorL.push(new Boolean(false));
/* 221 */       break;
/*     */     case 12: 
				condicion += "!= ";
/* 223 */       y = operacion();
/* 224 */       if (x != y) pilaValorL.push(new Boolean(true)); else
/* 225 */         pilaValorL.push(new Boolean(false));
/* 226 */       break;
/* 227 */     case 10: default:  pilaValorL.push(new Boolean(true));
/*     */     }
			System.out.println("************: "+condicion);
/*     */   }
/*     */   
/*     */   private boolean EvalCondicion()
/*     */   {
/* 233 */     Stack pilaTemp = new Stack();
/* 234 */     boolean aux = false;
			boolean band = false;
/* 235 */     condicion();
/*     */     do
/*     */     {
				System.out.println("Entrada dentro de evalcondicionDO: "+entrada);
/* 238 */       switch (entrada) {
/* 239 */       case 14:  pilaOperL.push(new Boolean(true)); //caso de and &
/* 240 */         band = true;
					condicion += "&& ";
/* 241 */         break;
/* 242 */       case 15:  pilaOperL.push(new Boolean(false)); //caso de or |
/* 243 */         band = true;
					condicion += "| ";
/*     */       }
/*     */       
/* 246 */       if (band) 
					condicion();
/* 247 */       band = false;
/* 248 */     } while (entrada != 93);
/*     */     condicion += ")";
				System.out.println("Condiccion cierra if: "+condicion);
/* 250 */     while (!pilaOperL.isEmpty()) {//verifica si se cumple la condicion(compara en las pilas)
/* 251 */       aux = popStackBoolean(pilaOperL);
/* 252 */       if (aux) {
/* 253 */         if ((popStackBoolean(pilaValorL) & popStackBoolean(pilaValorL))) pilaValorL.push(new Boolean(true)); else {
/* 254 */           pilaValorL.push(new Boolean(false));
/*     */         }
/*     */       } else {
/* 257 */         pilaTemp.push(new Boolean(popStackBoolean(pilaValorL)));
/*     */       }
/*     */     }
/* 260 */     pilaTemp.push(new Boolean(popStackBoolean(pilaValorL)));
/*     */     do {
/* 262 */       aux = popStackBoolean(pilaTemp);
/* 263 */     } while (((!aux ? 1 : 0) & (!pilaTemp.isEmpty() ? 1 : 0)) != 0);
/* 264 */     return aux;
/*     */   }
/*     */   
/*     */   private void initTablaEDLocal()
/*     */   {
/* 269 */     String variable = "";
/* 270 */     int id = 0;int tipo = 0;
/* 271 */     double valor = 0.0D;
/* 272 */     int caracter = in.getCodigo();
/* 273 */     if (caracter == 64) {
/* 274 */       caracter = in.getCodigo();
/*     */       do {
/* 276 */         if (caracter == 36) {
/* 277 */           variable = in.getVariable();
/* 278 */           id = in.getCodigo();
/* 279 */           tipo = in.getCodigo();
/* 280 */           valor = in.getNumero();
/* 281 */           tablaEDLocal.insertar(variable, tipo, 0, valor, id);
/*     */         }
/* 283 */         caracter = in.getCodigo();
/* 284 */       } while (((caracter != -1 ? 1 : 0) & (caracter != 64 ? 1 : 0)) != 0);
/*     */       
/* 286 */       in.getCodigo();
/*     */     }
/* 288 */     init = in.getIndice();
/* 289 */     entrada = caracter;
/* 290 */     int i = 0;
/* 291 */     inn[0] = caracter;
/*     */     do {
/* 293 */       caracter = in.getCodigo();
/* 294 */       inn[(++i)] = caracter;
/* 295 */     } while (caracter != -1);
/*     */   }
/*     */   
/*     */   private double operacion()
/*     */   {
/* 314 */     boolean band = false;
/* 315 */     int caracter = 0;
/* 316 */     int i = 0;
			//evaluaSensor = false;
/* 317 */     caracter = inn[(ii++)];
System.out.println("CARACTER DENTRO DE OPERACIÓN: "+caracter);
/*     */     do {
/* 319 */       switch (caracter)
/*     */       {
/*     */ 
/*     */       case 35: 
/* 323 */         pilaNum.push(new Double(getNumeroOp()));
/* 324 */         if (band == true) {
/* 325 */           reducirMulDiv();
/* 326 */           band = false; }
/* 327 */         break;
/*     */       case 36: 
	System.out.println("VARIABLE");
/* 329 */         double y = getDoubleVar();
System.out.println("var "+y);
/* 330 */         pilaNum.push(new Double(y));
/* 331 */         if (band == true) {
/* 332 */           reducirMulDiv();
/* 333 */           band = false; }
/* 334 */         break;
/*     */       
/*     */       case 38: //caso de verificar sensor
/* 337 */         double yy = getSensor();
					
/* 338 */         pilaNum.push(new Double(yy));
/* 339 */         break;
/*     */       case 16: 
/*     */       case 17: 
/* 342 */         pilaOper.push(new Integer(caracter));
/* 343 */         break;
/*     */       case 18: case 19: 
/* 345 */         pilaOper.push(new Integer(caracter));
/* 346 */         band = true;
/* 347 */         break;
/*     */       case 40: 
/* 349 */         pilaOper.push(new Integer(caracter));
/* 350 */         band = false;
/* 351 */         break;
/*     */       case 41: 
/* 353 */         reducir();
/*     */       }
/*     */       
/* 356 */       caracter = inn[(ii++)];
//System.out.println("caracter DENTRO DE OPERACION y while: "+caracter);
/* 357 */     } while (!isDelimit(caracter));
/* 358 */     entrada = caracter;
//System.out.println("entrada DENTRO DE OPERACION: "+entrada);
/* 359 */     return resultado();
/*     */   }
/*     */   
/*     */   private double resultado() {
/* 363 */     int operd = 0;
/* 364 */     double valor = 0.0,temp;
	System.out.println("Hay operadores en la pila??? "+pilaOper.empty());
/* 365 */     if (!pilaOper.empty()) {
/*     */       do {
/* 367 */         Integer operador = (Integer)pilaOper.pop();
/* 368 */         operd = operador.intValue();
System.out.println("OPerador en RESultado : "+operd);
/* 369 */         switch (operd) {
/*     */         case 16: 
/* 371 */           temp = popStackNum();
/* 372 */           valor = temp + popStackNum();
/* 373 */           pilaNum.push(new Double(valor));
/* 374 */           break;
/*     */         case 17: 
/* 376 */           temp = popStackNum();
/* 377 */           valor = popStackNum() - temp;
/* 378 */           pilaNum.push(new Double(valor));
/* 379 */           break;
/*     */         case 18: 
/* 381 */           temp = popStackNum();
/* 382 */           valor = temp * popStackNum();
/* 383 */           pilaNum.push(new Double(valor));
/* 384 */           break;
/*     */         case 19: 
/* 386 */           temp = popStackNum();
/* 387 */           valor = popStackNum() / temp;
/* 388 */           pilaNum.push(new Double(valor));
/*     */         }
/*     */         
/*     */         
/* 392 */         temp = valor = 0.0;
/* 393 */       } while (!pilaOper.empty());
/*     */     }
/* 395 */     return popStackNum();
/*     */   }
/*     */   
/*     */   private double popStackNum() {
/* 399 */     double temp = 0.0D;
/*     */     try {
/* 401 */       Double doble = (Double)pilaNum.pop();
/* 402 */       temp = doble.doubleValue();
/*     */     } catch (java.util.EmptyStackException e) {}
System.out.println("TEMP en popstackNum : "+temp);
/* 404 */     return temp;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getNumeroOp()
/*     */   {
/* 303 */     String variable = "";
/* 304 */     int caracter = inn[(ii++)];
			
//System.out.println("NUMERO DE OPERACION: "+caracter);
/*     */     do {
/* 306 */       variable = variable + (char)caracter;
/* 307 */       caracter = inn[(ii++)];
/* 308 */     } while (caracter != 35);
				
				//Si es un sensor lo que se evalúa con un número
				//if(evaluaSensor)
				//{
					//if(Double.parseDouble(variable) > 0)
						//condicion += "true";
					//else
						//condicion += "false";
				//}
				//else
					//condicion += variable;
				//else
				//condicion+= variable+" ";
				//evaluaSensor = false;
				//System.out.println(condicion);
/* 309 */     return Double.parseDouble(variable);
/*     */   }
/*     */   
/*     */   private int popStackOper() {
/* 408 */     int operd = 0;
/*     */     try {
/* 410 */       Integer operador = (Integer)pilaOper.pop();
/* 411 */       operd = operador.intValue();
/*     */     } catch (java.util.EmptyStackException e) {}
/* 413 */     return operd;
/*     */   }
/*     */   
/*     */   private boolean popStackBoolean(Stack pila) {
/* 417 */     boolean temp = false;
/*     */     try {
/* 419 */       Boolean aux = (Boolean)pila.pop();
/* 420 */       temp = aux.booleanValue();
/*     */     } catch (java.util.EmptyStackException e) {}
/* 422 */     return temp;
/*     */   }
/*     */   
/*     */ 
/*     */   private void reducirMulDiv()
/*     */   {
/* 428 */     int operd = popStackOper();
/* 429 */     double temp; double valor; switch (operd) {
/*     */     case 18: 
/* 431 */       temp = popStackNum();
/* 432 */       valor = temp * popStackNum();
/* 433 */       pilaNum.push(new Double(valor));
/* 434 */       break;
/*     */     case 19: 
/* 436 */       temp = popStackNum();
/* 437 */       valor = popStackNum() / temp;
/* 438 */       pilaNum.push(new Double(valor));
/*     */     }
/*     */     
/*     */   }
/*     */   
/*     */   private void reducir()
/*     */   {
/* 445 */     boolean band = false;boolean once = false;
/* 446 */     double valor = 0.0D;
/*     */     
/* 448 */     int operd = popStackOper();
/*     */     do { double temp;
/* 450 */       switch (operd) {
/*     */       case 40: 
/* 452 */         if (!pilaOper.empty()) {
/* 453 */           operd = popStackOper();
/* 454 */           if ((operd == 18) || (operd == 19)) {
/* 455 */             pilaOper.push(new Integer(operd));
/* 456 */             once = true;
/*     */           }
/*     */         }
/* 459 */         band = true;
/* 460 */         break;
/*     */       case 16: 
/* 462 */         temp = popStackNum();
/* 463 */         valor = temp + popStackNum();
/* 464 */         pilaNum.push(new Double(valor));
/* 465 */         break;
/*     */       case 17: 
/* 467 */         temp = popStackNum();
/* 468 */         valor = popStackNum() - temp;
/* 469 */         pilaNum.push(new Double(valor));
/* 470 */         break;
/*     */       case 18: 
/* 472 */         temp = popStackNum();
/* 473 */         valor = temp * popStackNum();
/* 474 */         pilaNum.push(new Double(valor));
/* 475 */         once = false;
/* 476 */         break;
/*     */       case 19: 
/* 478 */         temp = popStackNum();
/* 479 */         valor = popStackNum() / temp;
/* 480 */         pilaNum.push(new Double(valor));
/* 481 */         once = false;
/*     */       }
/*     */       
/*     */       
/* 485 */       valor = 0.0D;
/* 486 */       operd = popStackOper();
/* 487 */     } while (((!band ? 1 : 0) | (band == true ? 1 : 0) & (once == true ? 1 : 0)) != 0);
/* 488 */     pilaOper.push(new Integer(operd));
/*     */   }
/*     */   
/*     */   private boolean isDelimit(int caracter) {
/* 492 */     boolean band = false;
/* 493 */     switch (caracter) {
/* 494 */     case 37:  band = true;
/* 495 */       break;
/* 496 */     case 93:  band = true;
/* 497 */       break;
/*     */     case 14: case 15: 
/* 499 */       band = true;
/* 500 */       break;
/*     */     case 6: case 7: case 8: case 9: case 11: case 12: 
/* 502 */       band = true;
/*     */     }
/*     */     
/*     */     
/* 506 */     return band;
/*     */   }
/*     */   
/*     */   private double getDoubleVar() {
/* 510 */     double temp = 0.0D;
/* 511 */     int id = inn[(ii++)];
/* 512 */     if (tablaEDLocal.buscar_Id(id)) { temp = tablaEDLocal.buscarIdValor2(id);
/* 513 */     } else if (tablaEDGlobal.buscar_Id(id)) temp = tablaEDGlobal.buscarIdValor2(id);
/* 514 */     return temp;
/*     */   }
/*     */   
/*     */   private double getSensor() {
/* 518 */     double temp = 0.0D;
			
			String tempNombreSensor = "";
			Sensor tempS;
/* 519 */     int id = inn[(ii++)];
			System.out.println("....idSensor: "+id);
			
				for(int i = 1; i <= agente.tablaSensores.count(); i++)
				{
					if(id == agente.tablaSensores.getIdIndice(i))
					{
						tempTipoSensor = agente.tablaSensores.getTipoIndice(i);
						tempNombreSensor = agente.tablaSensores.getNombre(i);
					}
				}
				System.out.println("TIPOSENSOR: "+tempTipoSensor);
				if(tempTipoSensor == 5)
				{
					condicion += "sensorLuz.getLightValue() ";
					//evaluaSensor = true; //los valores de la condición depende de si es un sensor el que se está evaluando
				}
				if(tempTipoSensor == 4)
				{
					condicion += "sensorTactil.isPressed() ";
					evaluaSensor = true; //los valores de la condición depende de si es un sensor el que se está evaluando
				}
				if(tempTipoSensor == 6)
					condicion += "sensorTemp.getTemperature() ";
/* 520 */     temp = agente.tablaSensores.buscarIdValor2(id);
			System.out.println("conducta actual : "+condicion);
/* 521 */     return temp;
/*     */   }
/*     */ }

/* Location:           C:\Users\Pab\Desktop\InAge exe\InAge exe\Age2000W.jar
 * Qualified Name:     MaquinaVirtual.Conducta
 * Java Class Version: 1.1 (45.3)
 * JD-Core Version:    0.7.1
 */

