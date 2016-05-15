package Herramientas;

/*
 * La clase Errores, es una estructura compuesta de NodoLista, en donde contiene
 * los errores encontrados en el compilador, y los almacena para mostrarlos
 * hasta el final de su análisis
*/
public class Errores{
  private NodoLista raiz;
  private NodoLista hoja;

  public Errores(){
  raiz=hoja=null;
  }

  public boolean vacio(){
  if(raiz==null)
    return true;
  else
    return false;
  }

/*
 * Inserta un nodo a la lista de errores
*/
  public void insertar(int r, String d){
  if(raiz==null)
    raiz = hoja = new NodoLista(r,d);
  else
    hoja = hoja.next = new NodoLista(r,d);
  }

  public void imprimir(){
  NodoLista temp;
  temp = raiz;
  while(temp!=null){
    temp.recorre();
    temp = temp.next;
  }
  }

  /*
   * Cuenta la cantidad de nodos existentes en la lista
   * Cuenta la cantidad de errores encontrados en el archivo fuente, al
   * correr los procesos de un compilador
  */

  public int getCountNodo(){
  int count=0;
  NodoLista temp;
  temp = raiz;
  while(temp!=null){
    count++;
    temp = temp.next;
  }
  return count;
  }

  public String getRowDesc(int nodo){
  NodoLista temp;
  temp = raiz;
  for(int i=1; i<nodo && temp!=null; i++) temp = temp.next;
  if(temp!=null) return temp.getStringNodo(); else return "";
  }

  public int getRow(int nodo){
  NodoLista temp;
  temp = raiz;
  for(int i=1; i<nodo && temp!=null; i++) temp = temp.next;
  if(temp!=null) return temp.getRow(); else return 0;
  }

  public NodoLista getNodo(int nodo){
    NodoLista temp;
    temp = raiz;
    for(int i=1; i<nodo && temp!=null; i++) temp = temp.next;
    return temp;
  }

}
