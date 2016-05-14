package Sintactico;

public class CpoEntero{
  private Sintactico.AnalizadorSintactico sintactico;
  private int follow_set[]={5,4,3,1,12,8,13,9,15,11,124,108,122,135,6,7,129,125,127,131,19, 17};// ;,op_aritm,inicio,entonces,hacer,veces,op_logico,si,mientras,repite,solicita,;, )
  private int header_set[]={113,114,102};// finConducta,finComportamiento,acciones

  public CpoEntero(Sintactico.AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis()throws Exception{
  IdEntero idEntero = new IdEntero(sintactico);
  OpAritmetico opAritmetico = new OpAritmetico(sintactico);
  OpEntero opEntero = new OpEntero(sintactico);;
  idEntero.analisis();
  sintactico.verifica_entrada(5, follow_set, header_set);
  if(verificar_follow()==true){
    do{
      opAritmetico.analisis();
      opEntero.analisis();
      sintactico.verifica_entrada(5, follow_set, header_set);
    }while(verificar_follow()==true);
  }
  }//fin de analisis()

  private boolean verificar_follow(){
  int i=0;
  for(i=0;i<4;i++)
      if(sintactico.compara(follow_set[i])==true)
        return true;
  return false;
  }

}// fin de la clase TInstrucciones
