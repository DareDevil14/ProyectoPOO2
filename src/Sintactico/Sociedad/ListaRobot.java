package Sintactico.Sociedad;

public class ListaRobot{
  private Sintactico.AnalizadorSintactico sintactico;
  private int follow_set[]={18, 19};// :, ;
  private int header_set[]={112, 115, 106, 102};// finAgente, finSociedad, comportamiento, acciones

  public ListaRobot(Sintactico.AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis()throws Exception{
  try{
    sintactico.variables(0,1, follow_set, header_set);// es un agente
    sintactico.verifica_entrada(18, follow_set, header_set); // busca ":"
  }
  catch(Sintactico.ExcepcionPR ex){
    sintactico.error("No se permite usar Palabras Reservadas como variables");
  }
  catch(Sintactico.ExcepcionAtras ex){
    sintactico.error("No especificó el nombre del agente");
    sintactico.verifica_entrada(18, follow_set, header_set);
  }

/*  catch(Exception e){
    sintactico.error("No especificó el nombre del agente");
    sintactico.verifica_entrada(18, follow_set, header_set);
  }*/
  }


}// fin de la clase TListaRobot
