package Lexico;

import java.io.*;

class PalabrasReservadas{

  private BufferedReader fuente;
  private Herramientas.Arbol arbol;
  private Herramientas.Errores error;

//Constructor de la clase TPReservadas
  public PalabrasReservadas(Herramientas.Errores err){
    arbol = new Herramientas.Arbol();
    String ruta = "/cfg/reservadas.dat";
    error = err;
    
    try{
        InputStream inputStream = PalabrasReservadas.class.getResourceAsStream(ruta);
      fuente = new BufferedReader( // abre el archiv
           new InputStreamReader(
           inputStream));
      vaciar(); // organiza las palabras reservadas en un árbol
    }
    catch (Exception e){
         e.printStackTrace();
     System.out.println("Exception :: " + e.getMessage() );
      error.insertar(0,"No se abrió bien el archivo palabras reservadas");
    }

  }

/* Funci�n vaciar()
 * Esta funci�n se realiza de forma interna, ya que una vez abierto el archivo
 * donde se encuentran las palabras reservadas, �sta las organiza en un �rbol
 * para realizar la b�squeda de palabras reservadas optimizando.
*/
  private void vaciar(){
    String cadena;
    int cad_num=0;
    try{
      cadena=fuente.readLine();
      do{
        cad_num=Integer.parseInt(fuente.readLine());
        arbol.insertarNodo(cadena,cad_num,cadena.length());
        cadena=fuente.readLine();
      }while(cadena!=null);
      cierra_archivo();
    }// fin del try
    catch (EOFException eof){  // No detecta el fin del archivo aqu�.
      cierra_archivo();
    }
    catch (IOException e){
      error.insertar(0,"No se puede leer el archivo");
    }
  }// fin del m�todo vaciar

  public int buscar(String cadena, int tam){
        return arbol.buscar(cadena, tam);
  }

  private void cierra_archivo(){
    try{
      fuente.close();
    }
    catch (IOException e){
      error.insertar(0,"No cerró bien el archivo");
    }
  }

}
