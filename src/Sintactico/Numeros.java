package Sintactico;


public class Numeros {
  private Sintactico.AnalizadorSintactico sintactico;
  private int follow_set[]={6,7,5,4,3,1,12,13,8,9,15,11,17,124};//&,|,+,-,*,/,<,<=,>,>=,==,!=,),inicio
  private int header_set[]={113,102,108,122};// finConducta,acciones,entonces,hacer

  public Numeros(Sintactico.AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis()throws Exception{
  try{
    int tipo=0;
    if(sintactico.compara(22)){ // Si es una variable
      tipo = sintactico.getTipoVariable(); // qué tipo de variable es
      switch(tipo){
        case 1:sintactico.error("La variable está declarada como agente");
                break;
        case 2:sintactico.error("La variable está declarada como conducta");
                break;
        case 3:sintactico.error("La variable está declarada como accion");
                break;
        case 4: case 5: case 6:sintactico.error("La variable está declarada como tipo sensor");
                break;
        case 7: case 72:
                sintactico.setCodigoObjeto(36); // es una variable
                sintactico.setCodigoObjeto(sintactico.getIdTablaSimbolos()); // Id de la variable
                sintactico.existe_var_numero(7, follow_set, header_set);
                break;
        case 8: case 82:
                sintactico.setCodigoObjeto(36); // es una variable
                sintactico.setCodigoObjeto(sintactico.getIdTablaSimbolos()); // Id de la variable
                sintactico.existe_var_numero(8, follow_set, header_set);
                break;
        case 17:sintactico.error("La variable está declarada como tipo motor");
                break;
      }

    }
    else{
      if (sintactico.compara(21)){  // si es un entero
        sintactico.setCodigoObjeto(35); // inicio de número
        sintactico.processNumeroObjeto();
        sintactico.setCodigoObjeto(35); // fin de número
        sintactico.comparar(21);
      }
      else{
        if(sintactico.compara(25)){  // si es un decimal
          sintactico.setCodigoObjeto(35); // inicio de número
          sintactico.processNumeroObjeto();
          sintactico.setCodigoObjeto(35); // fin de número
          sintactico.comparar(25);
        }
        else
          sintactico.error("No especificó un identificador numérico");
      }
    }
  }
  catch(Sintactico.ExcepcionPR ex){
    sintactico.error("No se permite usar Palabras Reservadas como variables");
  }
  catch(Sintactico.ExcepcionAtras ex){
    sintactico.error("No especificó un numérico en la condición");
 }

  }//fin de analisis()
}
