package MaquinaVirtual;

import java.io.*;


/* ********************
La clase ArchivoObjeto se encarga de manejar la lectura de los archivos
que contienen el código objeto.
Es el encargado de abrir el archivo, leer los caracteres del
archivo, cerrar el archivo.
******************** */

class ArchivoObjeto{

private int i; // índice del buffer
private int j;  // indice del archivo
private String ruta;
private int codigo;
private int tamanio;
private char[] myBuff;
private BufferedReader in;

public ArchivoObjeto(String ruta){
  tamanio=100;
  i=0;
  j=0;
  this.ruta = ruta;
  myBuff = new char[tamanio];
  try{
     
    in = new BufferedReader(
         new InputStreamReader(
         new FileInputStream(ruta)));
    lee_archivo();
  }
  catch (IOException e){
    // "No se abrió bien el archivo fuente"
  }
}

public int getIndice(){
  return j;
}

private void abrir_archivo(){
  try{
    in = new BufferedReader(
         new InputStreamReader(
         new FileInputStream(ruta)));
    lee_archivo();
  }
  catch (IOException e){
    // "No se abrió bien el archivo fuente"
  }
}

public void setCodigoIndice(int indice){
  if(i-indice>=0){
    i = i-indice;
  }
  else{
    int temp = j-indice;
    i = j= 0;
    cierra_archivo();
    abrir_archivo();
    getIndex(temp);
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
    // "No se puede leer el archivo"
  }
}

public int getIndex(int indice){
  int valor=0;
  for(int i=0; i<indice; i++){
    valor = getCodigo();
  }
  return valor;
}

public int getCodigo(){
  int lectura=0; // lectura es una variable local que detecta en caso de ser:
  do{   // 0:no realiza ninguna lectura 1:lectura de caracter 2: fin de archivo
    if (i<myBuff.length){ // El mejor de los casos, existe un caracter
      if(myBuff[i]!='\0'){  // exsite qué leer
        codigo=(int)myBuff[i];
        i++;
        j++;
        lectura=1;
      }
      else{   // Se detecta el fin de archivo
        cierra_archivo();
        lectura=2;
        codigo = -1;
        }
    }
    else { // Realiza una nueva lectura al archivo
      lee_archivo();
      i=0;
    }
  }while(lectura==0);
  return codigo;
}

public int getOnlyCodigo(){
  return codigo;
}

public String getVariable(){
  String variable="";
  int caracter = getCodigo();
  if(caracter == 34){ // "
    caracter = getCodigo();
    do{
      variable = variable + (char)caracter;
      caracter = getCodigo();
    }while(caracter!= 34); // "
  }
  return variable;
}

public double getNumero(){
  String variable="";
  int caracter = getCodigo();
  if(caracter == '#'){ // "
    caracter = getCodigo();
    do{
      variable = variable + (char)caracter;
      caracter = getCodigo();
    }while(caracter!= '#'); // "
  }
  return Double.parseDouble(variable);
}

public int getNumeroInt(){
  String variable="";
  int caracter = getCodigo();
  if(caracter == '#'){ // "
    caracter = getCodigo();
    do{
      variable = variable + (char)caracter;
      caracter = getCodigo();
    }while(caracter!= '#'); // "
  }
  return Integer.parseInt(variable);
}

public double getNumeroOp(){
  String variable="";
  int caracter = getCodigo();
  do{
      variable = variable + (char)caracter;
      caracter = getCodigo();
  }while(caracter!= '#'); // "
  return Double.parseDouble(variable);
}


public void cierra_archivo(){
  try{
    in.close();
  }
  catch (IOException e){
    // "No cerró bien el archivo"
  }
}


}
