package com.compimac.editor;

import Herramientas.Arbol;
import java.io.*;


public class ColorPR {

  private BufferedReader fuente;
  private Arbol arbol;

  public ColorPR() {
    String ruta;
    arbol = new Arbol();
//    ruta = "c:\\compilador_IDE\\src\\IDE" + "\\colorPR.dat";
    ruta = "/cfg/colorPR.dat";    
    try{
      fuente = new BufferedReader(
           new InputStreamReader(
           new FileInputStream(ruta)));
//           new FileInputStream("C:/compilador/Lexico/reservadas.dat")));
      vaciar();
    }
    catch (IOException e){
    }
  }

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
      catch (EOFException eof){  // No detecta el fin del archivo aqu.
        cierra_archivo();
      }
      catch (IOException e){
      }
    }// fin del mtodo vaciar

    public int buscar(String cadena, int tam){
          return arbol.buscar(cadena, tam);
    }

    private void cierra_archivo(){
      try{
        fuente.close();
      }
      catch (IOException e){
      }
  }

}
