package Herramientas;

import java.io.*;

public class GeneracionCodigo {
  private Errores errores;
  private CompSociedad lista;
  private DataOutputStream output;
  private String directorio;
  private String cadena;
  private String variables;

  public GeneracionCodigo(String direc, Errores err) {
  directorio = direc;
  errores = err;
  cadena = "";
  variables = "";
  }

  public void IniciaSeccion(String nombreArchivo){
    if(errores.getCountNodo()==0){ // si no existen errores
      cadena = "";
      crearArchivo(nombreArchivo);
    }
  }

  public void setCodigo(int codigo){
    cadena = cadena + (char) codigo;
  }

  public void setLongCodigo(int codigo){
    String temp;
    temp = cadena;
    cadena = "";
    int i, corchete;
    for(corchete = 1,  i=temp.length()-1 ; corchete >= 1; i--){
      if(temp.charAt(i)=='{'){  // verifica que cuente a partir del corchete correcto
        corchete--;
      }
      if(temp.charAt(i)=='}'){
        corchete++;
      }
    }
//    Una vez hallado el corchete correcto, se procede a manipular la cadena
    int longitud = temp.length() - i -1;
    i= i+2;
    cadena = temp.substring(0,i); // copia la cadena + `{`
    cadena = cadena + (char) longitud; // longitud de la cadena donde inicia el corchete
    cadena = cadena + temp.substring(i, temp.length()) + (char) codigo;
  }

  public void setLongCondicion(){
    String temp;
    temp = cadena;
    int i, llave, corchete, count;
    count = 0;
    for(llave = 1,  i=temp.length()-2 ; llave >= 1; i--){
      if(temp.charAt(i)=='{'){  // verifica que cuente a partir del corchete correcto
        llave--;
      }
      if(temp.charAt(i)=='}'){
        llave++;
      }
    }
    for(corchete = 1, i--; corchete >= 1; i--){
      if(temp.charAt(i)=='['){  // verifica que cuente a partir del corchete correcto
        corchete--;
      }
      if(temp.charAt(i)==']'){
        corchete++;
      }
    }
    count = temp.length()-i;
    cadena = cadena + (char) count;
  }


  public void setVariableLocal(String tabla){
    cadena = tabla + cadena;
  }

  public void FinalizaSeccion(){
    try{
    if(errores.getCountNodo()==0){ // si no existen errores
      output.writeBytes(cadena);
      output.writeByte(0); // Buscar el colocar el fin de archivo
      cerrarArchivo();
    }
    }
    catch (IOException e){
    }
  }

  /*
   * La siguiente función crea 2 archivos
   * 1.- Relaciones de los agentes con sus conductas
   * 2.- Relaciones de las conductas con sus acciones
   */

  public void setCodigoSociedad(int codigo){
    variables = variables + (char) codigo;
  }

  public void setCodigoSociedad(String codigo){
    variables = variables + codigo;
  }

/*
 * Para el caso de crear el archivo de sociedad,
 * se da un tratamiento a variables globales para incluir esa tabla en el archivo
 */
  public void checkVariables(){
    boolean band = false;
    int i;
    for(i=0; i<variables.length()-1; i++){
      if(variables.charAt(i)=='@' & variables.charAt(i+1)=='@'){
        band = true;
        break;
      }
    }
    if(band) variables = variables.substring(0,i) + variables.substring(i+2,variables.length());
  }

  public void archivoSociedad(CompSociedad agentes, String nombreArchivo){
  lista = agentes;
  try{
    if(errores.getCountNodo()==0){ // si no existen errores
      if(variables !="") checkVariables();
      crearArchivo(nombreArchivo);
      output.writeBytes(variables);
      output.writeBytes(lista.getCodigo());
      cerrarArchivo();
      variables = "";
    }
  }
  catch (IOException e){
  }
  }


/*
 * Los siguientes archivos son básicos
 * Abren y Cierran los archivos que se crean con codigo objeto
 */
  private void crearArchivo(String nombreArchivo){
    String ruta= directorio + "\\" + nombreArchivo + ".Age2000.obj";
    try{
      if(errores.getCountNodo()==0){ // si no existen errores
        output = new DataOutputStream(new FileOutputStream(ruta));
      }
    }
    catch (IOException e){
    }
  }

  private void cerrarArchivo(){
    try{
      if(errores.getCountNodo()==0){ // si no existen errores
        output.close();
      }
    }
    catch (IOException e){
    }
  }

}
