package Sintactico;

public class ListaDecimal{
  private AnalizadorSintactico sintactico;
  //-,num.entero, ",", ";", =, conducta, finConducta,finAccion, decimal, solicita,
  // repite, si, mientras, inicio, acción
  private int follow_set[]={4,21,20,19,14,105,113,110,116,131,127, 129, 125, 124, 101};
  private int header_set[]={102};// acciones

  public ListaDecimal(AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis(int tipo)throws Exception{
  try{
      sintactico.var_decimal(0,tipo, follow_set, header_set);
  }
  catch(Exception e){
   sintactico.error("No declaró correctamente una variable de tipo \"decimal\"");
   sintactico.verifica_entrada(19, follow_set, header_set);
  }
  }

}// fin de la clase TListaEntero
