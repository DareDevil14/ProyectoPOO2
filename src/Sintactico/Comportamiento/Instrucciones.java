package Sintactico.Comportamiento;

public class Instrucciones{
  private Sintactico.AnalizadorSintactico sintactico;
  private int follow_set[]={109,124,129,125,127,22,131,113};// fin,inicio,si,mientras,repite,variable,solicita,finConducta
  private int header_set[]={114,102};// finComportamiento,acciones

  public Instrucciones(Sintactico.AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis()throws Exception{
  try{
  Instruccion instruccion= new Instruccion(sintactico);
  Bloque bloque = new Bloque(sintactico);
  sintactico.verifica_entrada(124, follow_set, header_set);
  if(sintactico.compara(113)==false){
      if(sintactico.compara(124))
        bloque.analisis();
      else
        instruccion.analisis();
//      sintactico.verifica_entrada(124, follow_set, header_set);
  }
  }catch(Sintactico.ExcepcionAtras ex){
      sintactico.error("No especificó ninguna instrucción");
  }
  }//fin de analisis()

}// fin de la clase TInstrucciones
