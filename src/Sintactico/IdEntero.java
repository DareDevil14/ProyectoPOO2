package Sintactico;

import Lexico.AnalizadorLexico;

public class IdEntero{
  private Sintactico.AnalizadorSintactico sintactico;
  private int follow_set[]={21,22,12,8,13,9,15,11,124};//num,variable, op_logicos, inicio
  private int header_set[]={113,102,108,122};// finConducta,acciones,entonces,hacer

  public IdEntero(Sintactico.AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis()throws Exception{
  try{
      if(sintactico.compara(22)){ // si es una variable
        if(sintactico.getTipo(7)==7){
          sintactico.setCodigoObjeto(36); // es una variable
          sintactico.setCodigoObjeto(sintactico.getIdTablaSimbolos()); // Id de la variable
          sintactico.existe_var_numero(7, follow_set, header_set);
        }
        }
      else
        if(sintactico.compara(21)){ // si es el valor entero
          sintactico.setCodigoObjeto(35);
          sintactico.processNumeroObjeto();
          sintactico.setCodigoObjeto(35);
          sintactico.comparar(21);}
        else{
        	if(AnalizadorLexico.idioma == 1)
        		sintactico.error("No especificó un identificador de tipo entero");
        	else{sintactico.error("It did not specify a identifier type int");}
        }          
  }
  catch(Sintactico.ExcepcionPR ex){
	  if(AnalizadorLexico.idioma == 1)
	  		sintactico.error("No se permite usar Palabras Reservadas como variables");
	  	else{sintactico.error("It is not allowed to use Reserved words like variables");}
  }
  catch(Sintactico.ExcepcionAtras ex){
	  if(AnalizadorLexico.idioma == 1)
		  sintactico.error("No especificó un tipo entero en la condición");
	  	else{sintactico.error("It did not specify a int type in the condition");}
  }

  }//fin de analisis()

}// fin de la clase IdEntero
