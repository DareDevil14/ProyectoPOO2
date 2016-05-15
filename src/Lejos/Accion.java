package Lejos;

import Herramientas.TablaSimbolos;

import java.util.*;
import java.lang.Integer;
import java.lang.Double;

public class Accion
/*     */ {
/*     */   Agente agente;
/*     */   String directorio;
/*     */   int entrada;
/*     */   int id;
/*     */   ArchivoObjeto in;
/*     */   int init;
/*     */   String nombre;
/*     */   Stack pilaNum;
/*     */   Stack pilaOper;
/*     */   Stack pilaOperL;
/*     */   Stack pilaValorL;
/*     */   TablaSimbolos tablaEDGlobal;
/*     */   TablaSimbolos tablaEDLocal;
			boolean boolAvanza = false;
			boolean boolRetrocede = false;
			boolean boolGiraIzq = false;
			boolean boolGiraDer = false;
			//Variables para Lejos
			String accionRobot = "";
			//boolean accionDeclarada = false;

/*     */   
/*     */   public void initial(String directorio, String nombreAccion, TablaSimbolos tablaEDGlobal, Agente agente)
/*     */   {
/*  28 */     this.agente = agente;
/*  29 */     this.directorio = directorio;
/*  30 */     nombre = nombreAccion;
/*  31 */     init = (entrada = 0);
/*  32 */     this.tablaEDGlobal = tablaEDGlobal;
/*  33 */     tablaEDLocal = new TablaSimbolos();
/*  34 */     pilaNum = new Stack();
/*  35 */     pilaOper = new Stack();
/*  36 */     pilaOperL = new Stack();
/*  37 */     pilaValorL = new Stack();
/*  38 */     abrirArchivo();
/*  39 */     initTablaEDLocal();
/*  40 */     in.cierra_archivo();
/*     */   }

		public void initial2(String directorio, String nombreAccion, TablaSimbolos tablaEDGlobal)
/*     */   {
/*  29 */     this.directorio = directorio;
/*  30 */     nombre = nombreAccion;
/*  31 */     init = (entrada = 0);
/*  32 */     this.tablaEDGlobal = tablaEDGlobal;
/*  33 */     tablaEDLocal = new TablaSimbolos();
/*  34 */     pilaNum = new Stack();
/*  35 */     pilaOper = new Stack();
/*  36 */     pilaOperL = new Stack();
/*  37 */     pilaValorL = new Stack();
/*  38 */     abrirArchivo();
/*  39 */     initTablaEDLocal();
/*  40 */     in.cierra_archivo();
/*     */   }
/*     */   
/*     */   public void setNombre(String nombreAccion) {
/*  44 */     nombre = nombreAccion;
/*     */   }
/*     */   
/*     */   public void setId(int id) {
/*  48 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getNombreAccion() {
/*  52 */     return nombre;
/*     */   }
/*     */   
/*     */   public int getId() {
/*  56 */     return id;
/*     */   }
/*     */   
/*     */   private void abrirArchivo() {
				
				System.out.println("Abriendo archivo accion obj: "+nombre);
				System.out.println(directorio + nombre + ".Age2000.obj");
/*  60 */     	in = new ArchivoObjeto(directorio + nombre + ".Age2000.obj");
				
/*     */   }
/*     */   public String getAccionRobot(){
			accionRobot += "\n\t}\n";
				
			return accionRobot;
}
/*     */   public void start() {
				System.out.println();
				accionRobot+= "static void "+nombre+"(){\n";	
				
/*  66 */     instrucciones();
/*     */   }
/*     */   
/*     */   private void instrucciones() {
/*  70 */     while (entrada != -1) {
/*  71 */       switchInstrucciones();
/*  72 */       entrada = in.getCodigo();
/*     */     }
/*     */   }
/*     */   
/*     */   private void switchInstrucciones() {
/*     */     int caracter;
/*     */     int numInstr;
/*     */     boolean eval;
				System.out.println("ENTRADA EN ACCIONES: "+entrada);
/*  80 */     switch (entrada) {
/*  81 */     case 1:  //caso cuando solicitan accioness
				int id = in.getCodigo();
				System.out.println("ID EN ACCIONES: "+id);
/*  82 */       switch (id) {
/*  83 */       	case 2:  //System.out.println("********************************Agente: " + agente.nombre + " AVANZA");
/*  84 */         	accionRobot +="\t\tLCD.drawString(\"dar_un_paso\",0,0);\n";
					accionRobot += "\t\tAvanzar.condicion = true;\n";
					//agente.avanzar();
					boolAvanza = true;
/*  85 */         	break;
/*  86 */       	case 3:  //System.out.println("********************************Agente: " + agente.nombre + "  RETROCEDE");
					accionRobot +="\t\tLCD.drawString(\"retrocede\",0,1);\n";
					accionRobot += "\t\tRetroceder.condicion = true;\n";
/*  87 */         	//agente.retroceder();
					boolRetrocede= true;
/*  88 */         	break;
/*  89 */       	case 4:  //System.out.println("********************************Agente: " + agente.nombre + "  GIRA_IZQ");
					accionRobot +="\t\tLCD.drawString(\"giraIzq\",0,2);\n";
					accionRobot += "\t\tGiraIzquierda.condicion = true;\n";
/*  90 */         	//agente.gira_izq();
					boolGiraIzq = true;
/*  91 */         	break;
/*  92 */       	case 5:  //System.out.println("********************************Agente: " + agente.nombre + "  GIRA_DER");
					accionRobot +="\t\tLCD.drawString(\"giraDer\",0,3);\n";				
					accionRobot += "\t\tGiraDerecha.condicion = true;\n";
/*  93 */         	//agente.gira_der();
					boolGiraDer = true;
/*     */       }
/*     */       
/*  96 */      break;
/*  97 */     case 61:  if (in.getCodigo() == 36) {
/*  98 */         int idVar = in.getCodigo();
/*  99 */         double resultado = operacion();
/* 100 */         if (tablaEDLocal.buscar_Id(idVar)) {
/* 101 */           tablaEDLocal.actualiza_Id_valor2(idVar, resultado);
/*     */ 
/*     */         }
/* 104 */         else if (tablaEDGlobal.buscar_Id(idVar)) {
/* 105 */           tablaEDGlobal.actualiza_Id_valor2(idVar, resultado);
/*     */         }
/*     */       }
/* 108 */       break;
case 22: //repite
	accionRobot += "for( ";
/*  94 */       caracter = in.getCodigo();
/*  95 */       double ciclo = 0.0D;
/*  96 */       numInstr = 0;
/*  97 */       if (caracter == 91) { //[
/*  98 */         ciclo = operacion();
					System.out.println("OPERACIONCICLO: "+ciclo);
/*     */       }
accionRobot += "int i = 0; i < "+ciclo+"; i++){\n";
/* 100 */       if (in.getCodigo() == 123) {//{
/* 101 */         numInstr = in.getCodigo();
/* 102 */         if (ciclo > 0.0D) {
		
/* 103 */           //for (int i = 0; i < ciclo; i++) {
/*     */             do {
/* 105 */               entrada = in.getCodigo();
/* 106 */               switchInstrucciones();
/* 107 */             } while (entrada != 125);//}
		  
/* 108 */             //if (i + 1 < ciclo) { 
			//	ii -= numInstr;//recalcular el ii para realizar otro ciclo
/*     */             //}
/*     */           //}
/*     */         } else {
/* 112 */           for (int y = 0; y < numInstr; y++)
/* 113 */             in.getCodigo();
/*     */         }
/*     */       }
//cierra el for
accionRobot += "\n}";
System.out.println("FORFORFORFORFORFORFORFORFORFORFORFORFORFORFOR");

System.out.println(accionRobot);

System.out.println("FORFORFORFORFORFORFORFORFORFORFORFORFORFORFOR");
/* 116 */       break;

/*     */     case 23: //si
/* 134 */       caracter = in.getCodigo();
/* 135 */       numInstr = 0;
/* 136 */       eval = true;
/* 137 */       if (caracter == 91) {
/* 138 */         eval = EvalCondicion();
/*     */       }
/* 140 */       if (in.getCodigo() == 123) {
/* 141 */         numInstr = in.getCodigo();
/* 142 */         if (eval) {
/* 143 */           entrada = in.getCodigo();
/*     */           do {
/* 145 */             switchInstrucciones();
/* 146 */             entrada = in.getCodigo();
/* 147 */           } while (entrada != 125);
/*     */         }
/*     */         else {
/* 150 */           for (int y = 0; y < numInstr; y++) {
/* 151 */             in.getCodigo();
/*     */           }
/* 153 */           if (in.getCodigo() == 24) {
/* 154 */             if (in.getCodigo() == 123) {
/* 155 */               numInstr = in.getCodigo();
/* 156 */               entrada = in.getCodigo();
/*     */               do {
/* 158 */                 switchInstrucciones();
/* 159 */                 entrada = in.getCodigo();
/* 160 */               } while (entrada != 125);
/*     */             }
/*     */           }
/*     */           else
/* 164 */             in.setCodigoIndice(1);
/*     */         }
/*     */       }
/* 167 */       break;
/*     */     case 21: //mientras
/* 169 */       caracter = in.getCodigo();
/* 170 */       numInstr = 0;
/* 171 */       eval = true;
/* 172 */       if (caracter == 91) {
/* 173 */         eval = EvalCondicion();
/*     */       }
/* 175 */       if (in.getCodigo() == 123) {
/* 176 */         numInstr = in.getCodigo();
/* 177 */         if (eval) {
/* 178 */           entrada = in.getCodigo();
/*     */           do {
/* 180 */             switchInstrucciones();
/* 181 */             entrada = in.getCodigo();
/* 182 */           } while (entrada != 125);
/* 183 */           int num = in.getCodigo();
/* 184 */           in.setCodigoIndice(num + 1);
/*     */         }
/*     */         else {
/* 187 */           for (int y = 0; y < numInstr + 1; y++) {
/* 188 */             in.getCodigo();
/*     */           }
/*     */         }
/*     */       }
/*     */       break;
/*     */     }
			
/*     */     
/*     */   }
/*     */   
/*     */   private void condicion()
/*     */   {
/* 199 */     double x = operacion();
/* 200 */     double y; switch (entrada) {
/*     */     case 6: 
/* 202 */       y = operacion();
/* 203 */       if (x < y) pilaValorL.push(new Boolean(true)); else
/* 204 */         pilaValorL.push(new Boolean(false));
/* 205 */       break;
/*     */     case 7: 
/* 207 */       y = operacion();
/* 208 */       if (x > y) pilaValorL.push(new Boolean(true)); else
/* 209 */         pilaValorL.push(new Boolean(false));
/* 210 */       break;
/*     */     case 8: 
/* 212 */       y = operacion();
/* 213 */       if (x <= y) pilaValorL.push(new Boolean(true)); else
/* 214 */         pilaValorL.push(new Boolean(false));
/* 215 */       break;
/*     */     case 9: 
/* 217 */       y = operacion();
/* 218 */       if (x >= y) pilaValorL.push(new Boolean(true)); else
/* 219 */         pilaValorL.push(new Boolean(false));
/* 220 */       break;
/*     */     case 11: 
/* 222 */       y = operacion();
/* 223 */       if (x == y) pilaValorL.push(new Boolean(true)); else
/* 224 */         pilaValorL.push(new Boolean(false));
/* 225 */       break;
/*     */     case 12: 
/* 227 */       y = operacion();
/* 228 */       if (x != y) pilaValorL.push(new Boolean(true)); else
/* 229 */         pilaValorL.push(new Boolean(false));
/* 230 */       break;
/* 231 */     case 10: default:  pilaValorL.push(new Boolean(true));
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean EvalCondicion()
/*     */   {
/* 237 */     Stack pilaTemp = new Stack();
/* 238 */     boolean aux = false;boolean band = false;
/* 239 */     condicion();
/*     */     do {
/* 241 */       switch (entrada) {
/* 242 */       case 14:  pilaOperL.push(new Boolean(true));
/* 243 */         band = true;
/* 244 */         break;
/* 245 */       case 15:  pilaOperL.push(new Boolean(false));
/* 246 */         band = true;
/*     */       }
/*     */       
/* 249 */       if (band) condicion();
/* 250 */       band = false;
/* 251 */     } while (entrada != 93);
/* 252 */     while (!pilaOperL.isEmpty()) {
/* 253 */       aux = popStackBoolean(pilaOperL);
/* 254 */       if (aux) {
/* 255 */         if ((popStackBoolean(pilaValorL) & popStackBoolean(pilaValorL))) pilaValorL.push(new Boolean(true)); else {
/* 256 */           pilaValorL.push(new Boolean(false));
/*     */         }
/*     */       } else {
/* 259 */         pilaTemp.push(new Boolean(popStackBoolean(pilaValorL)));
/*     */       }
/*     */     }
/* 262 */     pilaTemp.push(new Boolean(popStackBoolean(pilaValorL)));
/*     */     do {
/* 264 */       aux = popStackBoolean(pilaTemp);
/* 265 */     } while (((!aux ? 1 : 0) & (!pilaTemp.isEmpty() ? 1 : 0)) != 0);
/* 266 */     return aux;
/*     */   }
/*     */   
/*     */   private void initTablaEDLocal()
/*     */   {
/* 271 */     String variable = "";
/* 272 */     int id = 0;int tipo = 0;
/* 273 */     double valor = 0.0D;
/*     */     
/* 275 */     int caracter = in.getCodigo();// @ -> 64 -> variables enteros locales en accion
/* 276 */     if (caracter == 64) {
/* 277 */       caracter = in.getCodigo();
/*     */       do {
/* 279 */         if (caracter == 36) {// $ ->35 ->indica variable 
/* 280 */           variable = in.getVariable();
/* 281 */           id = in.getCodigo();
/* 282 */           tipo = in.getCodigo();
/* 283 */           valor = in.getNumero();
/* 284 */           tablaEDLocal.insertar(variable, tipo, 0, valor, id);
/*     */         }
/* 286 */         caracter = in.getCodigo();
/* 287 */       } while (((caracter != -1 ? 1 : 0) & (caracter != 64 ? 1 : 0)) != 0);
/* 288 */       tablaEDLocal.imprimir();
/* 289 */       in.getCodigo();
/*     */     }
/* 291 */     init = in.getIndice();
/* 292 */     entrada = caracter;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private double operacion()
/*     */   {
/* 300 */     boolean band = false;
/* 301 */     int caracter = 0;
/* 302 */     int i = 0;
/* 303 */     caracter = in.getCodigo();
/*     */     do {
/* 305 */       switch (caracter)
/*     */       {
/*     */ 
/*     */       case 35: 
/* 309 */         pilaNum.push(new Double(in.getNumeroOp()));
/* 310 */         if (band == true) {
/* 311 */           reducirMulDiv();
/* 312 */           band = false; }
/* 313 */         break;
/*     */       case 36: 
	System.out.println("VARIABLE");
/* 315 */         double y = getDoubleVar();
System.out.println("VAR "+y);
/* 316 */         pilaNum.push(new Double(y));
/* 317 */         if (band == true) {
/* 318 */           reducirMulDiv();
/* 319 */           band = false; }
/* 320 */         break;
/*     */       case 16: 
/*     */       case 17: 
/* 323 */         pilaOper.push(new Integer(caracter));
/* 324 */         break;
/*     */       case 18: case 19: 
/* 326 */         pilaOper.push(new Integer(caracter));
/* 327 */         band = true;
/* 328 */         break;
/*     */       case 40: 
/* 330 */         pilaOper.push(new Integer(caracter));
/* 331 */         band = false;
/* 332 */         break;
/*     */       case 41: 
/* 334 */         reducir();
/*     */       }
/*     */       
/* 337 */       caracter = in.getCodigo();
/* 338 */     } while (!isDelimit(caracter));
/* 339 */     entrada = caracter;
/* 340 */     return resultado();
/*     */   }
/*     */   
/*     */   private double resultado() {
/* 344 */     int operd = 0;
/* 345 */     double valor = 0.0, temp;
/* 346 */     if (!pilaOper.empty()) {
/*     */       do {
/* 348 */         Integer operador = (Integer)pilaOper.pop();
/* 349 */         operd = operador.intValue();
/* 350 */         switch (operd) {
/*     */         case 16: 
/* 352 */           temp = popStackNum();
/* 353 */           valor = temp + popStackNum();
/* 354 */           pilaNum.push(new Double(valor));
/* 355 */           break;
/*     */         case 17: 
/* 357 */           temp = popStackNum();
/* 358 */           valor = popStackNum() - temp;
/* 359 */           pilaNum.push(new Double(valor));
/* 360 */           break;
/*     */         case 18: 
/* 362 */           temp = popStackNum();
/* 363 */           valor = temp * popStackNum();
/* 364 */           pilaNum.push(new Double(valor));
/* 365 */           break;
/*     */         case 19: 
/* 367 */           temp = popStackNum();
/* 368 */           valor = popStackNum() / temp;
/* 369 */           pilaNum.push(new Double(valor));
/*     */         }
/*     */         
/*     */         
/* 373 */         temp = valor = 0.0;
/* 374 */       } while (!pilaOper.empty());
/*     */     }
/* 376 */     return popStackNum();
/*     */   }
/*     */   
/*     */   private double popStackNum() {
/* 380 */     double temp = 0.0D;
/*     */     try {
/* 382 */       Double doble = (Double)pilaNum.pop();
/* 383 */       temp = doble.doubleValue();
/*     */     } catch (EmptyStackException e) {}
/* 385 */     return temp;
/*     */   }
/*     */   
/*     */   private int popStackOper() {
/* 389 */     int operd = 0;
/*     */     try {
/* 391 */       Integer operador = (Integer)pilaOper.pop();
/* 392 */       operd = operador.intValue();
/*     */     } catch (EmptyStackException e) {}
/* 394 */     return operd;
/*     */   }
/*     */   
/*     */   private boolean popStackBoolean(Stack pila) {
/* 398 */     boolean temp = false;
/*     */     try {
/* 400 */       Boolean aux = (Boolean)pila.pop();
/* 401 */       temp = aux.booleanValue();
/*     */     } catch (EmptyStackException e) {}
/* 403 */     return temp;
/*     */   }
/*     */   
/*     */ 
/*     */   private void reducirMulDiv()
/*     */   {
/* 409 */     int operd = popStackOper();
/* 410 */     double temp; double valor; switch (operd) {
/*     */     case 18: 
/* 412 */       temp = popStackNum();
/* 413 */       valor = temp * popStackNum();
/* 414 */       pilaNum.push(new Double(valor));
/* 415 */       break;
/*     */     case 19: 
/* 417 */       temp = popStackNum();
/* 418 */       valor = popStackNum() / temp;
/* 419 */       pilaNum.push(new Double(valor));
/*     */     }
/*     */     
/*     */   }
/*     */   
/*     */   private void reducir()
/*     */   {
/* 426 */     boolean band = false;boolean once = false;
/* 427 */     double valor = 0.0D;
/*     */     
/* 429 */     int operd = popStackOper();
/*     */     do { double temp;
/* 431 */       switch (operd) {
/*     */       case 40: 
/* 433 */         if (!pilaOper.empty()) {
/* 434 */           operd = popStackOper();
/* 435 */           if ((operd == 18) || (operd == 19)) {
/* 436 */             pilaOper.push(new Integer(operd));
/* 437 */             once = true;
/*     */           }
/*     */         }
/* 440 */         band = true;
/* 441 */         break;
/*     */       case 16: 
/* 443 */         temp = popStackNum();
/* 444 */         valor = temp + popStackNum();
/* 445 */         pilaNum.push(new Double(valor));
/* 446 */         break;
/*     */       case 17: 
/* 448 */         temp = popStackNum();
/* 449 */         valor = popStackNum() - temp;
/* 450 */         pilaNum.push(new Double(valor));
/* 451 */         break;
/*     */       case 18: 
/* 453 */         temp = popStackNum();
/* 454 */         valor = temp * popStackNum();
/* 455 */         pilaNum.push(new Double(valor));
/* 456 */         once = false;
/* 457 */         break;
/*     */       case 19: 
/* 459 */         temp = popStackNum();
/* 460 */         valor = popStackNum() / temp;
/* 461 */         pilaNum.push(new Double(valor));
/* 462 */         once = false;
/*     */       }
/*     */       
/*     */       
/* 466 */       valor = 0.0D;
/* 467 */       operd = popStackOper();
/* 468 */     } while (((!band ? 1 : 0) | (band == true ? 1 : 0) & (once == true ? 1 : 0)) != 0);
/* 469 */     pilaOper.push(new Integer(operd));
/*     */   }
/*     */   
/*     */   private boolean isDelimit(int caracter) {
/* 473 */     boolean band = false;
/* 474 */     switch (caracter) {
/* 475 */     case 37:  band = true;
/* 476 */       break;
/* 477 */     case 93:  band = true;
/* 478 */       break;
/*     */     case 14: case 15: 
/* 480 */       band = true;
/* 481 */       break;
/*     */     case 6: case 7: case 8: case 9: case 11: case 12: 
/* 483 */       band = true;
/*     */     }
/*     */     
/*     */     
/* 487 */     return band;
/*     */   }
/*     */   
/*     */   private double getDoubleVar() {
/* 491 */     double temp = 0.0D;
/* 492 */     int id = in.getCodigo();
/* 493 */     if (tablaEDLocal.buscar_Id(id)) { temp = tablaEDLocal.buscarIdValor2(id);
/* 494 */     } else if (tablaEDGlobal.buscar_Id(id)) temp = tablaEDGlobal.buscarIdValor2(id);
/* 495 */     return temp;
/*     */   }
/*     */ }