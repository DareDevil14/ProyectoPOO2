package Sintactico.Sociedad;

public class Inicio{
  private Sintactico.AnalizadorSintactico sintactico;
  private int follow_set[]={130,103,112,115,18,106,105,113,114,102,101,110,111};//acciones,
  private int header_set[]={};//

  public Inicio(Sintactico.AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis() throws Exception{
  Sociedad sociedad= new Sociedad(sintactico);
  Sintactico.Comportamiento.Comportamiento comportamiento= new Sintactico.Comportamiento.Comportamiento(sintactico);
  Sintactico.Acciones.Acciones acciones = new Sintactico.Acciones.Acciones(sintactico);

  sintactico.verifica_entrada(130, follow_set, header_set);
  sociedad.analisis();
  sintactico.verifica_entrada(106, follow_set, header_set);
  comportamiento.analisis();
  sintactico.verifica_entrada(102, follow_set, header_set);
  acciones.analisis();
  }// fin del método analisis

}// fin de la clase TInicio
