/*     */ package Herramientas;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ 
/*     */ public class TablaSimbolos
/*     */ {
/*     */   private NodoSimbolo hoja;
/*     */   private int id;
/*     */   private NodoSimbolo raiz;
/*     */   private NodoSimbolo temp;
/*     */   
/*     */   public TablaSimbolos()
/*     */   {
/*  15 */     raiz = (hoja = null);
/*  16 */     id = 1;
/*     */   }
/*     */   
/*     */   public boolean vacio() {
/*  20 */     System.out.println("Entro al vacio");
/*  21 */     if (raiz == null) {
/*  22 */       return true;
/*     */     }
/*  24 */     return false;
/*     */   }
/*     */   
/*     */   public void insertar(String n, int t, int v) {
/*  28 */     if (raiz == null) {
/*  29 */       raiz = (hoja = new NodoSimbolo(n, t, v, id));
/*     */     } else
/*  31 */       hoja = (hoja.next = new NodoSimbolo(n, t, v, id));
/*  32 */     id += 1;
/*     */   }
/*     */   
/*     */   public void insertar(String n, int t, int v, double v2) {
/*  36 */     if (raiz == null) {
/*  37 */       raiz = (hoja = new NodoSimbolo(n, t, v, v2, id));
/*     */     } else
/*  39 */       hoja = (hoja.next = new NodoSimbolo(n, t, v, v2, id));
/*  40 */     id += 1;
/*     */   }
/*     */   
/*     */   public void insertar(String n, int t, int v, double v2, int ccid) {
/*  44 */     if (raiz == null) {
/*  45 */       raiz = (hoja = new NodoSimbolo(n, t, v, v2, ccid));
/*     */     } else
/*  47 */       hoja = (hoja.next = new NodoSimbolo(n, t, v, v2, ccid));
/*     */   }
/*     */   
/*     */   public void eliminar(String n) {
/*  51 */     temp = null;
/*  52 */     nodo_eliminar(raiz, n);
/*     */   }
/*     */   
/*     */   public void nodo_eliminar(NodoSimbolo nodo, String dato) {
/*  56 */     if (nodo == null) {
/*  57 */       return;
/*     */     }
/*  59 */     if (dato.compareTo(nodo.nombre) == 0) {
/*  60 */       if (temp == null) {
/*  61 */         raiz = temp = nodo.next;
/*     */       } else {
/*  63 */         if (nodo.next == null)
/*  64 */           hoja = temp;
/*  65 */         temp.next = nodo.next;
/*     */       }
/*  67 */       nodo = null;
/*  68 */       return;
/*     */     }
/*     */     
/*  71 */     temp = nodo;
/*  72 */     nodo_eliminar(nodo.next, dato);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean buscar(String dato)
/*     */   {
/*  80 */     return buscar_nodo(raiz, dato);
/*     */   }
/*     */   
/*     */   private boolean buscar_nodo(NodoSimbolo nodo, String dato) {
/*  84 */     if (nodo == null) {
/*  85 */       return false;
/*     */     }
/*  87 */     if (dato.compareTo(nodo.nombre) == 0) {
/*  88 */       return true;
/*     */     }
/*  90 */     return buscar_nodo(nodo.next, dato);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int count()
/*     */   {
/*  97 */     int i = 0;
/*  98 */     return buscar_count(raiz, i);
/*     */   }
/*     */   
/*     */   private int buscar_count(NodoSimbolo nodo, int i) {
/* 102 */     if (nodo == null) {
/* 103 */       return i;
/*     */     }
/* 105 */     i++;
/* 106 */     return buscar_count(nodo.next, i);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean getUso(int x)
/*     */   {
/* 113 */     NodoSimbolo temp = raiz;
/* 114 */     while (x > 1) {
/* 115 */       temp = temp.next;
/* 116 */       x--;
/*     */     }
/* 118 */     return temp.uso;
/*     */   }
/*     */   
/*     */ 
/*     */   public int getUsoTipo(int x, int tipo)
/*     */   {
/* 124 */     NodoSimbolo temp = raiz;
/* 125 */     while (x > 1) {
/* 126 */       temp = temp.next;
/* 127 */       x--;
/*     */     }
/* 129 */     if (temp.tipo == tipo) {
/* 130 */       if (temp.uso) return 1;
/* 131 */       return 0;
/*     */     }
/*     */     
/* 134 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */   public String getNombre(int x)
/*     */   {
/* 140 */     NodoSimbolo temp = raiz;
/* 141 */     while (x > 1) {
/* 142 */       temp = temp.next;
/* 143 */       x--;
/*     */     }
/* 145 */     return temp.nombre;
/*     */   }
/*     */   
/*     */ 
/*     */   public int buscar_var_tipo(String dato)
/*     */   {
/* 151 */     return buscar_nodo_var_tipo(raiz, dato);
/*     */   }
/*     */   
/*     */   private int buscar_nodo_var_tipo(NodoSimbolo nodo, String dato) {
/* 155 */     if (nodo == null) {
/* 156 */       return -1;
/*     */     }
/* 158 */     if (dato.compareTo(nodo.nombre) == 0) {
/* 159 */       return nodo.tipo;
/*     */     }
/* 161 */     return buscar_nodo_var_tipo(nodo.next, dato);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getCodigoSensores()
/*     */   {
/* 171 */     NodoSimbolo temp = raiz;
/* 172 */     String cadena = "";
/* 173 */     while (temp != null) {
/* 174 */       if ((((temp.tipo == 4 ? 1 : 0) | (temp.tipo == 5 ? 1 : 0) | (temp.tipo == 6 ? 1 : 0) 
			| (temp.tipo == 9 ? 1 : 0) | (temp.tipo == 10 ? 1 : 0) | (temp.tipo == 11 ? 1 : 0) 
			| (temp.tipo == 12 ? 1 : 0) | (temp.tipo == 13 ? 1 : 0) | (temp.tipo == 14 ? 1 : 0) 
			| (temp.tipo == 15 ? 1 : 0) | (temp.tipo == 16 ? 1 : 0)) & (temp.uso == true ? 1 : 0)) != 0)
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/* 179 */         cadena = cadena + '&' + '"' + temp.nombre + '"' + (char)temp.id + (char)temp.tipo;
/*     */       }
/*     */       
/* 182 */       temp = temp.next;
/*     */     }
/* 184 */     return cadena;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getCodigoAcciones()
/*     */   {
/* 193 */     NodoSimbolo temp = raiz;
/* 194 */     String cadena = "";
/* 195 */     while (temp != null) {
/* 196 */       if (((temp.tipo == 3 ? 1 : 0) & (temp.uso == true ? 1 : 0)) != 0)
/*     */       {
/*     */ 
/* 199 */         cadena = cadena + '*' + '"' + temp.nombre + '"' + (char)temp.id;
/*     */       }
/*     */       
/* 202 */       temp = temp.next;
/*     */     }
/* 204 */     return cadena;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getCodigoEnteroDecimal()
/*     */   {
/* 214 */     NodoSimbolo temp = raiz;
/* 215 */     String cadena = "";
/* 216 */     while (temp != null)
/*     */     {
/*     */ 
/* 219 */       if (((temp.tipo == 7 ? 1 : 0) & (temp.uso == true ? 1 : 0)) != 0) {
/* 220 */         cadena = cadena + '$' + '"' + temp.nombre + '"' + (char)temp.id + (char)temp.tipo + '#' + temp.valor + '#';
/*     */       }
/* 222 */       if (((temp.tipo == 8 ? 1 : 0) & (temp.uso == true ? 1 : 0)) != 0) {
/* 223 */         cadena = cadena + '$' + '"' + temp.nombre + '"' + (char)temp.id + (char)temp.tipo + '#' + temp.valor2 + '#';
/*     */       }
/* 225 */       temp = temp.next;
/*     */     }
/* 227 */     return cadena;
/*     */   }
/*     */   
/*     */ 
/*     */   public String buscar_nombre(int tipo)
/*     */   {
/* 233 */     return buscar_nodo_nombre(raiz, tipo);
/*     */   }
/*     */   
/*     */   private String buscar_nodo_nombre(NodoSimbolo nodo, int tipo) {
/* 237 */     if (nodo == null) {
/* 238 */       return "";
/*     */     }
/* 240 */     if (nodo.tipo == tipo) {
/* 241 */       return nodo.nombre;
/*     */     }
/* 243 */     return buscar_nodo_nombre(nodo.next, tipo);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean buscar_tipo(int tipo)
/*     */   {
/* 250 */     return buscar_nodo_tipo(raiz, tipo);
/*     */   }
/*     */   
/*     */   private boolean buscar_nodo_tipo(NodoSimbolo nodo, int tipo) {
/* 254 */     if (nodo == null) {
/* 255 */       return false;
/*     */     }
/* 257 */     if (nodo.tipo == tipo) {
/* 258 */       return true;
/*     */     }
/* 260 */     return buscar_nodo_tipo(nodo.next, tipo);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void eliminar_tipo(int tipo)
/*     */   {
/* 268 */     eliminar_nodo_tipo(raiz, tipo);
/*     */   }
/*     */   
/*     */   private void eliminar_nodo_tipo(NodoSimbolo nodo, int tipo) {
/* 272 */     if (nodo == null) { return;
/*     */     }
/* 274 */     if (nodo.tipo == tipo) {
/* 275 */       if (temp == null) {
/* 276 */         raiz = temp = nodo.next;
/*     */       }
/*     */       else {
/* 279 */         if (nodo.next == null)
/* 280 */           hoja = temp;
/* 281 */         temp.next = nodo.next;
/*     */       }
/* 283 */       nodo = null;
/* 284 */       eliminar_nodo_tipo(temp.next, tipo);
/*     */     }
/*     */     else {
/* 287 */       temp = nodo;
/* 288 */       eliminar_nodo_tipo(nodo.next, tipo);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean buscar_tipo_valor(int tipo, int valor)
/*     */   {
/* 296 */     return buscar_nodo_tipo_valor(raiz, tipo, valor);
/*     */   }
/*     */   
/*     */   private boolean buscar_nodo_tipo_valor(NodoSimbolo nodo, int tipo, int valor) {
/* 300 */     if (nodo == null) {
/* 301 */       return false;
/*     */     }
/* 303 */     if (((nodo.tipo == tipo ? 1 : 0) & (nodo.valor == valor ? 1 : 0)) != 0) {
/* 304 */       return true;
/*     */     }
/* 306 */     return buscar_nodo_tipo_valor(nodo.next, tipo, valor);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int buscar_var_valor(String dato)
/*     */   {
/* 313 */     return buscar_nodo_var_valor(raiz, dato);
/*     */   }
/*     */   
/*     */   private int buscar_nodo_var_valor(NodoSimbolo nodo, String dato) {
/* 317 */     if (nodo == null) {
/* 318 */       return -1;
/*     */     }
/* 320 */     if (dato.compareTo(nodo.nombre) == 0) {
/* 321 */       return nodo.valor;
/*     */     }
/* 323 */     return buscar_nodo_var_valor(nodo.next, dato);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double buscar_var_valor2(String dato)
/*     */   {
/* 330 */     return buscar_nodo_var_valor2(raiz, dato);
/*     */   }
/*     */   
/*     */   private double buscar_nodo_var_valor2(NodoSimbolo nodo, String dato) {
/* 334 */     if (nodo == null) {
/* 335 */       return -1.0D;
/*     */     }
/* 337 */     if (dato.compareTo(nodo.nombre) == 0) {
/* 338 */       return nodo.valor2;
/*     */     }
/* 340 */     return buscar_nodo_var_valor2(nodo.next, dato);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int buscar_var_id(String dato)
/*     */   {
/* 348 */     return buscar_nodo_var_id(raiz, dato);
/*     */   }
/*     */   
/*     */   private int buscar_nodo_var_id(NodoSimbolo nodo, String dato) {
/* 352 */     if (nodo == null) {
/* 353 */       return -1;
/*     */     }
/* 355 */     if (dato.compareTo(nodo.nombre) == 0) {
/* 356 */       return nodo.id;
/*     */     }
/* 358 */     return buscar_nodo_var_id(nodo.next, dato);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void actualiza_tipo(String dato, int tipo)
/*     */   {
/* 366 */     actualiza_nodo_var_tipo(raiz, dato, tipo);
/*     */   }
/*     */   
/*     */   private void actualiza_nodo_var_tipo(NodoSimbolo nodo, String dato, int tipo) {
/* 370 */     if (nodo != null) {
/* 371 */       if (dato.compareTo(nodo.nombre) == 0) {
/* 372 */         nodo.tipo = tipo;
/*     */       }
/*     */       else {
/* 375 */         actualiza_nodo_var_tipo(nodo.next, dato, tipo);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void actualiza_Id_valor2(int id, double valor2)
/*     */   {
/* 382 */     actualiza_nodo_var_Id_var2(raiz, id, valor2);
/*     */   }
/*     */   
/*     */   private void actualiza_nodo_var_Id_var2(NodoSimbolo nodo, int id, double valor2) {
/* 386 */     if (nodo != null) {
/* 387 */       if (nodo.id == id) {
/* 388 */         nodo.valor2 = valor2;
/*     */       }
/*     */       else {
/* 391 */         actualiza_nodo_var_Id_var2(nodo.next, id, valor2);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void actualiza_valor(String dato, int valor)
/*     */   {
/* 399 */     actualiza_nodo_var_valor(raiz, dato, valor);
/*     */   }
/*     */   
/*     */   private void actualiza_nodo_var_valor(NodoSimbolo nodo, String dato, int valor) {
/* 403 */     if (nodo != null) {
/* 404 */       if (dato.compareTo(nodo.nombre) == 0) {
/* 405 */         nodo.valor = valor;
/*     */       } else {
/* 407 */         actualiza_nodo_var_valor(nodo.next, dato, valor);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void actualiza_valor2(String dato, double valor)
/*     */   {
/* 414 */     actualiza_nodo_var_valor2(raiz, dato, valor);
/*     */   }
/*     */   
/*     */   private void actualiza_nodo_var_valor2(NodoSimbolo nodo, String dato, double valor2) {
/* 418 */     if (nodo != null) {
/* 419 */       if (dato.compareTo(nodo.nombre) == 0) {
/* 420 */         nodo.valor2 = valor2;
/*     */       } else {
/* 422 */         actualiza_nodo_var_valor2(nodo.next, dato, valor2);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public int actualiza_uso(String dato, boolean uso)
/*     */   {
/* 429 */     return actualiza_nodo_var_uso(raiz, dato, uso);
/*     */   }
/*     */   
/*     */   private int actualiza_nodo_var_uso(NodoSimbolo nodo, String dato, boolean uso) {
/* 433 */     if (nodo != null) {
/* 434 */       if (dato.compareTo(nodo.nombre) == 0) {
/* 435 */         if (nodo.uso == uso)
/* 436 */           return 2;
/* 437 */         nodo.uso = uso;
				  return 1;
/*     */       }
/*     */       
/* 440 */       return actualiza_nodo_var_uso(nodo.next, dato, uso);
/*     */     }
/*     */     
/* 443 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public int actualiza_uso_tipo(String dato, boolean uso, int tipo)
/*     */   {
/* 449 */     return actualiza_nodo_var_uso_tipo(raiz, dato, uso, tipo);
/*     */   }
/*     */   
/*     */   private int actualiza_nodo_var_uso_tipo(NodoSimbolo nodo, String dato, boolean uso, int tipo) {
/* 453 */     if (nodo != null) {
/* 454 */       if (dato.compareTo(nodo.nombre) == 0) {
/* 455 */         if (nodo.uso == uso) {
/* 456 */           return 2;
/*     */         }
/* 458 */         nodo.uso = uso;
/* 459 */         nodo.tipo = tipo;
/* 460 */         return 1;
/*     */       }
/*     */       
/* 463 */       return actualiza_nodo_var_uso_tipo(nodo.next, dato, uso, tipo);
/*     */     }
/*     */     
/* 466 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getIndex(String dato)
/*     */   {
/* 473 */     NodoSimbolo nodo = raiz;
/* 474 */     int i = 1;
/* 475 */     while (nodo != null) {
/* 476 */       if (dato.compareTo(nodo.nombre) == 0) { return i;
/*     */       }
/* 478 */       i++;
/* 479 */       nodo = nodo.next;
/*     */     }
/*     */     
/* 482 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getNombreIndice(int indice)
/*     */   {
/* 491 */     NodoSimbolo temp = raiz;
/* 492 */     for (int i = 1; ((temp != null ? 1 : 0) & (i < indice ? 1 : 0)) != 0; i++) {
/* 493 */       temp = temp.next;
/*     */     }
/* 495 */     return temp.nombre;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getTipoIndice(int indice)
/*     */   {
/* 502 */     NodoSimbolo temp = raiz;
/* 503 */     for (int i = 1; ((temp != null ? 1 : 0) & (i < indice ? 1 : 0)) != 0; i++) {
/* 504 */       temp = temp.next;
/*     */     }
/* 506 */     return temp.tipo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getIdIndice(int indice)
/*     */   {
/* 513 */     NodoSimbolo temp = raiz;
/* 514 */     for (int i = 1; ((temp != null ? 1 : 0) & (i < indice ? 1 : 0)) != 0; i++) {
/* 515 */       temp = temp.next;
/*     */     }
/* 517 */     return temp.id;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void actualizaValorIndice(int indice, int valor)
/*     */   {
/* 524 */     NodoSimbolo temp = raiz;
/* 525 */     for (int i = 1; ((temp != null ? 1 : 0) & (i < indice ? 1 : 0)) != 0; i++) {
/* 526 */       temp = temp.next;
/*     */     }
/* 528 */     temp.valor = valor;
/*     */   }
/*     */   
/*     */   public void actualizaAllUso(boolean valor)
/*     */   {
/* 533 */     NodoSimbolo temp = raiz;
/* 534 */     while (temp != null) {
/* 535 */       temp.uso = valor;
/* 536 */       temp = temp.next;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public double buscarIdValor2(int id)
/*     */   {
/* 543 */     return buscar_nodo_IdValor2(raiz, id);
/*     */   }
/*     */   
/*     */   private double buscar_nodo_IdValor2(NodoSimbolo nodo, int id) {
/* 547 */     if (nodo == null) {
/* 548 */       return -1.0D;
/*     */     }
/* 550 */     if (nodo.id == id) {
/* 551 */       return nodo.valor2;
/*     */     }
/* 553 */     return buscar_nodo_IdValor2(nodo.next, id);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean buscar_Id(int id)
/*     */   {
/* 560 */     return buscar_nodo_Id(raiz, id);
/*     */   }
/*     */   
/*     */   private boolean buscar_nodo_Id(NodoSimbolo nodo, int id) {
/* 564 */     if (nodo == null) {
/* 565 */       return false;
/*     */     }
/* 567 */     if (nodo.id == id) {
/* 568 */       return true;
/*     */     }
/* 570 */     return buscar_nodo_Id(nodo.next, id);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getNombreId(int id)
/*     */   {
/* 578 */     return getNodo_NombreId(raiz, id);
/*     */   }
/*     */   
/*     */   private String getNodo_NombreId(NodoSimbolo nodo, int id) {
/* 582 */     if (nodo == null) {
/* 583 */       return "";
/*     */     }
/* 585 */     if (nodo.id == id) {
/* 586 */       return nodo.nombre;
/*     */     }
/* 588 */     return getNodo_NombreId(nodo.next, id);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void imprimir()
/*     */   {
/* 596 */     NodoSimbolo temp = raiz;
/* 597 */     System.out.println("********************************************");
/* 598 */     while (temp != null) {
/* 599 */       temp.recorre();
/* 600 */       temp = temp.next;
/*     */     }
/* 602 */     System.out.println("********************************************");
/*     */   }
/*     */ }

/* Location:           C:\Users\Pab\Desktop\InAge exe\InAge exe\Age2000W.jar
 * Qualified Name:     Herramientas.TablaSimbolos
 * Java Class Version: 1.1 (45.3)
 * JD-Core Version:    0.7.1
 */



//package Herramientas;

/*
 * TablaSimbolos, es la clase que forma y contiene las variables y sus tipos,
 * encontradas en un programa
*/
/*
public class TablaSimbolos{
  private NodoSimbolo raiz;
  private NodoSimbolo hoja;
  private NodoSimbolo temp;
  private int id;

  public TablaSimbolos(){
  raiz=hoja=null;
  id = 1;
  }

  public boolean vacio(){
  System.out.println("Entro al vacio");
  if(raiz==null)
    return true;
  else
    return false;
  }

  public void insertar(String n, int t, int v){
  if(raiz==null)
    raiz = hoja = new NodoSimbolo(n,t,v,id);
  else
    hoja = hoja.next = new NodoSimbolo(n,t,v, id);
  id++;
  }

  public void insertar(String n, int t, int v, double v2){
  if(raiz==null)
    raiz = hoja = new NodoSimbolo(n,t,v,v2,id);
  else
    hoja = hoja.next = new NodoSimbolo(n,t,v,v2, id);
  id++;
  }

  public void insertar(String n, int t, int v, double v2, int ccid){
  if(raiz==null)
    raiz = hoja = new NodoSimbolo(n,t,v,v2,ccid);
  else
    hoja = hoja.next = new NodoSimbolo(n,t,v,v2,ccid);
  }

  public void eliminar(String n){
    temp = null;
    nodo_eliminar(raiz, n);
  }

  public void nodo_eliminar(NodoSimbolo nodo, String dato){
    if(nodo == null)
      return;
    else {
      if(dato.compareTo(nodo.nombre)==0){
        if(temp==null)
          raiz = temp = nodo.next;
        else{
          if(nodo.next==null)
            hoja=temp;
          temp.next = nodo.next;
          }
         nodo = null;
        return;
        }
      else{
         temp = nodo;
         nodo_eliminar(nodo.next, dato);
         }
    }
  }

  public boolean buscar(String dato){ // busca un nodo
    return buscar_nodo(raiz, dato);
  }

  private boolean buscar_nodo(NodoSimbolo nodo, String dato){
    if(nodo == null)
      return false;
    else {
      if(dato.compareTo(nodo.nombre)==0)
         return true;
      else
         return buscar_nodo(nodo.next, dato);
          }
  }// fin del método buscar_nodo


  public int count(){ // busca un nodo
    int i=0;
    return buscar_count(raiz, i);
  }

  private int buscar_count(NodoSimbolo nodo, int i){
    if(nodo == null)
      return i;
    else {
         i++;
         return buscar_count(nodo.next, i);
          }
  }// fin del método buscar_nodo


  public boolean getUso(int x){ // busca un nodo
  NodoSimbolo temp = raiz;
    while(x>1){
      temp = temp.next;
      x--;
    }
  return temp.uso;
  }


  public int getUsoTipo(int x, int tipo){ // busca un nodo
  NodoSimbolo temp = raiz;
    while(x>1){
      temp = temp.next;
      x--;
    }
  if(temp.tipo==tipo){      // el tipo a evaluar es el que se pide
    if(temp.uso) return 1; // su uso es TRUE
      else return 0;        // su uso es FALSE
  }
  else
    return -1;  // no es igual al tipo que se pide
  }


  public String getNombre(int x){ // busca un nodo
  NodoSimbolo temp = raiz;
    while(x>1){
      temp = temp.next;
      x--;
    }
  return temp.nombre;
  }


  public int buscar_var_tipo(String dato){ // busca un nodo
    return buscar_nodo_var_tipo(raiz, dato);
  }

  private int buscar_nodo_var_tipo(NodoSimbolo nodo, String dato){
    if(nodo == null)
      return -1;
    else {
      if(dato.compareTo(nodo.nombre)==0)
        return nodo.tipo;
      else
         return buscar_nodo_var_tipo(nodo.next, dato);
          }
  }// fin del método buscar_nodo


    public String getCodigoSensores(){ // busca un nodo
      NodoSimbolo temp;
      temp = raiz;
      String cadena = "";
      while(temp!=null){
        if((temp.tipo==4 | temp.tipo==5 | temp.tipo==6 | temp.tipo==9 | temp.tipo==10 |
           temp.tipo==11 | temp.tipo==12 | temp.tipo==13 | temp.tipo==14 | temp.tipo==15 |
           temp.tipo==16) & temp.uso == true){
         // la cadena que regresa con el formato de un sensor es
         // "  (&)("NombreVariable")(id)(tipo)   "
          cadena = cadena + '&' + (char)34 + temp.nombre + (char)34 + (char)temp.id + (char)temp.tipo;
        }

        temp = temp.next;
      }
      return cadena;
    }

      public String getCodigoAcciones(){ // busca un nodo
        NodoSimbolo temp;
        temp = raiz;
        String cadena = "";
        while(temp!=null){
          if(temp.tipo==3 & temp.uso == true){
           // la cadena que regresa con el formato de una acción
           // "  (*)("NombreVariable")(id)"
            cadena = cadena + '*' + (char)34 + temp.nombre + (char)34 + (char)temp.id;
          }

          temp = temp.next;
        }
        return cadena;
      }



      public String getCodigoEnteroDecimal(){ // busca un nodo
        NodoSimbolo temp;
        temp = raiz;
        String cadena = "";
        while(temp!=null){
          // la cadena que regresa con el formato de un Entero o Decimal es
          // "  (&)("NombreVariable")(id)(tipo)(valor)   "
          if(temp.tipo==7 & temp.uso == true){ // Si es un entero
            cadena = cadena + '$' + (char)34 + temp.nombre + (char)34 + (char)temp.id + (char)temp.tipo + (char)35 + temp.valor + (char)35;
          }
          if(temp.tipo==8 & temp.uso == true){ // Si es un decimal
            cadena = cadena + '$' + (char)34 + temp.nombre + (char)34 + (char)temp.id + (char)temp.tipo + (char)35 + temp.valor2 + (char)35;
          }
          temp = temp.next;
        }
        return cadena;
      }


  public String buscar_nombre(int tipo){ // busca un nodo
    return buscar_nodo_nombre(raiz, tipo);
  }

  private String buscar_nodo_nombre(NodoSimbolo nodo, int tipo){
    if(nodo == null)
      return "";
    else {
      if(nodo.tipo==tipo)
        return nodo.nombre;
      else
         return buscar_nodo_nombre(nodo.next, tipo);
          }
  }// fin del método buscar_nodo_nombre


  public boolean buscar_tipo(int tipo){ // busca un nodo
    return buscar_nodo_tipo(raiz, tipo);
  }

  private boolean buscar_nodo_tipo(NodoSimbolo nodo, int tipo){
    if(nodo == null)
      return false;
    else {
      if(nodo.tipo==tipo)
        return true;
      else
         return buscar_nodo_tipo(nodo.next, tipo);
          }
  }// fin del método buscar_nodo



  public void eliminar_tipo(int tipo){ // busca un nodo
    eliminar_nodo_tipo(raiz, tipo);
  }

  private void eliminar_nodo_tipo(NodoSimbolo nodo, int tipo){
    if(nodo == null) return;
    else {
      if(nodo.tipo==tipo){
        if(temp==null){
          raiz = temp = nodo.next;
          }
        else{
          if(nodo.next==null)
            hoja=temp;
          temp.next = nodo.next;
          }
         nodo = null;
         eliminar_nodo_tipo(temp.next, tipo);
        }
      else{
         temp = nodo;
         eliminar_nodo_tipo(nodo.next, tipo);
         }
    }
  }// fin del método buscar_nodo


  public boolean buscar_tipo_valor(int tipo, int valor){ // busca un nodo
    return buscar_nodo_tipo_valor(raiz, tipo, valor);
  }

  private boolean buscar_nodo_tipo_valor(NodoSimbolo nodo, int tipo, int valor){
    if(nodo == null)
      return false;
    else {
      if(nodo.tipo==tipo & nodo.valor==valor)
        return true;
      else
         return buscar_nodo_tipo_valor(nodo.next, tipo, valor);
          }
  }// fin del método buscar_nodo


  public int buscar_var_valor(String dato){ // busca un nodo
    return buscar_nodo_var_valor(raiz, dato);
  }
  
  private int buscar_nodo_var_valor(NodoSimbolo nodo, String dato){
    if(nodo == null)
      return -1;
    else {
      if(dato.compareTo(nodo.nombre)==0)
        return nodo.valor;
      else
         return buscar_nodo_var_valor(nodo.next, dato);
          }
  }// fin del método buscar_nodo



    public double buscar_var_valor2(String dato){ // busca un nodo
      return buscar_nodo_var_valor2(raiz, dato);
    }

    private double buscar_nodo_var_valor2(NodoSimbolo nodo, String dato){
      if(nodo == null)
        return -1;
      else {
        if(dato.compareTo(nodo.nombre)==0)
          return nodo.valor2;
        else
           return buscar_nodo_var_valor2(nodo.next, dato);
            }
    }// fin del método buscar_nodo

*/
    /* ****************************************************** */
/*
        public int buscar_var_id(String dato){ // busca un nodo
          return buscar_nodo_var_id(raiz, dato);
        }

        private int buscar_nodo_var_id(NodoSimbolo nodo, String dato){
          if(nodo == null)
            return -1;
          else {
            if(dato.compareTo(nodo.nombre)==0)
              return nodo.id;
            else
               return buscar_nodo_var_id(nodo.next, dato);
                }
        }// fin del método buscar_nodo

*/
 /* ****************************************************** */

  /*public void actualiza_tipo(String dato, int tipo){
    actualiza_nodo_var_tipo(raiz, dato, tipo);
  }

  private void actualiza_nodo_var_tipo(NodoSimbolo nodo, String dato, int tipo){
    if(nodo != null){
      if(dato.compareTo(nodo.nombre)==0){
        nodo.tipo = tipo;
        }
      else
         actualiza_nodo_var_tipo(nodo.next, dato, tipo);
    }
  }// fin del método buscar_nodo
*/
  /* ****************************************************** */
/*
   public void actualiza_Id_valor2(int id, double valor2){
     actualiza_nodo_var_Id_var2(raiz, id, valor2);
   }

   private void actualiza_nodo_var_Id_var2(NodoSimbolo nodo, int id, double valor2){
     if(nodo != null){
       if(nodo.id==id){
         nodo.valor2 = valor2;
         }
       else
          actualiza_nodo_var_Id_var2(nodo.next, id, valor2);
     }
   }// fin del método buscar_nodo

*/
/* ****************************************************** */
/*
  public void actualiza_valor(String dato, int valor){
    actualiza_nodo_var_valor(raiz, dato, valor);
  }

  private void actualiza_nodo_var_valor(NodoSimbolo nodo, String dato, int valor){
    if(nodo != null){
      if(dato.compareTo(nodo.nombre)==0)
        nodo.valor = valor;
      else
         actualiza_nodo_var_valor(nodo.next, dato, valor);
    }
  }// fin del método buscar_nodo
*/
/* ****************************************************** */
/*
    public void actualiza_valor2(String dato, double valor){
      actualiza_nodo_var_valor2(raiz, dato, valor);
    }

    private void actualiza_nodo_var_valor2(NodoSimbolo nodo, String dato, double valor2){
      if(nodo != null){
        if(dato.compareTo(nodo.nombre)==0)
          nodo.valor2 = valor2;
        else
           actualiza_nodo_var_valor2(nodo.next, dato, valor2);
      }
    }// fin del método buscar_nodo
*/
/*
  public int actualiza_uso(String dato, boolean uso){
    return actualiza_nodo_var_uso(raiz, dato, uso);
  }

  private int actualiza_nodo_var_uso(NodoSimbolo nodo, String dato, boolean uso){
    if(nodo != null){
      if(dato.compareTo(nodo.nombre)==0){
        if(nodo.uso==uso)
          return 2; // ya se encuentra con el valor de uso deseado
        else{ nodo.uso = uso; return 1;} // actualizó el valor de uso al deseado
      }
      else
         return actualiza_nodo_var_uso(nodo.next, dato, uso);
    }
    else
      return 0; // no encontró el valor del nodo especificado
  }// fin del método buscar_nodo


    public int actualiza_uso_tipo(String dato, boolean uso, int tipo){
      return actualiza_nodo_var_uso_tipo(raiz, dato, uso, tipo);
    }

    private int actualiza_nodo_var_uso_tipo(NodoSimbolo nodo, String dato, boolean uso, int tipo){
      if(nodo != null){
        if(dato.compareTo(nodo.nombre)==0){
          if(nodo.uso==uso)
            return 2; // ya se encuentra con el valor de uso deseado
          else{
            nodo.uso = uso;
            nodo.tipo = tipo;
            return 1;} // actualizó el valor de uso al deseado
        }
        else
           return actualiza_nodo_var_uso_tipo(nodo.next, dato, uso, tipo);
      }
      else
        return 0; // no encontró el valor del nodo especificado
    }// fin del método buscar_nodo



  public String getNombreIndice(int indice){
    NodoSimbolo temp;
    temp = raiz;
    for(int i=1; temp!=null & i<indice; i++){
      temp = temp.next;
    }
    return temp.nombre;
  }


    public int getTipoIndice(int indice){
      NodoSimbolo temp;
      temp = raiz;
      for(int i=1; temp!=null & i<indice; i++){
        temp = temp.next;
      }
      return temp.tipo;
    }


  public int getIdIndice(int indice){
    NodoSimbolo temp;
    temp = raiz;
    for(int i=1; temp!=null & i<indice; i++){
      temp = temp.next;
    }
    return temp.id;
  }


  public void actualizaValorIndice(int indice, int valor){
    NodoSimbolo temp;
    temp = raiz;
    for(int i=1; temp!=null & i<indice; i++){
      temp = temp.next;
    }
    temp.valor = valor;
  }

  public void actualizaAllUso(boolean valor){
    NodoSimbolo temp;
    temp = raiz;
    while(temp!=null){
      temp.uso = valor;
      temp = temp.next;
    }
  }


    public double buscarIdValor2(int id){ // busca un nodo
      return buscar_nodo_IdValor2(raiz, id);
    }

    private double buscar_nodo_IdValor2(NodoSimbolo nodo, int id){
      if(nodo == null)
        return -1.0;
      else {
        if( nodo.id == id)
          return nodo.valor2;
        else
           return buscar_nodo_IdValor2(nodo.next, id);
            }
    }// fin del método buscar_nodo


      public boolean buscar_Id(int id){ // busca un nodo
        return buscar_nodo_Id(raiz, id);
      }

      private boolean buscar_nodo_Id(NodoSimbolo nodo, int id){
        if(nodo == null)
          return false;
        else {
          if(nodo.id == id)
             return true;
          else
             return buscar_nodo_Id(nodo.next, id);
              }
      }// fin del método buscar_nodo



     public String getNombreId(int id){ // busca un nodo
          return getNodo_NombreId(raiz, id);
     }

      private String getNodo_NombreId(NodoSimbolo nodo, int id){
        if(nodo == null)
           return "";
        else {
            if(nodo.id == id)
                return nodo.nombre;
            else
                return getNodo_NombreId(nodo.next, id);
        }
      }// fin del método buscar_nodo


  public void imprimir(){
  NodoSimbolo temp;
  temp = raiz;
  System.out.println("********************************************");
  while(temp!=null){
    temp.recorre();
    temp = temp.next;
  }
  System.out.println("********************************************");
  }

}
*/