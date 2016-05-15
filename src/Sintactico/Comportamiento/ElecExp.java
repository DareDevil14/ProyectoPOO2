package Sintactico.Comportamiento;

public class ElecExp{
  private Sintactico.AnalizadorSintactico sintactico;
  private int follow_set[]={6,7,124,108,122,12,8,15,13,9,11};// op_logicos,inicio,entonces,hacer,operadores_relaciones
  private int header_set[]={113,114,102};// finConducta,finComportamiento,acciones

  public ElecExp(Sintactico.AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis()throws Exception{
  try{
    int temp=0;
    IdSensor idSensor = new IdSensor(sintactico);
    Sintactico.OpRelacional opRelacional = new Sintactico.OpRelacional(sintactico);
    Sintactico.Operacion operacion = new Sintactico.Operacion(sintactico);
    ExpSensor expSensor = new ExpSensor(sintactico);
    // Primera opción (p.e. !tactilDer)
    if(sintactico.compara(10)){ // "!"
      idSensor.analisis();
    }
    else{
      // Segunda opción (p.e tactilDer <1)
      if (sintactico.compara(22)) {
        temp = sintactico.es_variable();
        if ( (temp >= 4 & temp <= 6) || (temp >= 9 & temp <= 16)) { // si es una variable tipo sensor
          sintactico.setCodigoObjeto(38); // es un sensor
          sintactico.setCodigoObjeto(sintactico.getIdTablaSimbolos()); // Id de la variable
          sintactico.existe_var_tipo(temp, follow_set, header_set);
          sintactico.verifica_entrada(12, follow_set, header_set); // verifica el siguiente token. En espera que sea un operador relacional
          if(sintactico.compara(8) || sintactico.compara(9) || sintactico.compara(11) ||
             sintactico.compara(12) || sintactico.compara(13) || sintactico.compara(15) ){
            opRelacional.analisis();
            operacion.analisis();
          }
          else{
            sintactico.setCodigoObjeto(7); //  var_sensor -->> var_sensor > 0
            sintactico.setCodigoObjeto(35); // código de números
            sintactico.processNumeroObjeto("0");
            sintactico.setCodigoObjeto(35); // fin del número
          }
        }
        else{ // Tercera opción (p.e. 1> tactilDer ó 1>1)
          expSensor.analisis();
        }
      } // Tercera opción
      else{
        if(sintactico.compara(21) || sintactico.compara(25)){
          expSensor.analisis();
        }
        else{
          sintactico.error("No declaró correctamente la condición");
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

}// fin de la clase ElecExp
