package Sintactico.Acciones;

public class Primitivas{
  private Sintactico.AnalizadorSintactico sintactico;

  public Primitivas(Sintactico.AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis(){
  if(sintactico.compara(104)){  // avanza
    sintactico.comparar(104);
    sintactico.setCodigoObjeto(1);
    sintactico.setCodigoObjeto(2);
  }
  if(sintactico.compara(128)){  // retrocede
    sintactico.comparar(128);
    sintactico.setCodigoObjeto(1);
    sintactico.setCodigoObjeto(3);
  }
  if(sintactico.compara(121)){  // giraIzq
    sintactico.comparar(121);
    sintactico.setCodigoObjeto(1);
    sintactico.setCodigoObjeto(4);
  }
  if(sintactico.compara(120)){  // giraDer
    sintactico.comparar(120);
    sintactico.setCodigoObjeto(1);
    sintactico.setCodigoObjeto(5);
  }
  sintactico.comparar(19);// ;
  }//fin de analisis()

}// fin de la clase TPrimitivas
