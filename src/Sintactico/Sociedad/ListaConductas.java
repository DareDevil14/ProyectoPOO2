package Sintactico.Sociedad;

public class ListaConductas{
  private Sintactico.AnalizadorSintactico sintactico;
  private int follow_set[]={112, 115};//finAgente, finSociedad
  // header_set[] se quitó: 102 (acciones)
  private int header_set[]={106};// comportamiento

  public ListaConductas(Sintactico.AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis()throws Exception{
    AlmConducta AlmConducta= new AlmConducta(sintactico);
    do{
      AlmConducta.analisis();
      sintactico.verifica_entrada(22, follow_set, header_set);
    }while(sintactico.compara(22));
  }


}// fin de la clase TListaConductas
