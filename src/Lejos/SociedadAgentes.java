package Lejos;

import Herramientas.TablaSimbolos;


import java.util.Vector;

/*     */ public class SociedadAgentes
/*     */ {
/*     */   //Agente agente;
			Vector agentes;
/*     */   String archivo;
/*     */   String directorio;
/*     */   ArchivoObjeto in;
/*     */   int numAge;
/*     */   TablaSimbolos tablaAcciones;
/*     */   TablaSimbolos tablaEDGlobal;
/*     */   TablaSimbolos tablaSensores;
/*     */   
/*     */   public SociedadAgentes(String directorio, String archivo)
/*     */   {
/*  19 */     this.directorio = directorio;
/*  20 */     this.archivo = archivo;
/*  21 */     numAge = 0;
/*  22 */     tablaSensores = new TablaSimbolos();
/*  23 */     tablaEDGlobal = new TablaSimbolos();
/*  24 */     tablaAcciones = new TablaSimbolos();
/*     */     
/*  27 */     in = new ArchivoObjeto(directorio + archivo  + ".age" + ".Sociedad.Age2000.obj");
/*  28 */     initTablaSensores();
/*  29 */     initTablaEDGlobal();
/*  30 */     initTablaAcciones();
/*  31 */     initAgentes();
/*     */   }
/*     */   
/*     */   public int countAgentes() {
/*  35 */     return numAge;
/*     */   }
/*     */   
/*     */   private void initAgentes() {
/*  39 */     int i = -1;
/*  40 */     String nombreAge = "";
			String nombreCond = "";
/*  41 */     numAge = in.getCodigo();
/*  42 */     agentes = new Vector(numAge);
/*  43 */     int caracter = in.getCodigo();
/*  44 */     if (caracter == 92) {
/*     */       do {
/*  46 */         i++;
/*  47 */         nombreAge = in.getVariable();
/*  48 */         Agente temp = new Agente(nombreAge, directorio, tablaSensores, tablaAcciones, tablaEDGlobal, i);
/*  49 */         agentes.insertElementAt(temp, i);
/*  50 */         TablaSimbolos tablaConductas = new TablaSimbolos();
/*  51 */         caracter = in.getCodigo();
/*  52 */         if (caracter == 94) {
/*     */           do {
/*  54 */             nombreCond = in.getVariable();
/*  55 */             tablaConductas.insertar(nombreCond, 0, 0);
/*  56 */             caracter = in.getCodigo();
/*  57 */           } while (((caracter != -1 ? 1 : 0) & (caracter == 94 ? 1 : 0)) != 0);
/*     */         }
/*  59 */         temp.setTablaConductas(tablaConductas);
/*  60 */       } while (((caracter != -1 ? 1 : 0) & (caracter == 92 ? 1 : 0)) != 0);
/*     */     }
/*     */   }
/*     */   
/*     */   private void initTablaSensores() {
/*  66 */     String variable = "";
/*  67 */     int id = 0;
/*  68 */     int tipo = 0;
/*  69 */     int caracter = in.getCodigo();
/*  70 */     if (caracter == 63) {
/*  71 */       caracter = in.getCodigo();
/*     */       do {
/*  73 */         if (caracter == 38) {
/*  74 */           variable = in.getVariable();
/*  75 */           id = in.getCodigo();
/*  76 */           tipo = in.getCodigo();
/*  77 */           tablaSensores.insertar(variable, tipo, 0, 0.0D, id);
/*     */         }
/*  79 */         caracter = in.getCodigo();
/*  80 */       } while (((caracter != -1 ? 1 : 0) & (caracter != 63 ? 1 : 0)) != 0);
/*  81 */       tablaSensores.imprimir();
/*  82 */       in.getCodigo();
/*     */     }
/*     */   }
/*     */   
/*     */   private void initTablaEDGlobal() {
/*  87 */     String variable = "";
/*  88 */     int id = 0;int tipo = 0;
/*  89 */     double valor = 0.0D;
/*     */     
/*  91 */     int caracter = in.getOnlyCodigo();
/*  92 */     if (caracter == 64) {
/*  93 */       caracter = in.getCodigo();
/*     */       do {
/*  95 */         if (caracter == 36) {
/*  96 */           variable = in.getVariable();
/*  97 */           id = in.getCodigo();
/*  98 */           tipo = in.getCodigo();
/*  99 */           valor = in.getNumero();
/* 100 */           tablaEDGlobal.insertar(variable, tipo, 0, valor, id);
/*     */         }
/* 102 */         caracter = in.getCodigo();
/* 103 */       } while (((caracter != -1 ? 1 : 0) & (caracter != 64 ? 1 : 0)) != 0);
/* 104 */       tablaEDGlobal.imprimir();
/* 105 */       in.getCodigo();
/*     */     }
/*     */   }
/*     */   
/*     */   private void initTablaAcciones() {
/* 110 */     String variable = "";
/* 111 */     int id = 0;
/*     */     
/* 113 */     int caracter = in.getOnlyCodigo();
/* 114 */     if (caracter == 126) {
/* 115 */       caracter = in.getCodigo();
/*     */       do {
/* 117 */         if (caracter == 42) {
/* 118 */           variable = in.getVariable();
/* 119 */           id = in.getCodigo();
/* 120 */           tablaAcciones.insertar(variable, 0, 0, 0.0D, id);
/*     */         }
/* 122 */         caracter = in.getCodigo();
/* 123 */       } while (((caracter != -1 ? 1 : 0) & (caracter == 42 ? 1 : 0)) != 0);
/* 124 */       tablaAcciones.imprimir();
/*     */     }
/*     */   }
/*     */   
/*     */   public String getNombreAgente(int i)
/*     */   {
/* 130 */     Agente aux = (Agente)agentes.elementAt(i);
/* 131 */     return aux.getNombreAgente();
/*     */   }
/*     */   
/*     */   public void startAge() {
/* 135 */     for (int i = 0; i < agentes.capacity(); i++)
/*     */     {
/* 137 */       Agente aux = (Agente)agentes.elementAt(i);
/* 138 */       aux.start();
/*     */     }
/*     */   }
/*     */   
/*     */   public void suspendAgentes() {
/* 143 */     for (int i = 0; i < agentes.capacity(); i++)
/*     */     {
/* 145 */       Agente aux = (Agente)agentes.elementAt(i);
/* 146 */       aux.suspend();
/*     */     }
/*     */   }
/*     */   
/*     */   public void resumeAgentes() {
/* 151 */     for (int i = 0; i < agentes.capacity(); i++)
/*     */     {
/* 153 */       Agente aux = (Agente)agentes.elementAt(i);
/* 154 */       aux.resume();
/*     */     }
/*     */   }
/*     */   
/*     */   public void stopAgentes() {
/* 159 */     for (int i = 0; i < agentes.capacity(); i++)
/*     */     {
/* 161 */       Agente aux = (Agente)agentes.elementAt(i);
/* 162 */       aux.stop();
/*     */     }
/* 164 */     tablaEDGlobal.imprimir();
/*     */   }
/*     */ }
