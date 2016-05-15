package MaquinaVirtual;

import Herramientas.TablaSimbolos;
import java.util.*;
import java.lang.Integer;
import java.lang.Double;

public class Accion extends Thread{

  Agente agente;
  String nombre;  // Nombre de la accion
  int id;
  String directorio; // Directorio en donde se encuentra el archivo de la accion
  ArchivoObjeto in;
  int init, entrada;
  TablaSimbolos tablaEDLocal;
  TablaSimbolos tablaEDGlobal;
  Stack pilaNum;
  Stack pilaOper;
  Stack pilaOperL;
  Stack pilaValorL;


  public Accion(ThreadGroup grupo, String directorio, String nombreAccion, TablaSimbolos tablaEDGlobal, Agente agente){
    super(grupo, nombreAccion);
    this.agente = agente;
    this.directorio = directorio;
    this.nombre = nombreAccion;
    init= entrada = 0;
    this.tablaEDGlobal = tablaEDGlobal;
    tablaEDLocal = new TablaSimbolos();
    pilaNum = new Stack();
    pilaOper = new Stack();
    pilaOperL = new Stack();
    pilaValorL = new Stack();
    abrirArchivo();
    initTablaEDLocal();
    in.cierra_archivo();

  }

  public Accion(){
    // El constructor es vacío
  }

  public void setNombre(String nombreAccion){
    this.nombre = nombreAccion;
  }

  public void setId(int id){
    this.id = id;
  }

  public String getNombreAccion(){
    return nombre;
  }

  public long getId(){
    return id;
  }

  private void abrirArchivo(){
    in = new ArchivoObjeto(directorio+"\\"+nombre+".Age2000.obj");
  }

   public void run(){
     for(;;){
       abrirArchivo();  // Abre el archivo las veces que sea necesario.
       entrada = in.getIndex(init); // Toma el primer caracter del conjunto de instrucciones
       instrucciones(); // ejecuta las instrucciones
     }
   }

   private void instrucciones(){
     while(entrada!=-1){
       switchInstrucciones();
       entrada = in.getCodigo();
     }
   }

   private void switchInstrucciones(){
     int caracter, numInstr, num;
     double ciclo;
     boolean eval;
     switch(entrada){
       case 1:  int id = in.getCodigo();   // Primitiva
                switch(id){
                  case 2: System.out.println("******************************** AVANZA");
                          agente.avanzar();
                          break;
                  case 3: System.out.println("******************************** RETROCEDE");
                          agente.retroceder();
                          break;
                  case 4: System.out.println("******************************** GIRA_IZQ");
                          agente.gira_izq();
                          break;
                  case 5: System.out.println("******************************** GIRA_DER");
                          agente.gira_der();
                          break;
                }
                break;
       case '=':if(in.getCodigo()=='$'){ // Detecta una asignación
                   int idVar = in.getCodigo();
                   double resultado = operacion();
                   if(tablaEDLocal.buscar_Id(idVar)){
                     tablaEDLocal.actualiza_Id_valor2(idVar, resultado);
                   }
                   else{
                     if(tablaEDGlobal.buscar_Id(idVar)){
                       tablaEDGlobal.actualiza_Id_valor2(idVar, resultado);
                     }
                   }
                }
                break;
         case 22: caracter = in.getCodigo();  // Evalúa la condición REPITE
                  ciclo = 0.0;
                  numInstr=0;
                  if(caracter=='['){
                    ciclo = operacion();
                  }
                  if(in.getCodigo()=='{'){// llaves de inicio de instrucciones (INICIO)
                    numInstr = in.getCodigo(); // número de instrucciones en el ciclo
                    if (ciclo > 0) {
                      for (int i = 0; i < ciclo; i++) {
                        do{
                          entrada = in.getCodigo();
                          switchInstrucciones();
                        }while(entrada!='}');
                        if(i+1<ciclo) in.setCodigoIndice(numInstr);
                      }
                    }
                    else{ // no hay ciclo qué cumplir
                      for (int y = 0; y < numInstr; y++) {
                        in.getCodigo();
                      }
                    }
                  }
                  break;
         case 23: caracter = in.getCodigo();  // Evalúa la condición "SI"
                  numInstr=0;
                  eval = true;
                  if(caracter=='['){
                    eval = EvalCondicion();
                  }
                   if(in.getCodigo()=='{'){// llaves de inicio de instrucciones (INICIO)
                      numInstr = in.getCodigo(); // número de instrucciones en la condición
                      if (eval) {
                            entrada = in.getCodigo();
                            do{
                               switchInstrucciones();
                               entrada = in.getCodigo();
                            }while(entrada!='}');
                       }
                       else{ // no hay ciclo qué cumplir
                          for (int y = 0; y < numInstr; y++) {
                             in.getCodigo();
                          }
                          if(in.getCodigo()==24){ // condición alternativa OTRO
                            if(in.getCodigo()=='{'){
                              numInstr = in.getCodigo();
                              entrada = in.getCodigo();
                              do{
                                switchInstrucciones();
                                entrada = in.getCodigo();
                              }while(entrada!='}');
                            }
                          }
                          else{ // si no se encuentra declarado un proceso alternativo (OTRO)
                            in.setCodigoIndice(1);
                          }
                       }
                   }
                  break;
         case 21: caracter = in.getCodigo();  // Evalúa la condición "MIENTRAS"
                  numInstr=0;
                  eval = true;
                  if(caracter=='['){
                    eval = EvalCondicion();
                  }
                  if(in.getCodigo()=='{'){// llaves de inicio de instrucciones (INICIO)
                    numInstr = in.getCodigo(); // número de instrucciones en la condición
                    if (eval) {
                        entrada = in.getCodigo();
                        do{
                          switchInstrucciones();
                          entrada = in.getCodigo();
                        }while(entrada!='}');
                        num = in.getCodigo(); // dato del total de caracteres que comprende "mientras"
                        in.setCodigoIndice(num+1);
                    }
                    else{ // no hay ciclo qué cumplir
                        for (int y = 0; y < numInstr+1; y++) {
                          in.getCodigo();}
                    }
                  }
                  break;


       }
   }

