package Herramientas;

/*
 * La clase Nodo, Son los contenedores de información y quienes estructuran
 * un árbol.
*/

class Nodo{
  public int tam;
  public Nodo der; // su nodo derecho
  public Nodo izq; // su nodo izquierdo
  public int numero; // valor del nodo
  public String palabra; // valor, palabra reservada

  // Constructor de un nodo
  public Nodo(String dato, int j, int i){
    palabra = dato;
    numero = j;
    tam = i;
    izq=der=null;
  }

  public void insert(String dato, int j, int i){
    if(i < tam){      // Nodo izquierdo es menor
      if(izq ==null)
        izq = new Nodo(dato, j, i);
      else
        izq.insert(dato, j, i);
    }
    else {            // Nodo derecho es mayor o igual
      if(der == null)
        der = new Nodo(dato, j, i);
      else
        der.insert(dato, j, i);
    }
  }// fin del método insert

}
