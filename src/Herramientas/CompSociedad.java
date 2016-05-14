package Herramientas;

public class CompSociedad{
  private NodoLista2D raiz;
  private NodoLista2D hojaLista;

  public CompSociedad(){
  raiz=hojaLista=null;
  }

  public boolean vacio(){
  if(raiz==null)
    return true;
  else
    return false;
  }

  public void insertar_lista(String d){
  if(raiz==null)
    raiz = hojaLista = new NodoLista2D(d);
  else
    hojaLista = hojaLista.lista = new NodoLista2D(d);
  }

  public String getAgente(){ // busca un nodo
    if(raiz==null)
       return "";
    return hojaLista.descripcion;
  }

  public boolean insertar_elemento(String elemento){ // busca un nodo
    return insertar_nodo_elemento(hojaLista, elemento);
  }

  private boolean insertar_nodo_elemento(NodoLista2D nodo, String elemento){
  NodoLista2D temp;
    if(nodo.elemento== null){ // inserta el nuevo elemento
      nodo.elemento = new NodoLista2D(elemento);
      return true;    // insertó un elemento con éxito
      }
    else {
      temp = nodo.elemento;
      if(elemento.compareTo(temp.descripcion)==0)
         return false;  // no puede insertar un elemento ya existente
      else
         return insertar_nodo_elemento(nodo.elemento, elemento);
          }
  }// fin del método insertar_nodo_elemento();


  public void imprimir(){
  NodoLista2D temp;
  temp = raiz;
  while(temp!=null){
    System.out.println("\nAGENTE:"+ temp.getDescripcion());
    NodoLista2D aux = temp.elemento;
    while(aux!=null){
      System.out.println("-->"+ aux.getDescripcion());
      aux = aux.elemento;
    }
    temp = temp.lista;
  }
  }

  public String getCodigo(){
    int count=0;
    NodoLista2D temp;
    temp = raiz;
    String cadena = "";
    while(temp!=null){
      count++;
      cadena = cadena + (char)92 + (char)34 + temp.getDescripcion() + (char)34;
      NodoLista2D aux = temp.elemento;
      while(aux!=null){
        cadena = cadena + (char)94  + (char)34 + aux.getDescripcion() + (char)34;
        aux = aux.elemento;
      }
      temp = temp.lista;
    }
    cadena = (char)count + cadena;
    return cadena;
  }


}// fin de la clase TCompSociedad
