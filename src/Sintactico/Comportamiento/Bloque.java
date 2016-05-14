package Sintactico.Comportamiento;

public class Bloque{
  private Sintactico.AnalizadorSintactico sintactico;
/*  private int follow_set[]={124,129,125,127,22};// inicio,si,mientras,repite,variable
  private int header_set[]={114,102};// finComportamiento,acciones*/

  public Bloque(Sintactico.AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis()throws Exception{
  InstruccionCiclo InstruccionCiclo= new InstruccionCiclo(sintactico);
  sintactico.comparar(124);
  InstruccionCiclo.analisis();
  sintactico.comparar(109);
  }

}// fin de la clase TBloque
