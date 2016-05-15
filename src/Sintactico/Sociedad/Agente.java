package Sintactico.Sociedad;

public class Agente{
  private Sintactico.AnalizadorSintactico sintactico;
  private int follow_set[]={103};//finSociedad
  private int header_set[]={106, 102};// comportamiento, acciones

  public Agente(Sintactico.AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis()throws Exception{
  try{
    int renIni=0, renFin=0;
    ListaRobot ListaRobot= new ListaRobot(sintactico);
    ListaConductas ListaConductas= new ListaConductas(sintactico);
    sintactico.verifica_entrada(103, follow_set, header_set);
    sintactico.comparar(103);
    renIni=sintactico.renglon();
    ListaRobot.analisis();
    renFin=sintactico.renglon();
    sintactico.compararDel(18,renIni,renFin);
    ListaConductas.analisis();
    sintactico.comparar(112);
  }
  catch(Sintactico.ExcepcionAtras ex){
    sintactico.error("No declaró correctamente el agente");
  }
/*  catch(Exception e){
    sintactico.error("No declar� correctamente un agente");
  }*/

  }

}// fin de la clase TAgente
