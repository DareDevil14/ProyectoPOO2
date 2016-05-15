package Sintactico.Comportamiento;

public class AsigSensor{
  private Sintactico.AnalizadorSintactico sintactico;
  private int follow_set[]={20,19, 136, 137, 138, 114, 105, 107};// ",", ";", #3SENSORES, finComportamiento. conducta, entero,
  private int header_set[]={102};// acciones

  public AsigSensor(Sintactico.AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis(int sensor)throws Exception{
  try{
  sintactico.sensores(0,sensor, follow_set, header_set);// es un sensor
  }
/*  catch(Sintactico.TExcepcionAtras ex){
   sintactico.error("No declaró correctamente el sensor");
   sintactico.verifica_entrada(19, follow_set, header_set);
  }*/
  catch(Exception e){
   sintactico.error("No declaró correctamente el sensor");
   sintactico.verifica_entrada(19, follow_set, header_set);
  }
  }


}// fin de la clase TAsigSensor
