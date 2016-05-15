package Sintactico.Acciones;

public class Instruccion2{
  private Sintactico.AnalizadorSintactico sintactico;
  private int follow_set[]={129,125,127,22,104,128,121,120};// si,mientras,repite,variable,avanza,retrocede,giraIzq,giraDer
  private int header_set[]={111,102,126};// finAcciones,fin,otro

  public Instruccion2(Sintactico.AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis()throws Exception{

  CondSi2 si2 = new CondSi2(sintactico);
  CondMientras2 mientras2 = new CondMientras2(sintactico);
  CondRepite2 repite2 = new CondRepite2(sintactico);
  Sintactico.Asignacion asignacion = new Sintactico.Asignacion(sintactico);
  Primitivas primitivas = new Primitivas(sintactico);

  sintactico.verifica_entrada(129, follow_set, header_set);
      if(sintactico.compara(129))//si
        si2.analisis();
      if(sintactico.compara(125))//mientras
        mientras2.analisis();
      if(sintactico.compara(127))//repite
        repite2.analisis();
      if(sintactico.compara(22))//asignación
        asignacion.analisis();
      if(sintactico.compara(104) | sintactico.compara(128) |
        sintactico.compara(121) | sintactico.compara(120))//primitivas
        primitivas.analisis();
  }


}// fin de la clase TInstruccion