   private void condicion(){
     double x, y;
     x = operacion();
     switch(entrada){
       case 6:// '<'
               y = operacion();
               if(x<y) pilaValorL.push(new Boolean (true));
               else pilaValorL.push(new Boolean (false));
               break;
       case 7:// '>'
               y = operacion();
               if(x>y) pilaValorL.push(new Boolean (true));
               else pilaValorL.push(new Boolean (false));
               break;
       case 8:// '<='
               y = operacion();
               if(x<=y) pilaValorL.push(new Boolean (true));
               else pilaValorL.push(new Boolean (false));
               break;
       case 9:// '>='
               y = operacion();
               if(x>=y) pilaValorL.push(new Boolean (true));
               else pilaValorL.push(new Boolean (false));
               break;
       case 11:// '=='
               y = operacion();
               if(x==y) pilaValorL.push(new Boolean (true));
               else pilaValorL.push(new Boolean (false));
               break;
       case 12:// '!='
               y = operacion();
               if(x!=y) pilaValorL.push(new Boolean (true));
               else pilaValorL.push(new Boolean (false));
               break;
       default:pilaValorL.push(new Boolean (true));
               break;
     }
   }

   private boolean EvalCondicion(){
     Stack pilaTemp = new Stack();
     boolean aux = false, band = false;
     condicion();
     do{
       switch(entrada){
         case 14: pilaOperL.push(new Boolean (true)); // & --> AND
                  band = true;
                  break;
         case 15: pilaOperL.push(new Boolean (false));// |  -->  OR
                  band = true;
                  break;
       }
       if(band) condicion();
       band = false;
     }while(entrada!=']');
     while(pilaOperL.isEmpty()==false){
       aux = popStackBoolean(pilaOperL);
       if(aux){ // si es un AND
           if (popStackBoolean(pilaValorL)& popStackBoolean(pilaValorL)) pilaValorL.push(new Boolean(true));
           else pilaValorL.push(new Boolean(false));
       }
       else{ // si es un OR
           pilaTemp.push(new Boolean(popStackBoolean(pilaValorL)));
       }
     }
     pilaTemp.push(new Boolean(popStackBoolean(pilaValorL)));
     do{
       aux = popStackBoolean(pilaTemp);
     }while(aux==false &  pilaTemp.isEmpty()==false);
     return aux;
   }


