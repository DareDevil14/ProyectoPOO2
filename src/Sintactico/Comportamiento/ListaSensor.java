package Sintactico.Comportamiento;

public class ListaSensor{
  private Sintactico.AnalizadorSintactico sintactico;
  private int follow_set[]={19,16,17,20,114,22,107,105, 134, 132,133};//";", (,),",", finComportamiento, variable, entero, conducta, temperatura, tactcil, luz
  private int header_set[]={102};// acciones


  public ListaSensor(Sintactico.AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis(int sensor)throws Exception{
  AsigSensor AsigSensor= new AsigSensor(sintactico);
  boolean salir=false;
  AsigSensor.analisis(sensor);
  do{
  sintactico.verifica_entrada(20, follow_set, header_set);
  if(sintactico.compara(20) || sintactico.compara(22)){// || sintactico.compara(16) || sintactico.compara(17)){//igual a ",", variable
    sintactico.comparar(20);
    AsigSensor.analisis(sensor);
    }
  else{
    salir=true;
//    if(sintactico.compara(19) || sintactico.compara(114))
//      salir=true;
  }
  }while(salir==false);
  }// fin de void analisis(int)

}// fin de la clase TListaSensor
