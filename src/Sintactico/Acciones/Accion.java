package Sintactico.Acciones;

public class Accion{
  private Sintactico.AnalizadorSintactico sintactico;
  private int follow_set[]={110,129,125,127,22,104,128,121,120,116,107};// finAccion,si,mientras,repite,variable,primitivas, entero, decimal
  private int header_set[]={111};// finAcciones

  public Accion(Sintactico.AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis()throws Exception{
  try{
    int renIni=0, renFin=0;
    ListaAccion listaAccion= new ListaAccion(sintactico);
    Sintactico.Enteros enteros= new Sintactico.Enteros(sintactico);
    Sintactico.Decimales decimales = new Sintactico.Decimales(sintactico);
    InstruccionCiclo2 instruccionCiclo2 = new InstruccionCiclo2(sintactico);
    sintactico.comparar(101);//accion
    sintactico.tabla_global_local_inicia();
    renIni=sintactico.renglon();
    listaAccion.analisis();
    renFin=sintactico.renglon();
    sintactico.compararDel(18,renIni,renFin);
    sintactico.verifica_entrada(107, follow_set, header_set);
    do{
      if (sintactico.compara(107))
        enteros.analisis(72); // variable de tipo: entero locales = 72
      if (sintactico.compara(116))
        decimales.analisis(82); // variable de tipo: decimal locales = 82
      sintactico.verifica_entrada(116, follow_set, header_set);
    }while(sintactico.compara(107) | sintactico.compara(116));    // si existen más declaraciones de "enteros" ó "decimales"
    sintactico.tabla_global_local(72,7); // vacia las variables locales a una tabla de símbolos local
    sintactico.tabla_global_local(82,8); // vacia las variables locales a una tabla de símbolos local

/*    if(sintactico.compara(107)){
      enteros.analisis(72);
      sintactico.tabla_global_local(72);
      }*/
    sintactico.verifica_entrada(129, follow_set, header_set); // PR = "si"
    instruccionCiclo2.analisis();
    sintactico.reporte_variables_locales(); // reporta errores y warnings del uso de variables locales (entero)
    sintactico.comparar(110);//finAccion
    // Genera código objeto
    sintactico.generaCodigoEnteroDecimalLocal();
    sintactico.finalizaSeccion();

    sintactico.imprimir_tabla_local();
  }
  catch(Sintactico.ExcepcionAtras ex){
    sintactico.error("No declaró correctamente la acción");
  }
/*  catch(Exception e){
    sintactico.error("No declaró correctamente un agente");
  }*/

  }

}// fin de la clase TAccion
