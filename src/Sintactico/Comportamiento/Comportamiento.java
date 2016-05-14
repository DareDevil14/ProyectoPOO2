package Sintactico.Comportamiento;

public class Comportamiento{
  private Sintactico.AnalizadorSintactico sintactico;
  private int follow_set[]={105,132,133,134,136,137,138,107,116};//conducta, finConducta, tactil, temperatura, luz, SENSOR123, entero, decimal
  private int header_set[]={102};// acciones

  public Comportamiento(Sintactico.AnalizadorSintactico sint){
  sintactico = sint;
  }

  public void analisis() throws Exception{
  try{
  int renIni=0, renFin=0;
  Sensores sensores= new Sensores(sintactico);
  Conductas conductas = new Conductas(sintactico);
  Sintactico.Enteros enteros= new Sintactico.Enteros(sintactico);
  Sintactico.Decimales decimales = new Sintactico.Decimales(sintactico);
  renIni=sintactico.renglon();
  sintactico.comparar(106); // "comportamiento"
//sintactico.comparar(18);
  renFin=sintactico.renglon();
  sintactico.compararDel(18,renIni,renFin);
// Ingresa los 8 sensores integrados al agente
  sintactico.ingresarSensores();
// Verifica la existencia de sensores
  sintactico.verifica_entrada(132, follow_set, header_set);
  if(sintactico.compara(132) || sintactico.compara(133) || sintactico.compara(134)){
    sensores.analisis();
  }
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
  // Unicia el cuerpo de las condcutas
  sintactico.verifica_entrada(105, follow_set, header_set); // verifica si el token es "conducta"
  conductas.analisis(); // analiza conductas
  sintactico.reporte_globales(7); // reporta warnings - sin uso de variables globales (entero)
  sintactico.reporte_globales(8); // reporta warnings - sin uso de variables globales (decimal)
  sintactico.reporte_globales(4); // reporta warnings - sin uso de variables globales (tactil)
  sintactico.reporte_globales(5); // reporta warnings - sin uso de variables globales (luz)
  sintactico.reporte_globales(6); // reporta warnings - sin uso de variables globales (temperatura)
  sintactico.reporte_globales(2); // reporta errores de conductas declaradas sin desarrollar

  // Genera código de los sensores
  sintactico.generaCodigoSensores();
  // Genera código de las variables globales de tipo enteros y decimales
  sintactico.generaCodigoEnteroDecimal();

  // imprime la tabla de símbolos sólo para depuración
  sintactico.imprimir_tabla_simbolos();

  // se elimina los tipos "entero" y "decimal", y esto es para que los nombres utilizados para ellas
  // se puedan volver a utilizar en otra sección
  sintactico.eliminar_tabla_simbolos(7);// elimina las variables globales de tipo entero
  sintactico.eliminar_tabla_simbolos(8);// elimina las variables globales de tipo decimal
  sintactico.comparar(114); //finComportamiento
  }
  catch(Sintactico.ExcepcionSalirParte ex){
    sintactico.error("No Finalizó correctamente la declaración de Comportamiento");
  }
  catch(Sintactico.ExcepcionAtras ex){
    sintactico.error("ExcepcionAtras e de Comportamiento.java");
  }
/*  catch(Exception e){
    sintactico.error("Exception e de TComportamiento.java");
  }*/
 }

}// fin de la clase Comportamiento
