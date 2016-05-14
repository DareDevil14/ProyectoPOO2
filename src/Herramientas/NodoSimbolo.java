package Herramientas;

/*
 * NodoSimbolo
 * Es la estructura que forma la Tabla de Simbolos, donde se encuentran las
 * variables y sus tipos
*/

class NodoSimbolo{
  public int tipo;
  public int valor;
  public double valor2;
  public String nombre;
  public boolean uso;
  public int id;
  public NodoSimbolo next;

  public NodoSimbolo( String nom, int t, int v, int id){
    nombre = nom;
    tipo = t;
    valor = v;
    valor2 = 0.0;
    uso = false;
    this.id = id;
    next=null;
  }

  public NodoSimbolo( String nom, int t, int v, double v2, int id){
    nombre = nom;
    tipo = t;
    valor = v;
    valor2 = v2;
    uso = false;
    this.id = id;
    next=null;
  }

  public void recorre(){
    if(uso==false)
      System.out.println("Nombre: " + nombre + " tipo:" + tipo + " valor:" + valor + " valor2:" + valor2 + " uso:false" + " id:" + id);
    else
     System.out.println("Nombre: " + nombre + " tipo:" + tipo + " valor:" + valor + " valor2:" + valor2 + " uso:true" + " id:" + id);
  }

}
