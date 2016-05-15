package Sintactico.Acciones;

public class ListaMotor{
  private Sintactico.AnalizadorSintactico sintactico;
  private int follow_set[]={19,16,17,20,22,111,107,101, 142};//";", (,),",", variable, finAcciones, entero, accion, motor
  private int header_set[]={111};// finAcciones


  public ListaMotor(Sintactico.AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis()throws Exception{
  AsigMotor asigMotor= new AsigMotor(sintactico);
  boolean salir=false;
  asigMotor.analisis();
  do{
  sintactico.verifica_entrada(20, follow_set, header_set);
  if(sintactico.compara(20) || sintactico.compara(22)){ // || sintactico.compara(16) || sintactico.compara(17)){//",", variable
    sintactico.comparar(20);
    asigMotor.analisis();
    }
  else{
      salir=true;
//    if(sintactico.compara(19) || sintactico.compara(111))
  }
  }while(salir==false);
  }// fin de void analisis(int)

}// fin de la clase TListaSensor
