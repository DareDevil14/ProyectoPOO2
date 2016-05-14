package MaquinaVirtual;

import Herramientas.TablaSimbolos;
import java.util.Vector;

public class Agente{

  public String nombre;
  String directorio;
  String archivo;
  public TablaSimbolos tablaSensores;
  public TablaSimbolos tablaConductas;
  public TablaSimbolos tablaAcciones;
  public TablaSimbolos tablaEDGlobal;
  public Vector sensores, conductas;
  ThreadGroup grupoOraculo;
  Oraculo oraculo;
  SueloCanvas suelo;
  public int id;

  public Agente(String nombre, String directorio, TablaSimbolos tablaSensores, TablaSimbolos tablaAcciones, TablaSimbolos tablaEDGlobal, int id, SueloCanvas suelo){
    this.nombre = nombre;
    this.directorio = directorio;
    this.tablaEDGlobal = tablaEDGlobal;
    this.tablaAcciones = tablaAcciones;
    this.tablaSensores = new Herramientas.TablaSimbolos();
    this.tablaSensores = tablaSensores;
    this.id = id;
    this.suelo = suelo;
    iniciaTablaSensores();
    grupoOraculo = new ThreadGroup("Mi grupo de hilos");
  }

  public String getNombreAgente(){
    return nombre;
  }

  // En la siguiente función se inicia la Tabla de Sensores
  public void iniciaTablaSensores(){
    int i;
    String variable="";
    int tipo, id, numSens;
    numSens = tablaSensores.count();
    sensores = new Vector(numSens);
    for(i=0;i<numSens;i++){
      variable=tablaSensores.getNombreIndice(i+1);
      tipo = tablaSensores.getTipoIndice(i+1);
      id = tablaSensores.getIdIndice(i+1);
      sensores.insertElementAt(new Sensor(this, variable, tipo, id, tablaSensores),i);
    }
  }

  public void setTablaConductas(TablaSimbolos tabla){
    tablaConductas = new TablaSimbolos();
    tablaConductas = tabla; // copia la tabla de conductas
    oraculo = new Oraculo(grupoOraculo, nombre, directorio, tablaConductas, tablaAcciones, tablaEDGlobal, this);
    // actualiza, en el parámetro "valor", la ponderación de la conducta
    int num= tablaConductas.count();
    int tempNum = num;
    int i;
    conductas = new Vector(num);
    String variable="";
    for(i=1; i<=num; i++, tempNum--){
      tablaConductas.actualizaValorIndice(i,tempNum);
    }
    // Crea los hilos de las conductas
    for(i=0;i<num;i++){
      variable=tablaConductas.getNombreIndice(i+1);
      conductas.insertElementAt(new Conducta(this, variable, directorio, oraculo, tablaEDGlobal),i);
    }
  }

  /* Las siguientes funciones tienen como objetivo la manipulación del agente en el suelo;
   * es decir, se ocupan de mover el triángulo de acuerdo a las requisiciones del thread Accion
   */

  public void avanzar(){
    suelo.avanzar(id);
  }

  public void retroceder(){
    suelo.retroceder(id);
  }

  public void gira_der(){
    suelo.giraDer(id);
  }

  public void gira_izq(){
    suelo.giraIzq(id);
  }

  /*
   * Operaciones para conocer el estado del agente con respecto al suelo
  */

/*
     static final int IZQUIERDA = 4; //tactil
   static final int DERECHA = 5;  //tactil
   static final int ATRAS = 7;  //tactil
   static final int FRENTE = 2;

  */

  public boolean getTactil(int orientacion){
    return suelo.getTactil(orientacion,id);
  }

   public int getLuz(int orientacion){
     return suelo.getLuz(orientacion,id);
   }

   public int getTemperatura(int orientacion){
     return suelo.getTemperatura(orientacion,id);
   }

   /*
    * Operaciones sobre los hilos de las Conductas y Sensores
    * Start/Suspend/Resume/Stop
    * Es importante el orden en el que son llamados los hilos.
   */

   public void start(){
     oraculo.start();
     startSensores();
     startConductas();
   }

   public void suspend(){
     oraculo.suspend();
     suspendSensores();
     suspendConductas();
   }


   public void resume(){
     oraculo.resume();
     resumeSensores();
     resumeConductas();
   }

   public void stop(){
     //oraculo.stop();
     grupoOraculo.stop();
     stopSensores();
     stopConductas();
   }

   /*
    * Operaciones sobre los hilos de las Conductas
    * Start/Suspend/Resume/Stop
   */

   private void startConductas(){
    for (int i = 0; i < conductas.capacity(); i++) {
       Conducta aux;
       aux = (Conducta) conductas.elementAt(i);
       aux.start();
    }
   }

   private void suspendConductas(){
     for (int i = 0; i < conductas.capacity(); i++) {
       Conducta aux;
       aux = (Conducta) conductas.elementAt(i);
       aux.suspend();
     }
   }

   private void resumeConductas(){
     for (int i = 0; i < conductas.capacity(); i++) {
       Conducta aux;
       aux = (Conducta) conductas.elementAt(i);
       aux.resume();
     }
   }

   private void stopConductas(){
     for (int i = 0; i < conductas.capacity(); i++) {
       Conducta aux;
       aux = (Conducta) conductas.elementAt(i);
       aux.stop();
     }
     tablaConductas.imprimir();
   }

/*
 * Operaciones sobre los hilos de Sensores
 * Start/Suspend/Resume/Stop
*/

   private void startSensores(){
     for (int i = 0; i < sensores.capacity(); i++) {
       Sensor aux;
       aux = (Sensor) sensores.elementAt(i);
       aux.start();
     }
   }

   private void suspendSensores(){
     for(int i=0; i<sensores.capacity(); i++){
         Sensor aux;
         aux = (Sensor) sensores.elementAt(i);
         aux.suspend();
       }
   }

   private void resumeSensores(){
     for(int i=0; i<sensores.capacity(); i++){
         Sensor aux;
         aux = (Sensor) sensores.elementAt(i);
         aux.resume();
       }
   }

   private void stopSensores(){
     for(int i=0; i<sensores.capacity(); i++){
         Sensor aux;
         aux = (Sensor) sensores.elementAt(i);
         aux.stop();
       }
       tablaSensores.imprimir();
   }


}

