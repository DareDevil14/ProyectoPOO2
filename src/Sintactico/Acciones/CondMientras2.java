package Sintactico.Acciones;

public class CondMientras2{
  private Sintactico.AnalizadorSintactico sintactico;

  public CondMientras2(Sintactico.AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis()throws Exception{
  Sintactico.ExpRelacional expRelacional = new Sintactico.ExpRelacional(sintactico);
  Instrucciones2 instrucciones2 = new Instrucciones2(sintactico);
  sintactico.comparar(125);//mientras
  sintactico.setCodigoObjeto(21); // código objeto de mientras
  sintactico.setCodigoObjeto('[');
  expRelacional.analisis();
  sintactico.comparar(122);//hacer
  sintactico.setCodigoObjeto(']');
  sintactico.setCodigoObjeto('{');
  instrucciones2.analisis();
  sintactico.setLongCodigoObjeto('}');
  sintactico.setLongCondicion();
  }

}// fin de la clase CondMientras2
