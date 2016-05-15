package Sintactico.Comportamiento;

public class CondRepite{
  private Sintactico.AnalizadorSintactico sintactico;
  private int follow_set[]={135,124,129,125,127,22,131};//veces,inicio,si,mientras,repite,variable,solicita
  private int header_set[]={113,114,102};// finConducta,finComportamiento,acciones

  public CondRepite(Sintactico.AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis()throws Exception{
  Sintactico.OpEntero opEntero = new Sintactico.OpEntero(sintactico);
  Instrucciones instrucciones = new Instrucciones(sintactico);
  sintactico.comparar(127);//repite
  sintactico.setCodigoObjeto(22); // código obejto de repite
  sintactico.setCodigoObjeto('[');
  opEntero.analisis();
  sintactico.verifica_entrada(135, follow_set, header_set);
  sintactico.comparar(135);//veces
  sintactico.setCodigoObjeto(']');
  sintactico.setCodigoObjeto('{');
  instrucciones.analisis();
  sintactico.setLongCodigoObjeto('}');
//  sintactico.setLongCondicion();
  }

}// fin de la clase CondRepite
