package Sintactico.Acciones;

public class Acciones{
  private Sintactico.AnalizadorSintactico sintactico;
  private int follow_set[]={101,110,111,107,116};//accion,finAccion,finAcciones,entero,decimal
  private int header_set[]={102};// acciones

  public Acciones(Sintactico.AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis() throws Exception{
  try{
  int renIni=0, renFin=0;
  Motores motores= new Motores(sintactico);
  DeclAccion declAcciones = new DeclAccion(sintactico);
  Sintactico.Enteros enteros= new Sintactico.Enteros(sintactico);
  Sintactico.Decimales decimales = new Sintactico.Decimales(sintactico);
  renIni=sintactico.renglon();
  sintactico.comparar(102);//acciones
//  sintactico.comparar(18);
  renFin=sintactico.renglon();
  sintactico.compararDel(18,renIni,renFin);
  motores.analisis();

  // Verifica la existencia de enteros
// Verifica la existencia de decimales
  sintactico.verifica_entrada(107, follow_set, header_set);
  do{
    if (sintactico.compara(107))
      enteros.analisis(7); // variable de tipo:entero = 7
    if (sintactico.compara(116))
      decimales.analisis(8); // variable de tipo:decimal = 8
    sintactico.verifica_entrada(116, follow_set, header_set);
  }while(sintactico.compara(107) | sintactico.compara(116));    // si existen más declaraciones de "enteros" ó "decimales"
//  sintactico.verifica_entrada(101, follow_set, header_set);
  declAcciones.analisis();//accion
  sintactico.reporte_globales(7); // reporta warnings - sin uso de variables globales (entero)
  sintactico.reporte_globales(8); // reporta warnings - sin uso de variables globales (decimal)
  sintactico.reporte_globales(17); // reporta warnings - sin uso de variables globales (motor)
  sintactico.reporte_globales(3); // reporta errores de acciones declaradas sin desarrollar

  // Genera código de las variables globales de tipo enteros y decimales
  sintactico.generaCodigoEnteroDecimal();

  // imprime la tabla de símbolos sólo para depuración
  sintactico.imprimir_tabla_simbolos();

  // las variables de tipo "entero" y "decimal" se eliminan
  // para que el nombre de nuevas variables globales en otra sección sean permitidas
  sintactico.eliminar_tabla_simbolos(7);// elimina las variables globales de tipo entero
  sintactico.eliminar_tabla_simbolos(8);// elimina las variables globales de tipo decimal
  sintactico.comparar(111); //finAcciones
  }
  catch(Sintactico.ExcepcionSalirParte ex){
    sintactico.error("No Finalizó correctamente la declaración de Acciones");
  }
  catch(Sintactico.ExcepcionAtras ex){
    sintactico.error("TExcepcionAtras e de TComportamiento.java");
  }
 }

}// fin de la clase TAcciones
