package Sintactico.Comportamiento;

public class CondMientras{
  private Sintactico.AnalizadorSintactico sintactico;

  public CondMientras(Sintactico.AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis()throws Exception{
  Expresion expresion = new Expresion(sintactico);
  Instrucciones instrucciones = new Instrucciones(sintactico);
  sintactico.comparar(125);//mientras
  sintactico.setCodigoObjeto(21); // código objeto de mientras
  sintactico.setCodigoObjeto('[');
  expresion.analisis();
  sintactico.comparar(122);//hacer
  sintactico.setCodigoObjeto(']');
  sintactico.setCodigoObjeto('{');
  instrucciones.analisis();
  sintactico.setLongCodigoObjeto('}');
  sintactico.setLongCondicion();
  }

}// fin de la clase CondMientras
