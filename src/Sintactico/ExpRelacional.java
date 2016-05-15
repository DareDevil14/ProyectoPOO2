package Sintactico;

public class ExpRelacional{
  private Sintactico.AnalizadorSintactico sintactico;
  private int follow_set[]={12,8,13,9,15,11,6,7,10,124,108,122};// op_relacional,inicio,entonces,hacer
  private int header_set[]={113,114,102};// finConducta,finComportamiento,acciones

  public ExpRelacional(Sintactico.AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis()throws Exception{
  Operacion operacion = new Operacion(sintactico);
  OpRelacional opRelacional = new OpRelacional(sintactico);
  operacion.analisis();
  sintactico.verifica_entrada(12, follow_set, header_set);
  if(verificar_follow()==true){
      opRelacional.analisis();
      operacion.analisis();
  }
  }//fin de analisis()


  private boolean verificar_follow(){
  int i=0;
  for(i=0;i<6;i++)
      if(sintactico.compara(follow_set[i])==true)
        return true;
  return false;
  }

}// fin de la clase TInstrucciones
