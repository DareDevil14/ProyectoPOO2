package Lexico;

import java.io.*;

/* ********************
La clase CodigoFuente se encarga de manejar el archivo fuente, es decir,
es el encargado de abrir el archivo fuente, leer los caracteres del
archivo fuente, cerrar el archivo fuente.
******************** */

class CodigoFuente{

  public int i;
  public char caracter;
  private int tamanio;
  private char[] myBuff;
  private BufferedReader in;
  private Herramientas.Errores error;

  public CodigoFuente(String ruta, Herramientas.Errores err){
    tamanio=100;
    i=0;
    myBuff = new char[tamanio];
    error = err;
    try{
      in = new BufferedReader(
           new InputStreamReader(
           new FileInputStream(ruta)));
      lee_archivo();
    }
    catch (IOException e){
      error.insertar(0,"No se abrió bien el archivo fuente");
    }
  }


  private void lee_archivo(){
  int y;
    for(y=0; y < myBuff.length; y++)
        myBuff[y]='\0';
    try{
      in.read(myBuff);
    }
    catch (EOFException eof){  // No detecta el fin del archivo aquí.
      cierra_archivo();
    }
    catch (IOException e){
      error.insertar(0,"No se puede leer el archivo");
    }
  }


  public int lee_caracter(){
    int lectura=0; // lectura es una variable local que detecta en caso de ser:
    do{   // 0:no realiza ninguna lectura 1:lectura de caracter 2: fin de archivo
      if (i<myBuff.length){ // El mejor de los casos, existe un caracter
        if(myBuff[i]!='\0'){  // qué leer
          caracter=myBuff[i];
          i++;
          lectura=1;
        }
        else{   // Se detecta el fin de archivo
          cierra_archivo();
          lectura=2;
          }
      }
      else { // Realiza una nueva lectura al archivo
        lee_archivo();
        i=0;
      }
    }while(lectura==0);
    return lectura;
  }

  private void cierra_archivo(){
    try{
      in.close();
    }
    catch (IOException e){
      error.insertar(0,"No cerró bien el archivo");
    }
  }

}//fin de la clase TFuente
