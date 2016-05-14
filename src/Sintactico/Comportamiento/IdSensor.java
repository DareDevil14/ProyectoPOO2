package Sintactico.Comportamiento;

public class IdSensor{
  private Sintactico.AnalizadorSintactico sintactico;
  private int follow_set[]={22,6,7,124};//variable, op_logicos, inicio
  private int header_set[]={113,102,108,122};// finConducta,acciones,entonces,hacer

  public IdSensor(Sintactico.AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis()throws Exception{
  try{
      int temp=0;
      sintactico.comparar(10);
//      sintactico.verifica_entrada(22,follow_set,header_set);
        if (sintactico.compara(22)) {
          temp = sintactico.es_variable();
          if ( (temp >= 4 & temp <= 6) || (temp >= 9 & temp <= 16)) { // si es una variable tipo sensor
            sintactico.setCodigoObjeto(38); // es un sensor
            sintactico.setCodigoObjeto(sintactico.getIdTablaSimbolos()); // Id de la variable
            sintactico.existe_var_tipo(temp, follow_set, header_set);
            sintactico.setCodigoObjeto(11); // ! var_sensor -->> var_sensor == 0
            sintactico.setCodigoObjeto(35); // código de números
            sintactico.processNumeroObjeto("0");
            sintactico.setCodigoObjeto(35); // fin del número
          }
        }
      else
        sintactico.error("No especificó ningún sensor");
  }
  catch(Sintactico.ExcepcionPR ex){
    sintactico.error("No se permite usar Palabras Reservadas como variables");
  }
  catch(Sintactico.ExcepcionAtras ex){
    sintactico.error("No especificó sensor en la condición");
  }

  }//fin de analisis()

}// fin de la clase TInstrucciones
