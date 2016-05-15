package Sintactico.Comportamiento;

public class Instruccion{
  private Sintactico.AnalizadorSintactico sintactico;
  private int follow_set[]={129,125,127,22,131};// si,mientras,repite,variable,solicita
  private int header_set[]={109,114,102,126};// finComportamiento,acciones,fin,otro

  public Instruccion(Sintactico.AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis()throws Exception{

  CondSi si = new CondSi(sintactico);
  CondMientras mientras = new CondMientras(sintactico);
  CondRepite repite = new CondRepite(sintactico);
  Sintactico.Asignacion asignacion = new Sintactico.Asignacion(sintactico);
  Solicita solicita = new Solicita(sintactico);

  sintactico.verifica_entrada(129, follow_set, header_set);
      if(sintactico.compara(129))//si
        si.analisis();
      if(sintactico.compara(125))//mientras
        mientras.analisis();
      if(sintactico.compara(127))//repite
        repite.analisis();
      if(sintactico.compara(22))//asignación
        asignacion.analisis();
      if(sintactico.compara(131))//solicita
        solicita.analisis();

  }


}// fin de la clase TInstruccion
