package Sintactico;

public class Enteros{
  private AnalizadorSintactico sintactico;
  //";", ",", conducta, finConducta,finAccion, entero, decimal, solicita, repite, si,
  // mientras, inicio, acción
  private int follow_set[]={19,20,105,113,110,107,116,131,127, 129, 125, 124, 101};
  private int header_set[]={114,111};// finComportamiento,finAcciones

  public Enteros(AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis(int tipo)throws Exception{
  int renIni=0, renFin=0;
  ListaEntero ListaEntero= new ListaEntero(sintactico);
  do{
      if(sintactico.compara(107)){ // PR = entero
        renIni=sintactico.renglon();
        sintactico.comparar(107);
        ListaEntero.analisis(tipo);
        if(sintactico.compara(20)==true || sintactico.compara(22)==true){  // Si es "coma",  PR
          do{
              sintactico.comparar(20); // compara la "coma"
              ListaEntero.analisis(tipo);
              sintactico.verifica_entrada(20, follow_set, header_set); // verifica el siguiente token.  Recuperación en modo de "pánico"
            }while(sintactico.compara(20)==true || sintactico.compara(22)==true); // si hay mas variables declaradas
        }// fin del if(sintactico.compara(20))
        renFin=sintactico.renglon();
        sintactico.compararDel(19,renIni,renFin); // compara "punto y coma"
//          sintactico.comparar(19);
        }// fin del if(sintactico.compara(107))
      sintactico.verifica_entrada(107, follow_set, header_set); // verifica el siguiente token. En espera que sea entero
    }while(sintactico.compara(107)==true);
  }

}// fin de la clase Enteros
