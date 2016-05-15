package Sintactico.Comportamiento;

public class ExpSensor{
  private Sintactico.AnalizadorSintactico sintactico;
  private int follow_set[]={6,7,124,108,122,12,8,15,13,9,11};// op_logicos,inicio,entonces,hacer,operadores_relaciones
  private int header_set[]={113,114,102};// finConducta,finComportamiento,acciones

  public ExpSensor(Sintactico.AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis()throws Exception{
  try{
    int temp=0;
    Sintactico.Operacion operacion = new Sintactico.Operacion(sintactico);
    Sintactico.OpRelacional opRelacional = new Sintactico.OpRelacional(sintactico);
    if(sintactico.compara(22)){
      operacion.analisis();
      opRelacional.analisis();
      if (sintactico.compara(22)) {
        temp = sintactico.es_variable();
        // Si es una variable tipo sensor
        if ( (temp >= 4 & temp <= 6) || (temp >= 9 & temp <= 16)) { // si es una variable tipo sensor
          sintactico.setCodigoObjeto(38); // es un sensor
          sintactico.setCodigoObjeto(sintactico.getIdTablaSimbolos()); // Id de la variable
          sintactico.existe_var_tipo(temp, follow_set, header_set);
        }
        else{
          operacion.analisis();
        }
      }
    }
    }
  catch(Sintactico.ExcepcionAtras ex){
    sintactico.error("No declaró correctamente la condición");
  }
  catch(Sintactico.ExcepcionPR ex){
    sintactico.error("No se pueden utilizar palabras reservadas como variables");
  }
  }//fin de analisis()

}// fin de la clase TInstrucciones
