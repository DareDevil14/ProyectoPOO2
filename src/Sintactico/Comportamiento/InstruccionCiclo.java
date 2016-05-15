package Sintactico.Comportamiento;

public class InstruccionCiclo{
  private Sintactico.AnalizadorSintactico sintactico;
  private int follow_set[]={129,125,127,22,131,113,109};// si,mientras,repite,variable,solicita,finConducta,fin
  private int header_set[]={114,102};// finComportamiento,acciones

  public InstruccionCiclo(Sintactico.AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis()throws Exception{
  try{
  Instruccion instruccion= new Instruccion(sintactico);
  instruccion.analisis();
  sintactico.verifica_entrada(129, follow_set, header_set);
  if(sintactico.compara(113)==false && sintactico.compara(109)==false){
      do{
        instruccion.analisis();
        sintactico.verifica_entrada(129, follow_set, header_set);
        if(sintactico.compara(22) & sintactico.es_variable()!=7){
          sintactico.getToken();
          sintactico.verifica_entrada(129, follow_set, header_set);
          }
      }while(sintactico.compara(113)==false  && sintactico.compara(109)==false);
  }
  }catch(Sintactico.ExcepcionAtras ex){
      sintactico.error("No especificó ninguna instrucción");
  }
  }//fin de analisis();

}// fin de la clase TInstruccionCiclo
