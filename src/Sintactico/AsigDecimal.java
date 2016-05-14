package Sintactico;

public class AsigDecimal{
  private Sintactico.AnalizadorSintactico sintactico;
  private int follow_set[]={21,25,22,12,8,13,9,15,11,124,14};//entero,decimal,variable, op_logicos, inicio, ";"
  private int header_set[]={113,102,108,122};// finConducta,acciones,entonces,hacer

  public AsigDecimal(Sintactico.AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis()throws Exception{
  int renIni=0, renFin=0;
  Operacion operacion = new Operacion(sintactico);
  renIni=sintactico.renglon();
  if(sintactico.compara(22)){
    sintactico.setCodigoObjeto('='); // código para aviso de una asignación
    sintactico.setCodigoObjeto(36); // es una variable
    sintactico.setCodigoObjeto(sintactico.getIdTablaSimbolos()); // Id de la variable
    sintactico.existe_var_numero(8, follow_set, header_set);
  }
  sintactico.comparar(14);
  operacion.analisis();
//  sintactico.comparar(19);
  renFin=sintactico.renglon();
  sintactico.compararDel(19,renIni,renFin);
  sintactico.setCodigoObjeto(37); // fin de la asignación
  }//fin de analisis()

}// fin de la clase TAsignacion
