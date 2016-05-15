package Sintactico.Sociedad;

public class AlmConducta{
  private Sintactico.AnalizadorSintactico sintactico;
  private int follow_set[]={22};//variable, finAgente, finSociedad
  private int header_set[]={112, 115, 106, 102};// comportamiento, acciones

  public AlmConducta(Sintactico.AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis()throws Exception{
  try{
      int renIni=0, renFin=0;
      renIni=sintactico.renglon();
      sintactico.variables(0,2, follow_set, header_set);
//      sintactico.comparar(19);
      renFin=sintactico.renglon();
      sintactico.compararDel(19,renIni,renFin); // el delimitador debe de estar ubicado en el mismo renglón
  }
  catch(Sintactico.ExcepcionPR ex){
    sintactico.error("No se permite usar Palabras Reservadas como variables");
  }
  catch(Sintactico.ExcepcionAtras ex){
    sintactico.error("No especificó el nombre de la conducta");
    sintactico.verifica_entrada(19, follow_set, header_set);
  }
/*  catch(Exception e){
    sintactico.error("No especificó el nombre de la conducta");
    sintactico.verifica_entrada(19, follow_set, header_set);
  }*/
  }


}// fin de la clase TListaConductas
