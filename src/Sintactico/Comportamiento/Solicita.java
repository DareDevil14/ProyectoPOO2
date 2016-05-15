package Sintactico.Comportamiento;

public class Solicita{
  private Sintactico.AnalizadorSintactico sintactico;
  private int follow_set[]={22};//variable
  private int header_set[]={113,114,102};// finConducta,finComportamiento,acciones

  public Solicita(Sintactico.AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis()throws Exception{
  try{
      int renIni=0, renFin=0;
      renIni=sintactico.renglon();
      sintactico.comparar(131);//solicita
      sintactico.setCodigoObjeto(42);
      sintactico.variables(0,3, follow_set, header_set);//acción
    //  sintactico.comparar(19);
      renFin=sintactico.renglon();
      sintactico.compararDel(19,renIni,renFin);
  }
  catch(Sintactico.ExcepcionPR ex){
    sintactico.error("No se permite usar Palabras Reservadas como variables");
  }
  catch(Sintactico.ExcepcionAtras ex){
    sintactico.error("No especificó el nombre de la acción");
//    sintactico.verifica_entrada(19, follow_set, header_set);
  }
  }

}// fin de la clase TSolicita
