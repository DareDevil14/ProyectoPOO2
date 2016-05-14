package Sintactico.Comportamiento;

public class Sensor{
  private Sintactico.AnalizadorSintactico sintactico;
  private int follow_set[]={19, 132, 133, 134, 107, 105};//;, #3SENSORES, Entero, conducta
  private int header_set[]={102};// comportamiento, acciones

  public Sensor(Sintactico.AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis()throws Exception{
  int renIni=0,renFin=0;
  ListaSensor listaSensor= new ListaSensor(sintactico);
  int valor=0;
  sintactico.verifica_entrada(132, follow_set, header_set);
  valor = tipo_sensor();
  renIni=sintactico.renglon();
  if(valor>0){
    listaSensor.analisis(valor);
  }
  sintactico.verifica_entrada(19, follow_set, header_set);
  renFin=sintactico.renglon();
  sintactico.compararDel(19,renIni,renFin);
//  if(sintactico.compara(19))
//    sintactico.comparar(19);// ;
  }

  private int tipo_sensor(){
  if(sintactico.compara(132)){ // PR = tactil
    sintactico.comparar(132);
    return 4;}
  if(sintactico.compara(133)){ // PR = luz
    sintactico.comparar(133);
    return 5;}
  if(sintactico.compara(134)){ // PR = temperatura
    sintactico.comparar(134);
    return 6;}
  return -1;
  }

}// fin de la clase TSensor
