package Lejos;

import Herramientas.TablaSimbolos;

/*     */ class Sensor extends Thread {
/*   6 */   private final int TACTIL = 4;
/*   7 */   private final int LUZ = 5;
/*   8 */   private final int TEMPERATURA = 6;
/*     */   
/*     */ 
/*  11 */   private final int INFRARROJO = 9;
/*  12 */   private final int TACTILIZQ = 10;
/*  13 */   private final int TACTILDER = 11;
/*  14 */   private final int TACTILTRA = 12;
/*  15 */   private final int FOTOCELDAFI = 13;
/*  16 */   private final int FOTOCELDAFD = 14;
/*  17 */   private final int FOTOCELDATI = 15;
/*  18 */   private final int FOTOCELDATD = 16;
/*     */   
/*     */   static final int ATRAS = 7;
/*     */   
/*     */   static final int ATRASDER = 8;
/*     */   
/*     */   static final int ATRASIZQ = 6;
/*     */   static final int DERECHA = 5;
/*     */   static final int FRENTE = 2;
/*     */   static final int FRENTEDER = 1;
/*     */   static final int FRENTEIZQ = 3;
/*     */   static final int IZQUIERDA = 4;
/*     */   Agente agente;
/*     */   private int id;
/*     */   TablaSimbolos tablaSensores;
/*     */   private int tipo;
/*     */   private double valor;
/*     */   
/*     */   public Sensor(Agente agente, String nombre, int tipo, int id, TablaSimbolos tablaSensores)
/*     */   {
/*  38 */     super(nombre);
/*  39 */     this.agente = agente;
/*  40 */     this.tipo = tipo;
/*  41 */     this.id = id;
/*  42 */     this.tablaSensores = tablaSensores;
/*     */   }
			

			
			public int getTipo(){
				return this.tipo;
			}
			
			//public int getID(){
			//	return this.id;
			//}
			
			public double getValor(){
				return this.valor;
			}

/*     */   
/*     */   public void run()
/*     */   {//
/*     */     //for (;;) {
				System.out.println("TIPO: "+tipo);
/*  48 */       switch (tipo) {
				
/*     */       case 4: 
/*  50 */         if (agente.getTactil(2))
/*  51 */           setValor(1.0); else
/*  52 */           setValor(0.0);
/*  53 */         System.out.println("TACTIL " + getName() + "--> " + valor);
/*  54 */         break;
/*     */       case 5: 
/*  56 */         //setValor(agente.getLuz(2));
				//Valor de la lectura del sensor Luz
					setValor(agente.getLuz(2));
/*  57 */         System.out.println("LUZ " + getName() + "--> " + valor);
/*  58 */         break;
///*     */       case 6: 
///*  60 */         setValor(agente.getTemperatura(2));
///*  61 */         break;
///*     */       case 9: 
///*     */         break;
///*     */       case 10: 
///*  65 */         if (agente.getTactil(4))
///*  66 */           setValor(1.0); else {
///*  67 */           setValor(0.0);
///*     */         }
///*  69 */         break;
///*     */       case 11: 
///*  71 */         if (agente.getTactil(5))
///*  72 */           setValor(1.0); else {
///*  73 */           setValor(0.0);
///*     */         }
///*  75 */         break;
///*     */       case 12: 
///*  77 */         if (agente.getTactil(7))
///*  78 */           setValor(1.0); else {
///*  79 */           setValor(0.0);
///*     */         }
///*  81 */         break;
///*     */       case 13: 
///*  83 */         valor = agente.getLuz(3);
///*  84 */         if (valor == 0.0) valor = agente.getLuz(4);
///*  85 */         setValor(valor);
///*  86 */         break;
///*     */       case 14: 
///*  88 */         valor = agente.getLuz(1);
///*  89 */         if (valor == 0.0) valor = agente.getLuz(5);
///*  90 */         setValor(valor);
///*  91 */         break;
///*     */       case 15: 
///*  93 */         valor = agente.getLuz(6);
///*  94 */         if (valor == 0.0) valor = agente.getLuz(4);
///*  95 */         setValor(valor);
///*  96 */         break;
///*     */       case 16: 
///*  98 */         valor = agente.getLuz(8);
///*  99 */         if (valor == 0.0) valor = agente.getLuz(5);
///* 100 */         setValor(valor);
///*     */       }
/*     */       
/*     */     }
/*     */   }
/*     */   
/*     */   private void setValor(double valor)
/*     */   {
/* 108 */     this.valor = valor;
/* 109 */     tablaSensores.actualiza_Id_valor2(id, valor);
/*     */   }
/*     */ }

