package Sintactico.Acciones;

public class AsigMotor{
  private Sintactico.AnalizadorSintactico sintactico;
  private int follow_set[]={20,19, 139, 140, 141};// ",", ";", #3MOTORES
  private int header_set[]={111};// finAcciones

  public AsigMotor(Sintactico.AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis()throws Exception{
  try{
  sintactico.motores(0,17, follow_set, header_set);// es un motor
  }
  catch(Exception e){
   sintactico.error("No declaró correctamente el motor");
   sintactico.verifica_entrada(19, follow_set, header_set);
  }
  }

}// fin de la clase TAsigMotor
