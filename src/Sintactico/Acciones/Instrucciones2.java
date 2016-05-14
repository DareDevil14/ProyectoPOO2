package Sintactico.Acciones;

public class Instrucciones2{
  private Sintactico.AnalizadorSintactico sintactico;
  private int follow_set[]={109,124,129,125,127,22,104,128,121,120,110};// fin,inicio,si,mientras,repite,variable,primitivas,finAccion
  private int header_set[]={111};// finAcciones

  public Instrucciones2(Sintactico.AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis()throws Exception{
  try{
  Instruccion2 instruccion2= new Instruccion2(sintactico);
  Bloque2 bloque2 = new Bloque2(sintactico);
  sintactico.verifica_entrada(124, follow_set, header_set);
  if(sintactico.compara(110)==false){
      if(sintactico.compara(124))
        bloque2.analisis();
      else
        instruccion2.analisis();
//      sintactico.verifica_entrada(124, follow_set, header_set);
  }
  }catch(Sintactico.ExcepcionAtras ex){
      sintactico.error("No especificó ninguna instrucción");
  }
  }//fin de analisis()

}// fin de la clase TInstrucciones
