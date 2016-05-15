package Sintactico.Comportamiento;

public class Conductas{
  private Sintactico.AnalizadorSintactico sintactico;
  private int follow_set[]={113,114};//finConducta, finComportamiento
  private int header_set[]={102};// acciones

  public Conductas(Sintactico.AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis()throws Exception{
  try{
    Conducta conducta= new Conducta(sintactico);
    do{
      conducta.analisis();
      sintactico.verifica_entrada(105, follow_set, header_set);
    }while(sintactico.compara(105));
  }
  catch(Sintactico.ExcepcionAtras ex){
    sintactico.error("No Finalizó correctamente la declaración de conductas");
  }

  }

}// fin de la clase TConductas
