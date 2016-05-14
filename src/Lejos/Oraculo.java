
/*    */ package Lejos;
/*    */ 
/*    */ import Herramientas.TablaSimbolos;
/*    */ 
/*    */ public class Oraculo extends Thread
/*    */ {
/*    */   Accion accion;
/*    */   Agente agente;
/*    */   int count;
/*    */   String directorio;
/*    */   ThreadGroup grupoHilos;
/*    */   int idAccion;
/*    */   String nombre;
/*    */   TablaSimbolos tablaAcciones;
/*    */   TablaSimbolos tablaConductas;
/*    */   TablaSimbolos tablaEDGlobal;
/*    */   TablaSimbolos tablaEDLocal;
/*    */   
/*    */   public Oraculo(ThreadGroup grupoH, String nombre, String directorio, TablaSimbolos tablaConductas, TablaSimbolos tablaAcciones, TablaSimbolos tablaEDGlobal, Agente agente) {
/* 20 */     super(grupoH, "Oráculo " + nombre);
/* 21 */     grupoHilos = grupoH;
/* 22 */     this.nombre = nombre;
/* 23 */     this.directorio = directorio;
/* 24 */     this.tablaConductas = tablaConductas;
/* 25 */     this.tablaAcciones = tablaAcciones;
/* 26 */     this.tablaEDGlobal = tablaEDGlobal;
/* 27 */     this.agente = agente;
/*    */     
/* 29 */     idAccion = 0;
/* 30 */     count = tablaConductas.count();
/* 31 */     accion = new Accion();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void run()
/*    */   {
/* 40 */     int temp = 0;
/*    */     //for (;;)
/*    */     //{
	
/* 43 */      // boolean band = false;
/* 44 */       for (int i = 1; i <= count; i++) {
				//System.out.println("count: "+count);
				//System.out.println("getUSO: "+tablaConductas.getUso(i));
/* 45 */         //if (tablaConductas.getUso(i) == true) {
		
/* 46 */           temp = tablaConductas.getTipoIndice(i);
					System.out.println("temp Dentro de oraculo: "+temp);
					initAccion(accion, temp);
					System.out.println("Oráculo " + nombre + ", ACCION " + idAccion);
/* 53 */         	//tablaConductas.actualizaAllUso(false);
/* 54 */            
				//}
/* 47 */           //band = true;
/* 48 */           //break;
/*    */         //}
/*    */       }
				//System.out.println("band: "+band);
/* 51 */       //if (band) {
///* 52 */         initAccion(accion, temp);
///* 53 */         tablaConductas.actualizaAllUso(false);
///* 54 */         System.out.println("Oráculo " + nombre + ", ACCION " + idAccion);
///*    */       }
/*    */     //}
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void initAccion(Accion accion, int temp)
/*    */   {
/* 67 */     idAccion = temp;
/* 68 */     String var = tablaAcciones.getNombreId(idAccion);// en var se guarda el nombre de la accion, es la misma del archivo que contiene el código
															//objeto
			
/* 69 */     accion.initial(directorio, var, tablaEDGlobal, agente);
/* 70 */     accion.start();
/*    */   }
/*    */ }

/* Location:           C:\Users\Pab\Desktop\InAge exe\InAge exe\Age2000W.jar
 * Qualified Name:     MaquinaVirtual.Oraculo
 * Java Class Version: 1.1 (45.3)
 * JD-Core Version:    0.7.1
 */



/*
package MaquinaVirtual;

import Herramientas.TablaSimbolos;

public class Oraculo extends Thread{

  Agente agente;
  String nombre;
  String directorio; // Directorio en donde se encuentra el archivo de la conducta
  boolean result;
  TablaSimbolos tablaEDLocal;
  TablaSimbolos tablaEDGlobal;
  TablaSimbolos tablaConductas;
  TablaSimbolos tablaAcciones;
  int idAccion, count; // accion que se ejecuta actualmente, cantidad de conductas
  Accion accion;
  ThreadGroup grupoHilos;

  public Oraculo(ThreadGroup grupoH, String nombre, String directorio, TablaSimbolos tablaConductas, TablaSimbolos tablaAcciones, TablaSimbolos tablaEDGlobal, Agente agente){
    super(grupoH, "Oráculo "+nombre);
    grupoHilos = grupoH;
    this.nombre = nombre;
    this.directorio = directorio;
    this.tablaConductas = tablaConductas;
    this.tablaAcciones = tablaAcciones;
    this.tablaEDGlobal = tablaEDGlobal;
    this.agente = agente;
    result = false;
    idAccion = 0;
    count = tablaConductas.count();
    accion = new Accion();
  }

  public synchronized boolean solicita(String conducta, int idAcc){
//    tablaConductas.actualiza_uso(conducta, true); // actualiza a "activo" la conducta
//    tablaConductas.actualiza_tipo(conducta, idAccion); // actualiza colocando la acción que se pide
    tablaConductas.actualiza_uso_tipo(conducta, true, idAcc);
    System.out.println("AGENTE =" + nombre +" / CONDUCTA = "+ conducta + " / ACCION = "+ idAcc);
    while(!result){ // Espera hasta que se actualice a TRUE la variable "result"
      try{
        wait();
      }
      catch(InterruptedException e){
        System.err.println("Excepción: "+e.toString());
      }
    }
    notifyAll();
    return false;
  }

   public void run(){
     int i, temp=0;
     boolean band;
     for(;;){
       band = false;
       for(i=1; i<=count; i++){ // checa la tabla de conductas para notar conductas activas
         if(tablaConductas.getUso(i)==true){
           temp = tablaConductas.getTipoIndice(i); // en tipo se guarda el id de la Acción
           band = true;
           break; // ha encontrado una conducta activada de mayor prioridad, ya no continúa la búsqueda
         }
       }
       if(band){
         if (temp != idAccion & idAccion != 0) {
           accion.stop();
           initAccion(accion, temp);
         }
         if (idAccion == 0) { // si es la primera vez que se ejecuta una acción (nota: es importante el orden en el que se declaran los if)
           initAccion(accion, temp); // esto es porque, si este bloque estuviera arriba, idAccion cambiaría su valor y entraría a otro if (se previene el que exista el if).
         }
       }
       result= true;
       tablaConductas.actualizaAllUso(false); // limpia a todas las conductas a "false"
       System.out.println("Oráculo "+ nombre + ", ACCION " + idAccion);
     }
   }

   public void initAccion(Accion accion, int temp){
     String var;
     idAccion = temp;
     var = tablaAcciones.getNombreId(idAccion);
     accion = new Accion(grupoHilos, directorio, var, tablaEDGlobal, agente);
     accion.start();
   }


}

*/
