package Sintactico.Acciones;

public class Bloque2{
  private Sintactico.AnalizadorSintactico sintactico;
/*  private int follow_set[]={124,129,125,127,22};// inicio,si,mientras,repite,variable
  private int header_set[]={114,102};// finComportamiento,acciones*/

  public Bloque2(Sintactico.AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis()throws Exception{
  InstruccionCiclo2 InstruccionCiclo2= new InstruccionCiclo2(sintactico);
  sintactico.comparar(124);// inicio
//  sintactico.setCodigoObjeto('{');
  InstruccionCiclo2.analisis();
  sintactico.comparar(109);// fin
//  sintactico.setLongCodigoObjeto('}');
  }

}// fin de la clase TBloque2
