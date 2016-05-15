package Sintactico.Acciones;

public class CondSi2{
  private Sintactico.AnalizadorSintactico sintactico;
/*  private int follow_set[]={124,129,125,127,22};// inicio,si,mientras,repite,variable
  private int header_set[]={114,102};// finComportamiento,acciones*/

  public CondSi2(Sintactico.AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis()throws Exception{
  Sintactico.ExpRelacional expRelacional = new Sintactico.ExpRelacional(sintactico);
  Instrucciones2 instrucciones2 = new Instrucciones2(sintactico);
  sintactico.comparar(129);//si
  sintactico.setCodigoObjeto(23); // código objeto de si
  sintactico.setCodigoObjeto('[');
  expRelacional.analisis();
  sintactico.comparar(108);//entonces
  sintactico.setCodigoObjeto(']');
  sintactico.setCodigoObjeto('{');
  instrucciones2.analisis();
  sintactico.setLongCodigoObjeto('}');
  if(sintactico.compara(126)){//otro
     sintactico.setCodigoObjeto(24); // código objeto de otro
     sintactico.setCodigoObjeto('{');
     sintactico.comparar(126);
     instrucciones2.analisis();
     sintactico.setLongCodigoObjeto('}');
  }

  }

}// fin de la clase CondSi2
