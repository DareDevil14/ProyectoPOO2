package Sintactico.Comportamiento;

public class Expresion{
  private Sintactico.AnalizadorSintactico sintactico;
  private int follow_set[]={6,7,124,108,122,129,125,127,131,22};// op_logicos,inicio,entonces,hacer,si,mientras,repite,solicita
  private int header_set[]={113,114,102};// finConducta,finComportamiento,acciones

  public Expresion(Sintactico.AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis()throws Exception{
  ElecExp elecExp = new ElecExp(sintactico);
  Sintactico.OpLogico OpLogico = new Sintactico.OpLogico(sintactico);
  elecExp.analisis();
  sintactico.verifica_entrada(6, follow_set, header_set);
  if(sintactico.compara(6) || sintactico.compara(7)){
    do{
      OpLogico.analisis();
      elecExp.analisis();
      sintactico.verifica_entrada(6, follow_set, header_set);
    }while(sintactico.compara(6)==true || sintactico.compara(7)==true);
  }

  }//fin de analisis()

}// fin de la clase TInstrucciones
