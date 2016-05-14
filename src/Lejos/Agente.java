package Lejos;

import Herramientas.TablaSimbolos;

import java.util.Vector;

/*     */ public class Agente
/*     */ {
/*     */   String archivo;
/*     */   Vector conductas;
/*     */   String directorio;
/*     */   ThreadGroup grupoOraculo;
/*     */   int id;
/*     */   String nombre;
/*     */   Oraculo oraculo;
/*     */   Vector sensores;
/*     */   TablaSimbolos tablaAcciones;
/*     */   TablaSimbolos tablaConductas;
/*     */   TablaSimbolos tablaEDGlobal;
/*     */   TablaSimbolos tablaSensores;
/*     */   
/*     */   public Agente(String nombre, String directorio, TablaSimbolos tablaSensores, TablaSimbolos tablaAcciones, TablaSimbolos tablaEDGlobal, int id) {
/*  22 */     this.nombre = nombre;
/*  23 */     this.directorio = directorio;
/*  24 */     this.tablaEDGlobal = tablaEDGlobal;
/*  25 */     this.tablaAcciones = tablaAcciones;
/*  26 */     this.tablaSensores = new TablaSimbolos();
/*  27 */     this.tablaSensores = tablaSensores;
/*  28 */     this.id = id;
/*  30 */     iniciaTablaSensores();
/*  31 */     grupoOraculo = new ThreadGroup("Mi grupo de hilos");
/*     */   }
/*     */   
/*     */   public String getNombreAgente() {
/*  35 */     return nombre;
/*     */   }
/*     */   
/*     */ 
/*     */   public void iniciaTablaSensores()
/*     */   {
/*  41 */     String variable = "";
/*     */     
/*  43 */     int numSens = tablaSensores.count();
/*  44 */     sensores = new Vector(numSens);
/*  45 */     for (int i = 0; i < numSens; i++) {
/*  46 */       variable = tablaSensores.getNombreIndice(i + 1);
/*  47 */       int tipo = tablaSensores.getTipoIndice(i + 1);
/*  48 */       int id = tablaSensores.getIdIndice(i + 1);
/*  49 */       sensores.insertElementAt(new Sensor(this, variable, tipo, id, tablaSensores), i);
/*     */     }
/*     */   }
/*     */   
/*     */   public void setTablaConductas(TablaSimbolos tabla) {
/*  54 */     tablaConductas = new TablaSimbolos();
/*  55 */     tablaConductas = tabla;
/*  56 */     oraculo = new Oraculo(grupoOraculo, nombre, directorio, tablaConductas, tablaAcciones, tablaEDGlobal, this);
/*     */     
/*  58 */     int num = tablaConductas.count();
/*  59 */     int tempNum = num;

/*     */     
/*  61 */     conductas = new Vector(num);
/*  62 */     String variable = "";
/*  63 */     for (int i = 1; i <= num; tempNum--) {
/*  64 */       tablaConductas.actualizaValorIndice(i, tempNum);i++;
/*     */     }
/*     */     
/*  67 */     for (int i = 0; i < num; i++) {
/*  68 */       variable = tablaConductas.getNombreIndice(i + 1);
				//conductas.insertElementAt(new Conducta(this, variable, directorio, oraculo, tablaEDGlobal),i);
/*  69 */       conductas.insertElementAt(new Conducta(this, variable, directorio, oraculo, tablaEDGlobal, tablaConductas), i);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void avanzar()
/*     */   {
/*  78 */   //  suelo.avanzar(id);
/*     */   }
/*     */   
/*     */   public void retroceder() {
/*  82 */     //suelo.retroceder(id);
/*     */   }
/*     */   
/*     */   public void gira_der() {
/*  86 */     //suelo.giraDer(id);
/*     */   }
/*     */   
/*     */   public void gira_izq() {
/*  90 */     //suelo.giraIzq(id);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean getTactil(int orientacion)
/*     */   {
				return true;
/* 106 */     //return suelo.getTactil(orientacion, id);
/*     */   }
/*     */   
/*     */   public int getLuz(int orientacion) {
				return 2;
				//return true;
/* 110 */     //return suelo.getLuz(orientacion, id);
/*     */   }
/*     */   
/*     */   //public int getTemperatura(int orientacion) {
/* 114 */     //return suelo.getTemperatura(orientacion, id);
/*     */   //}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void start()
/*     */   {
/* 124 */     oraculo.start();
/* 125 */     startSensores();
/* 126 */     startConductas();
/*     */   }
/*     */   
/*     */   public void suspend() {
/* 130 */     oraculo.suspend();
/* 131 */     suspendSensores();
/* 132 */     suspendConductas();
/*     */   }
/*     */   
/*     */   public void resume()
/*     */   {
/* 137 */     oraculo.resume();
/* 138 */     resumeSensores();
/* 139 */     resumeConductas();
/*     */   }
/*     */   
/*     */   public void stop()
/*     */   {
/* 144 */     grupoOraculo.stop();
/* 145 */     stopSensores();
/* 146 */     stopConductas();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void startConductas()
/*     */   {
/* 155 */     for (int i = 0; i < conductas.capacity(); i++)
/*     */     {
/* 157 */       Conducta aux = (Conducta)conductas.elementAt(i);
/* 158 */       aux.start();
/*     */     }
/*     */   }
/*     */   
/*     */   private void suspendConductas() {
/* 163 */     for (int i = 0; i < conductas.capacity(); i++)
/*     */     {
/* 165 */       Conducta aux = (Conducta)conductas.elementAt(i);
/* 166 */       aux.suspend();
/*     */     }
/*     */   }
/*     */   
/*     */   private void resumeConductas() {
/* 171 */     for (int i = 0; i < conductas.capacity(); i++)
/*     */     {
/* 173 */       Conducta aux = (Conducta)conductas.elementAt(i);
/* 174 */       aux.resume();
/*     */     }
/*     */   }
/*     */   
/*     */   private void stopConductas() {
/* 179 */     for (int i = 0; i < conductas.capacity(); i++)
/*     */     {
/* 181 */       Conducta aux = (Conducta)conductas.elementAt(i);
/* 182 */       aux.stop();
/*     */     }
/* 184 */     tablaConductas.imprimir();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void startSensores()
/*     */   {
/* 193 */     for (int i = 0; i < sensores.capacity(); i++)
/*     */     {
/* 195 */       Sensor aux = (Sensor)sensores.elementAt(i);
/* 196 */       aux.start();
/*     */     }
/*     */   }
/*     */   
/*     */   private void suspendSensores() {
/* 201 */     for (int i = 0; i < sensores.capacity(); i++)
/*     */     {
/* 203 */       Sensor aux = (Sensor)sensores.elementAt(i);
/* 204 */       aux.suspend();
/*     */     }
/*     */   }
/*     */   
/*     */   private void resumeSensores() {
/* 209 */     for (int i = 0; i < sensores.capacity(); i++)
/*     */     {
/* 211 */       Sensor aux = (Sensor)sensores.elementAt(i);
/* 212 */       aux.resume();
/*     */     }
/*     */   }
/*     */   
/*     */   private void stopSensores() {
/* 217 */     for (int i = 0; i < sensores.capacity(); i++)
/*     */     {
/* 219 */       Sensor aux = (Sensor)sensores.elementAt(i);
/* 220 */       aux.stop();
/*     */     }
/* 222 */     tablaSensores.imprimir();
/*     */   }
/*     */ }

