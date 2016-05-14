package Sintactico;

public class ListaEntero{
  private AnalizadorSintactico sintactico;
  //-,num.entero, ",", ";", =, conducta, finConducta,finAccion, entero, solicita,
  // repite, si, mientras, inicio, acción
  private int follow_set[]={4,21,20,19,14,105,113,110,107,131,127, 129, 125, 124, 101};
  private int header_set[]={102};// acciones

  public ListaEntero(AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis(int tipo)throws Exception{
  try{
      sintactico.var_entero(0,tipo, follow_set, header_set);
  }
  catch(Exception e){
   sintactico.error("No declaró correctamente una variable de tipo \"entero\"");
   sintactico.verifica_entrada(19, follow_set, header_set);
  }
  }

}// fin de la clase TListaEntero
