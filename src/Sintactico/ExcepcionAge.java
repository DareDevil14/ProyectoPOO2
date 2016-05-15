package Sintactico;

public class ExcepcionAge extends Exception{

 ExcepcionAge() {
  super();
  System.out.println("Entró a la excepcion");
 }

 ExcepcionAge(int token) {
  super();
  System.out.println("Clase TException con el token " + token );
 }

}// Fin de la clase TException
