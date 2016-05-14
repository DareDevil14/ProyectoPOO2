package Sintactico.Acciones;

public class DeclAccion{
  private Sintactico.AnalizadorSintactico sintactico;
  private int follow_set[]={110,111};//finAccion, finAcciones
  private int header_set[]={};

  public DeclAccion(Sintactico.AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis()throws Exception{
  try{
    Accion accion = new Accion(sintactico);
    do{
      accion.analisis();
      sintactico.verifica_entrada(101, follow_set, header_set);
    }while(sintactico.compara(101));
  }
  catch(Sintactico.ExcepcionAtras ex){
    sintactico.error("No Finalizó correctamente la declaración de acciones");
  }
  }

}// fin de la clase TDeclAccion
