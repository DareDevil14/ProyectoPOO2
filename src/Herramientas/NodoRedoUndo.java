package Herramientas;

public class NodoRedoUndo {
  public NodoRedoUndo next;
  private int tipo; // tipo de acción
  private int id;
  private int x1;
  private int y1;
  private int x2;
  private int y2;


  public NodoRedoUndo(int tipo, int id, int x1, int y1, int x2, int y2){
    this.tipo = tipo;
    this.id = id;
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
    next=null;
  }

  public int getTipo(){
    return tipo;
  }

  public int getId(){
    return id;
  }

  public int getX1(){
    return x1;
  }

  public int getY1(){
    return y1;
  }

  public int getX2(){
    return x2;
  }

  public int getY2(){
    return y2;
  }


}
