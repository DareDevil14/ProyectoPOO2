package Sintactico.Sociedad;

public class Sociedad{
  private Sintactico.AnalizadorSintactico sintactico;
  // follow_set[] no se incluyó: 106 (comportamiento)
  private int follow_set[]={103, 112, 115, 18};// agente, finAgente, finSociedad, :
  // header_set[] no se incluyó: 106 (comportamiento), 102 (acciones)
  private int header_set[]={132,133,134,107,105,131};

  public Sociedad(Sintactico.AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis() throws Exception{
  try{
  int renIni=0, renFin=0;
  Agentes agentes= new Agentes(sintactico);
  renIni=sintactico.renglon();
  sintactico.comparar(130);
  renFin=sintactico.renglon();
//  sintactico.verifica_entrada(103, follow_set, header_set);
//  sintactico.comparar(18);
  sintactico.compararDel(18,renIni,renFin); // el delimitador debe de estar ubicado en el mismo renglón
  sintactico.verifica_entrada(103, follow_set, header_set);
  agentes.analisis();
  sintactico.comparar(115);

  }
  catch(Sintactico.ExcepcionSalirParte ex){
    sintactico.error("No Finalizó correctamente la Sociedad");
  }
  catch(Sintactico.ExcepcionAtras ex){
    sintactico.error("No Finalizó correctamente la Sociedad. TExcepcionAtras en JSociedad");
  }
/*  catch(Exception e){
    sintactico.error("Exception e TSociedad");
  }*/
 }

}// fin de la clase TSociedad
