package Sintactico.Sociedad;

public class Agentes{
  private Sintactico.AnalizadorSintactico sintactico;
  private int follow_set[]={22,115};//finSociedad
  private int header_set[]={106, 102};// comportamiento, acciones

  public Agentes(Sintactico.AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis() throws Exception{
  Agente agente= new Agente(sintactico);
  do{
    agente.analisis();
    sintactico.verifica_entrada(103, follow_set, header_set);
  }while(sintactico.compara(103) || sintactico.compara(22));
  }



}// fin de la clase TAgentes
