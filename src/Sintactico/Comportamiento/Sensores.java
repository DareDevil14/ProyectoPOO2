package Sintactico.Comportamiento;

public class Sensores{
  private Sintactico.AnalizadorSintactico sintactico;
  private int follow_set[]={107,132,133,134,136,137,138,105};//entero, luz, temperatura, SENSOR_1, SENSOR_2, SENSOR_3, conducta
  private int header_set[]={102};// acciones


  public Sensores(Sintactico.AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis()throws Exception{
  Sensor sensor= new Sensor(sintactico);
  sintactico.verifica_entrada(132, follow_set, header_set);
  while(sintactico.compara(132) || sintactico.compara(133) || sintactico.compara(134)){
      sensor.analisis();
      sintactico.verifica_entrada(132, follow_set, header_set);  // verifica si se encuentra dado de alta otro sensor
  }
/* condición que verifica que haya por lo menos un sensor dado de alta
   if((sintactico.existe_tipo(4) || sintactico.existe_tipo(5) || sintactico.existe_tipo(6))==false)
      sintactico.warning("No ha declarado sensores");
      */
  }


}// fin de la clase TSensores
