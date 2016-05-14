package Sintactico;

public class OpAritmetico{
  private Sintactico.AnalizadorSintactico sintactico;

  public OpAritmetico(Sintactico.AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis(){
  if(sintactico.compara(5)){// +
    sintactico.comparar(5);
    sintactico.setCodigoObjeto(16);
  }
  if(sintactico.compara(4)){// -
    sintactico.comparar(4);
    sintactico.setCodigoObjeto(17);
  }
  if(sintactico.compara(3)){// *
    sintactico.comparar(3);
    sintactico.setCodigoObjeto(18);
  }
  if(sintactico.compara(1)){// /
    sintactico.comparar(1);
    sintactico.setCodigoObjeto(19);
  }
  }//fin de analisis()

}// fin de la clase TOpAritmetico
