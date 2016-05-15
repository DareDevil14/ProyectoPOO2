package Sintactico.Sociedad;

public class ProgramaAge{
  private Sintactico.AnalizadorSintactico sintactico;


  public ProgramaAge(String directorio, String nombreSociedad, Lexico.AnalizadorLexico lexx, Herramientas.TablaSimbolos t_s, Herramientas.Errores err, Herramientas.Errores warn){
  sintactico= new Sintactico.AnalizadorSintactico(directorio, nombreSociedad, lexx, t_s, err, warn);

  }

  public void analisis(){
  try{
  Inicio inicio= new Inicio(sintactico);
  inicio.analisis();
  sintactico.comparar(0);
  sintactico.imprimir_tabla_simbolos();
  sintactico.imprimir_sociedad();
  sintactico.generarArchivosCabecera();
  }
  catch(Sintactico.ExcepcionTerminar ex){
    sintactico.error("El programa no está completo");
  }
  catch(Sintactico.ExcepcionAtras ex){
    sintactico.error("TExcepcionAtras ProgramaAge.java");
  }
  catch(Exception e){
     System.out.println(""+ e.getMessage()); 
     e.printStackTrace();
    sintactico.error("Exception ProgramaAge.java");
  }
  }

}// fin de la clase TSintactico
