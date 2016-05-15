package Herramientas;

/*
 * Clase NodoLista
 * Contiene los nodos que estructuran a una lista
 * Clase utilizada para el caso de los errores y warnings
*/

class NodoLista{
  public NodoLista next;
  private int renglon; // reglón del error
  private String descripcion; // descripción del error

  public NodoLista( int r, String d){
    renglon=r;
    descripcion = d;
    next=null;
  }

  public void recorre(){
    System.out.println("renglón: " + renglon + "-->" + descripcion);
  }

  public String getStringNodo(){
    return " "+renglon+" --> "+descripcion;
  }

  public int getRow(){
    return renglon;
  }

  public String getDesc(){
    return descripcion;
  }

}
