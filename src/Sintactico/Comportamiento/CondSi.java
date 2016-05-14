package Sintactico.Comportamiento;

public class CondSi{
  private Sintactico.AnalizadorSintactico sintactico;
/*  private int follow_set[]={124,129,125,127,22};// inicio,si,mientras,repite,variable
  private int header_set[]={114,102};// finComportamiento,acciones*/

  public CondSi(Sintactico.AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis()throws Exception{
  Expresion expresion = new Expresion(sintactico);
  Instrucciones instrucciones = new Instrucciones(sintactico);
  sintactico.comparar(129);//si
  sintactico.setCodigoObjeto(23); // código objeto de si
  sintactico.setCodigoObjeto('[');
  expresion.analisis();
  sintactico.comparar(108);//entonces
  sintactico.setCodigoObjeto(']');
  sintactico.setCodigoObjeto('{');
  instrucciones.analisis();
  sintactico.setLongCodigoObjeto('}');
  if(sintactico.compara(126)){//otro
     sintactico.setCodigoObjeto(24); // código objeto de otro
     sintactico.setCodigoObjeto('{');
     sintactico.comparar(126);
     instrucciones.analisis();
     sintactico.setLongCodigoObjeto('}');
  }

  }

}// fin de la clase
