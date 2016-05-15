package Herramientas;

public class Arbol{
  private Nodo raiz;

  // Constructor
  public Arbol(){
    raiz=null;
  }

  public void insertarNodo(String dato, int j, int i){
    if(raiz == null)        // Si llega a una hoja
      raiz = new Nodo(dato, j, i);
    else                    // Sigue recorriendo el árbol
      raiz.insert(dato, j, i);
  }

  public int buscar(String dato, int i){ // busca un nodo
    return buscar_nodo(raiz, dato, i);
  }

  private int buscar_nodo(Nodo nodo, String dato, int i){
    if(nodo == null)
      return 0;
    else { if(nodo.tam < i)
              return buscar_nodo(nodo.der, dato, i);
          else{
              if(nodo.tam > i)
                return buscar_nodo(nodo.izq, dato, i);
              else{
                if(dato.compareTo(nodo.palabra)==0)
                  return nodo.numero;
                else
                  return buscar_nodo(nodo.der, dato, i);
              }

          }
    }
  }// fin del método buscar_nodo

}
