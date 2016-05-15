package Sintactico.Sociedad;

public class DefAgente {

  private Sintactico.AnalizadorSintactico sintactico;
//  private int follow_set[]={103};//finSociedad
//  private int header_set[]={106, 102};// comportamiento, acciones

  public DefAgente(Sintactico.AnalizadorSintactico sint) {
  sintactico = sint;
  }

  public void analisis()throws Exception{
  try{
    int renIni=0, renFin=0;
    ListaRobot ListaRobot= new ListaRobot(sintactico);
    renIni=sintactico.renglon();
    ListaRobot.analisis();
    renFin=sintactico.renglon();
    sintactico.compararDel(18,renIni,renFin);
  }
  catch(Sintactico.ExcepcionAtras ex){
    sintactico.error("No definió correctamente el agente");
  }
/*  catch(Exception e){
    sintactico.error("No declaró correctamente un agente");
  }*/

  }

}
