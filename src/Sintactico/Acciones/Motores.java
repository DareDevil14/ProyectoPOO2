package Sintactico.Acciones;

public class Motores{
  private Sintactico.AnalizadorSintactico sintactico;
  private int follow_set[]={19, 142, 107, 101};//;, motor, Entero, accion
  private int header_set[]={111};// finAcciones

  public Motores(Sintactico.AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis()throws Exception{
  int renIni=0, renFin=0;
  ListaMotor listaMotor= new ListaMotor(sintactico);
  while(sintactico.compara(142)){
      sintactico.comparar(142);
      renIni=sintactico.renglon();
      listaMotor.analisis();
      sintactico.verifica_entrada(19, follow_set, header_set);
      renFin=sintactico.renglon();
      sintactico.compararDel(19,renIni,renFin);
    //  if(sintactico.compara(19))
    //    sintactico.comparar(19);// ;
  }
  }

}// fin de la clase TMotores
