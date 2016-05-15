package Herramientas;

public class ListaRedoUndo {

  private NodoRedoUndo raiz;
  private NodoRedoUndo hoja;
  private int count;

  public ListaRedoUndo(){
  raiz=hoja=null;
  count = 0;
  }

  public boolean vacio(){
  if(raiz==null)
    return true;
  else
    return false;
  }

/*
 * Inserta un nodo a la lista de Redo/Undo
*/
  public void insertar(int tipo, int id, int x1, int y1, int x2, int y2){
  if(raiz==null)
    raiz = hoja = new NodoRedoUndo(tipo, id, x1, y1, x2, y2);
  else
    hoja = hoja.next = new NodoRedoUndo(tipo, id, x1, y1, x2, y2);
  count++;
  if(count>5){
    NodoRedoUndo temp;
    temp = raiz;
    raiz=raiz.next;
    temp=null;
    count=5;
  }

  }


  /*
   * Cuenta la cantidad de nodos existentes en la lista
  */

  public int getCountNodo(){
  int count=0;
  NodoRedoUndo temp;
  temp = raiz;
  while(temp!=null){
    count++;
    temp = temp.next;
  }
  return count;
  }

  public int getCount(){
    return count;
  }

  public NodoRedoUndo getLastNodo(){
    NodoRedoUndo temp;
    temp = hoja;
    if(hoja==raiz) hoja=raiz=null;
    else{
      hoja = getNodo(count-1);
      hoja.next = null;
    }
    count--;
    return temp;
  }

  private NodoRedoUndo getNodo(int nodo){
    NodoRedoUndo temp;
    temp = raiz;
    for(int i=1; i<nodo && temp!=null; i++) temp = temp.next;
    return temp;
  }

}
