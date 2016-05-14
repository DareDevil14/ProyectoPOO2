package Sintactico;

public class OpLogico{
  private Sintactico.AnalizadorSintactico sintactico;

  public OpLogico(Sintactico.AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis(){
  if(sintactico.compara(6)){  // &
    sintactico.comparar(6);
    sintactico.setCodigoObjeto(14);
  }
  if(sintactico.compara(7)){ // |
    sintactico.comparar(7);
    sintactico.setCodigoObjeto(15);
  }
  }//fin de analisis()

}// fin de la clase TOpLogico
