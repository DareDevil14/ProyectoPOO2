package Herramientas;


class NodoLista2D{
  public NodoLista2D lista;
  public NodoLista2D elemento;
  public String descripcion;

  public NodoLista2D( String d ){
    descripcion = d;
    lista=null;
    elemento=null;
  }

  public String getDescripcion(){
    return descripcion;
  }


/*  public void recorre(){
    System.out.println("renglón: " + renglon + "-->" + descripcion);
  }*/

}
