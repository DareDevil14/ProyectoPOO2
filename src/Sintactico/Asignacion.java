package Sintactico;

public class Asignacion{
  private Sintactico.AnalizadorSintactico sintactico;
  private int follow_set[]={21,22,12,8,13,9,15,11,124,14};//num,variable, op_logicos, inicio, ";"
  private int header_set[]={113,102,108,122};// finConducta,acciones,entonces,hacer

  public Asignacion(Sintactico.AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis()throws Exception{
  AsigEntero asigEntero = new AsigEntero(sintactico);
  AsigDecimal asigDecimal = new AsigDecimal(sintactico);
  int tipo=0;
  if(sintactico.compara(22)){
    tipo = sintactico.getTipoVariable();
    if(tipo==7 || tipo == 72)
      asigEntero.analisis();
    if(tipo==8 || tipo == 82)
      asigDecimal.analisis();

  }

//  sintactico.comparar(19);
  }//fin de analisis()

}// fin de la clase Asignacion
