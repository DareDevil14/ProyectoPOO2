package Sintactico.Acciones;

public class InstruccionCiclo2{
  private Sintactico.AnalizadorSintactico sintactico;
  private int follow_set[]={129,125,127,22,110,109,104,128,121,120};// si,mientras,repite,variable,finAccion,fin,primitivas
  private int header_set[]={111};// finAcciones

  public InstruccionCiclo2(Sintactico.AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis()throws Exception{
  try{
  Instruccion2 instruccion2= new Instruccion2(sintactico);
  instruccion2.analisis();
  sintactico.verifica_entrada(129, follow_set, header_set);
  if(sintactico.compara(110)==false && sintactico.compara(109)==false){
      do{
        instruccion2.analisis();
        sintactico.verifica_entrada(129, follow_set, header_set);
        if(sintactico.compara(22) & sintactico.es_variable()!=7){
          sintactico.getToken();
          sintactico.verifica_entrada(129, follow_set, header_set);
          }
      }while(sintactico.compara(110)==false  && sintactico.compara(109)==false);
  }
  }catch(Sintactico.ExcepcionAtras ex){
      sintactico.error("No especificó ninguna instrucción");
  }
  }//fin de analisis();

}// fin de la clase TInstruccionCiclo2