   private void initTablaEDLocal(){
  String variable= "";
  int id=0, tipo = 0;
  double valor= 0.0;
  System.out.println("----------------------ACCION -----------------" + nombre);
  int caracter= in.getCodigo();
  if(caracter == 64){ // '@'  Si se declaran las variables tipo Entero y Decimal
    caracter = in.getCodigo();
    do{
        if (caracter == '$') { // Indicador de variable
          variable = in.getVariable();
          id = in.getCodigo();
          tipo = in.getCodigo();
          valor = in.getNumero();
          tablaEDLocal.insertar(variable,tipo,0,valor,id);
        }
        caracter = in.getCodigo();
    }while(caracter != -1 & caracter != 64);
    tablaEDLocal.imprimir(); // Sólo para depurar
    in.getCodigo();
  }
  init = in.getIndice();   // Importante conocer la ubicación de las primeras instrucciones, en el archivo
  entrada = caracter;
}

// ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **
//                                   Evaluación de operaciones aritméticas
// ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **

private double operacion(){
boolean band = false;
int caracter =0;
int i=0;
caracter = in.getCodigo();
do{
  switch(caracter){
    case '#': /*double x= 0.0;
              x = in.getNumeroOp();
              pilaNum.push(new Double(x));*/
              pilaNum.push(new Double (in.getNumeroOp()));
              if(band == true){
                reducirMulDiv();
                band = false;
              }
              break;
    case '$': double y= getDoubleVar();
              pilaNum.push(new Double(y));
              if(band == true){
                reducirMulDiv();
                band = false;
              }
              break;
    case 16: case 17: // + y -
              pilaOper.push(new Integer(caracter));
              break;
    case 18: case 19:  // * y |
               pilaOper.push(new Integer(caracter));
               band = true;
               break;
    case '(':
              pilaOper.push(new Integer(caracter));
              band = false;
              break;
    case ')':
              reducir();
              break;
  }
  caracter = in.getCodigo();
}while(!isDelimit(caracter));
return resultado();
}

private double resultado(){
int operd = 0;
double valor= 0.0, temp;
if(pilaOper.empty()==false){
  do {
    Integer operador = (Integer) pilaOper.pop();
    operd = operador.intValue();
    switch (operd) {
      case 16: // +
        temp = popStackNum();
        valor = temp + popStackNum();
        pilaNum.push(new Double(valor));
        break;
      case 17: // -
        temp = popStackNum();
        valor = popStackNum() - temp;
        pilaNum.push(new Double(valor));
        break;
      case 18: // *
        temp = popStackNum();
        valor = temp * popStackNum();
        pilaNum.push(new Double(valor));
        break;
      case 19: // '/'
        temp = popStackNum();
        valor = popStackNum() / temp;
        pilaNum.push(new Double(valor));
        break;

    }
    temp = valor = 0.0;
  }while(!pilaOper.empty());
}
return popStackNum();
}

private double popStackNum(){
double temp = 0.0;
try{
  Double doble = (Double) pilaNum.pop();
  temp = doble.doubleValue();
}catch(EmptyStackException e){}
return temp;
}

private int popStackOper(){
int operd=0;
try{
  Integer operador = (Integer) pilaOper.pop();
  operd = operador.intValue();
}catch(EmptyStackException e){}
return operd;
}

private boolean popStackBoolean(Stack pila){
    boolean temp = false;
    try{
      Boolean aux = (Boolean) pila.pop();
      temp = aux.booleanValue();
    }catch(EmptyStackException e){}
    return temp;
  }

private void reducirMulDiv(){
double temp, valor;
int operd;
operd = popStackOper();
switch (operd) {
  case 18: // *
           temp = popStackNum();
           valor = temp * popStackNum();
           pilaNum.push(new Double(valor));
           break;
  case 19: // '/'
           temp = popStackNum();
           valor = popStackNum() / temp;
           pilaNum.push(new Double(valor));
           break;
}

}

private void reducir(){  // Reduce *, /, a causa de encontrar los paréntesis
boolean band = false, once = false;
double valor= 0.0, temp;
int operd;
operd = popStackOper();
do{
  switch (operd) {
    case '(':// verifica si existe la multiplicación previamente
             if(pilaOper.empty()==false){ // si siguen habiendo
               operd = popStackOper();
               if (operd == 18 || operd == 19) {
                 pilaOper.push(new Integer(operd));
                 once = true;
               }
             }
             band = true;
             break;
    case 16: // +
             temp = popStackNum();
             valor = temp + popStackNum();
             pilaNum.push(new Double(valor));
             break;
    case 17: // -
             temp = popStackNum();
             valor = popStackNum() - temp;
             pilaNum.push(new Double(valor));
             break;
    case 18: // *
             temp = popStackNum();
             valor = temp * popStackNum();
             pilaNum.push(new Double(valor));
             once = false;
             break;
    case 19: //'/'
             temp = popStackNum();
             valor = popStackNum() / temp;
             pilaNum.push(new Double(valor));
             once = false;
             break;

  }
  valor = 0.0;
  operd = popStackOper();
}while(band==false | (band==true & once==true));
pilaOper.push(new Integer(operd));
}

private boolean isDelimit(int caracter){
boolean band = false;
switch(caracter){
  case '%': band = true;  // Termina una operación
            break;
  case ']': band = true;  // Termina una condición
            break;
  case 14: case 15: // & y |  // lo siguiente es un operador lógico
            band = true;
            break;
  case 6: case 7: case 8: case 9: case 11: case 12: // operadores relacionales
            band = true;
            break;

}
return band;
}

private double getDoubleVar(){
  double temp = 0.0;
  int id = in.getCodigo();
  if(tablaEDLocal.buscar_Id(id))  temp = tablaEDLocal.buscarIdValor2(id);
  else if(tablaEDGlobal.buscar_Id(id))  temp = tablaEDGlobal.buscarIdValor2(id);
  return temp;
}


// ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **

}
