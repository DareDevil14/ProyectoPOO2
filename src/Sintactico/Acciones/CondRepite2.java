package Sintactico.Acciones;

public class CondRepite2{
  private Sintactico.AnalizadorSintactico sintactico;
  private int follow_set[]={135,124,129,125,127,22,104,128,121,120};//veces,inicio,si,mientras,repite,variable,primitiva
  private int header_set[]={110,111};// finAccion,finAcciones

  public CondRepite2(Sintactico.AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis()throws Exception{
  Sintactico.OpEntero opEntero = new Sintactico.OpEntero(sintactico);
  Instrucciones2 instrucciones2 = new Instrucciones2(sintactico);
  sintactico.comparar(127);//repite
  sintactico.setCodigoObjeto(22); // código obejto de repite
  sintactico.setCodigoObjeto('[');
  opEntero.analisis();
  sintactico.verifica_entrada(135, follow_set, header_set);
  sintactico.comparar(135);//veces
  sintactico.setCodigoObjeto(']');
  sintactico.setCodigoObjeto('{');
  instrucciones2.analisis();
  sintactico.setLongCodigoObjeto('}');
//  sintactico.setLongCondicion();
  }

}// fin de la clase CondRepite2
