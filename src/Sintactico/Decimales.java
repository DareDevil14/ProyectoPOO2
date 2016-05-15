package Sintactico;

public class Decimales{
  private AnalizadorSintactico sintactico;
  //";", ",", conducta, finConducta,finAccion, entero, decimal, solicita, repite, si,
  // mientras, inicio, acción
  private int follow_set[]={19,20,105,113,110,107,116,131,127, 129, 125, 124, 101};
  private int header_set[]={114,111};// finComportamiento,finAcciones

  public Decimales(AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis(int tipo)throws Exception{
  int renIni=0, renFin=0;
  ListaDecimal ListaDecimal= new ListaDecimal(sintactico);
  do{
      if(sintactico.compara(116)){ // PR --  "decimal"
        renIni=sintactico.renglon();
        sintactico.comparar(116);
        ListaDecimal.analisis(tipo);
        if(sintactico.compara(20)==true || sintactico.compara(22)==true){  // coma o variable
          do{
              sintactico.comparar(20);
              ListaDecimal.analisis(tipo);
              sintactico.verifica_entrada(20, follow_set, header_set);
            }while(sintactico.compara(20)==true || sintactico.compara(22)==true);
        }// fin del if(sintactico.compara(20))
        renFin=sintactico.renglon();
        sintactico.compararDel(19,renIni,renFin);
//          sintactico.comparar(19);
        }// fin del if(sintactico.compara(107))
      sintactico.verifica_entrada(116, follow_set, header_set);
    }while(sintactico.compara(116)==true);
  }

}// fin de la clase Decimales
