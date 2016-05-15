package Sintactico;

import Lexico.AnalizadorLexico;

public class IdDecimal{
  private Sintactico.AnalizadorSintactico sintactico;
  private int follow_set[]={21,22,12,8,13,9,15,11,124};//num,variable, op_logicos, inicio
  private int header_set[]={113,102,108,122};// finConducta,acciones,entonces,hacer

  public IdDecimal(Sintactico.AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis()throws Exception{
  try{
      if(sintactico.compara(22)){
        if(sintactico.getTipo(8)==8){
          sintactico.setCodigoObjeto(36); // es una variable
          sintactico.setCodigoObjeto(sintactico.getIdTablaSimbolos()); // Id de la variable
          sintactico.existe_var_numero(8, follow_set, header_set);
        }
        }
      else
        if(sintactico.compara(25)){
          sintactico.setCodigoObjeto(35); // inicio de número
          sintactico.processNumeroObjeto();
          sintactico.setCodigoObjeto(35); // fin de número
          sintactico.comparar(25);}
        else{
        	if(AnalizadorLexico.idioma == 1)
        		sintactico.error("No especificó un identificador de tipo decimal");
        	else{sintactico.error("It did not specify a decimal type identifier");}
        }
  }
  catch(Sintactico.ExcepcionPR ex){
	  if(AnalizadorLexico.idioma == 1)
  		sintactico.error("No se permite usar Palabras Reservadas como variables");
  	else{sintactico.error("It is not allowed to use Reserved words like variables");}
  }
  catch(Sintactico.ExcepcionAtras ex){
	  if(AnalizadorLexico.idioma == 1)
		  sintactico.error("No especificó un tipo decimal en la condición");
	  	else{sintactico.error("It did not specify a decimal type in the condition");}    
  }

  }//fin de analisis()

}// fin de la clase TInstrucciones
