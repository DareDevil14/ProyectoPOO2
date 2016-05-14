package Sintactico;

public class OpRelacional{
  private Sintactico.AnalizadorSintactico sintactico;

  public OpRelacional(Sintactico.AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis(){
  if(sintactico.compara(12)){// <
    sintactico.comparar(12);
    sintactico.setCodigoObjeto(6);
  }
  if(sintactico.compara(8)){// >
    sintactico.comparar(8);
    sintactico.setCodigoObjeto(7);
  }
  if(sintactico.compara(13)){// <=
    sintactico.comparar(13);
    sintactico.setCodigoObjeto(8);
  }
  if(sintactico.compara(9)){// >=
    sintactico.comparar(9);
    sintactico.setCodigoObjeto(9);
  }
  if(sintactico.compara(15)){// ==
    sintactico.comparar(15);
    sintactico.setCodigoObjeto(11);
  }
  if(sintactico.compara(11)){// !=
    sintactico.comparar(11);
    sintactico.setCodigoObjeto(12);
  }
  }//fin de analisis()

}// fin de la clase TOpRelacional
