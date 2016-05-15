package Lejos;

import java.io.*;


/* ********************
La clase ArchivoObjeto se encarga de manejar la lectura de los archivos
que contienen el código objeto.
Es el encargado de abrir el archivo, leer los caracteres del
archivo, cerrar el archivo.
******************** */
/*     */ class ArchivoObjeto
/*     */ {
/*     */   private int codigo;
/*     */   private int i;
/*     */   private BufferedReader in;
/*     */   private int j;
/*     */   private char[] myBuff;
/*     */   private String ruta;
/*     */   private int tamanio;
/*     */   
/*     */   public ArchivoObjeto(String ruta)
/*     */   {
/*  24 */     tamanio = 400;
/*  25 */     i = 0;
/*  26 */     j = 0;
/*  27 */     this.ruta = ruta;
/*  28 */     myBuff = new char[tamanio];
/*     */     try {
/*  30 */       in = new BufferedReader(new InputStreamReader(new FileInputStream(ruta)));
/*     */       
/*     */ 
/*  33 */       lee_archivo();
/*     */     }
/*     */     catch (IOException e) {}
/*     */   }
/*     */   
/*     */ 
/*     */   public int getIndice()
/*     */   {
/*  41 */     return j;
/*     */   }
/*     */   
/*     */   private void abrir_archivo() {
/*     */     try {
/*  46 */       in = new BufferedReader(new InputStreamReader(new FileInputStream(ruta)));
/*     */       
/*     */ 
/*  49 */       lee_archivo();
/*     */     }
/*     */     catch (IOException e) {}
/*     */   }
/*     */   
/*     */ 
/*     */   public void setCodigoIndice(int indice)
/*     */   {
/*  57 */     if (i - indice >= 0) {
/*  58 */       i -= indice;
/*     */     }
/*     */     else {
/*  61 */       int temp = j - indice;
/*  62 */       i = (j = 0);
/*  63 */       cierra_archivo();
/*  64 */       abrir_archivo();
/*  65 */       getIndex(temp);
/*     */     }
/*     */   }
/*     */   
/*     */   private void lee_archivo()
/*     */   {
/*  71 */     for (int y = 0; y < myBuff.length; y++)
/*  72 */       myBuff[y] = '\000';
/*     */     try {
/*  74 */       in.read(myBuff);
/*     */     }
/*     */     catch (EOFException eof) {
/*  77 */       cierra_archivo();
/*     */     }
/*     */     catch (IOException e) {}
/*     */   }
/*     */   
/*     */ 
/*     */   public int getIndex(int indice)
/*     */   {
/*  85 */     int valor = 0;
/*  86 */     for (int i = 0; i < indice; i++) {
/*  87 */       valor = getCodigo();
/*     */     }
/*  89 */     return valor;
/*     */   }
/*     */   
/*     */   public int getCodigo() {
/*  93 */     int lectura = 0;
/*     */     do {
/*  95 */       if (i < myBuff.length) {
/*  96 */         if (myBuff[i] != 0) {
/*  97 */           codigo = myBuff[i];
/*  98 */           i += 1;
/*  99 */           j += 1;
/* 100 */           lectura = 1;
/*     */         }
/*     */         else {
/* 103 */           cierra_archivo();
/* 104 */           lectura = 2;
/* 105 */           codigo = -1;
/*     */         }
/*     */       }
/*     */       else {
/* 109 */         lee_archivo();
/* 110 */         i = 0;
/*     */       }
/* 112 */     } while (lectura == 0);
/* 113 */     return codigo;
/*     */   }
/*     */   
/*     */   public int getOnlyCodigo() {
/* 117 */     return codigo;
/*     */   }
/*     */   
/*     */   public String getVariable() {
/* 121 */     String variable = "";
/* 122 */     int caracter = getCodigo();
/* 123 */     if (caracter == 34) {
/* 124 */       caracter = getCodigo();
/*     */       do {
/* 126 */         variable = variable + (char)caracter;
/* 127 */         caracter = getCodigo();
/* 128 */       } while (caracter != 34);
/*     */     }
/* 130 */     return variable;
/*     */   }
/*     */   
/*     */   public double getNumero() {
/* 134 */     String variable = "";
/* 135 */     int caracter = getCodigo();
/* 136 */     if (caracter == 35) {
/* 137 */       caracter = getCodigo();
/*     */       do {
/* 139 */         variable = variable + (char)caracter;
/* 140 */         caracter = getCodigo();
/* 141 */       } while (caracter != 35);
/*     */     }
/* 143 */     return Double.parseDouble(variable);
/*     */   }
/*     */   
/*     */   public int getNumeroInt() {
/* 147 */     String variable = "";
/* 148 */     int caracter = getCodigo();
/* 149 */     if (caracter == 35) {
/* 150 */       caracter = getCodigo();
/*     */       do {
/* 152 */         variable = variable + (char)caracter;
/* 153 */         caracter = getCodigo();
/* 154 */       } while (caracter != 35);
/*     */     }
/* 156 */     return Integer.parseInt(variable);
/*     */   }
/*     */   
/*     */   public double getNumeroOp() {
/* 160 */     String variable = "";
/* 161 */     int caracter = getCodigo();
/*     */     do {
/* 163 */       variable = variable + (char)caracter;
/* 164 */       caracter = getCodigo();
/* 165 */     } while (caracter != 35);
/* 166 */     return Double.parseDouble(variable);
/*     */   }
/*     */   
/*     */   public void cierra_archivo()
/*     */   {
/*     */     try {
/* 172 */       in.close();
/*     */     }
/*     */     catch (IOException e) {}
/*     */   }
/*     */ }
