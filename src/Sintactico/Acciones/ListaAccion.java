package Sintactico.Acciones;

public class ListaAccion{
  private Sintactico.AnalizadorSintactico sintactico;
  private int follow_set[]={18,107,129,125,127,124,131};// :, entero,si,mientras,repite,inicio,solicita
  private int header_set[]={110,111};// finAccion,finAcciones

  public ListaAccion(Sintactico.AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis()throws Exception{
  try{
    sintactico.existe_var_tipo(3, follow_set, header_set);
  }
  catch(Sintactico.ExcepcionPR ex){
    sintactico.error("No se permite usar Palabras Reservadas como variables");
  }
  catch(Sintactico.ExcepcionAtras ex){
    sintactico.error("No especificó el nombre de la accion");
    sintactico.verifica_entrada(18, follow_set, header_set);
  }

/*  catch(Exception e){
    sintactico.error("No especificó el nombre del agente");
    sintactico.verifica_entrada(18, follow_set, header_set);
  }*/
  }


}// fin de la clase TListaAccion
