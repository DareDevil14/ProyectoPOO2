
package Lejos;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;


public class Archivo {
	
	private String directorio;
	private String cadena;
	private String variables;
	String archivoAgente;
	private DataOutputStream output;
	private FileOutputStream fileout;
	private PrintStream printout;
	private FileWriter archivo = null;
	private PrintWriter pw = null;
	private BufferedWriter buff= null;
	private Shell shell = new Shell();
	private int numAgente;
	Archivo(String direc, int numAgente) {
		  directorio = direc;
		  cadena = "";
		  variables = "";
		  this.numAgente = numAgente;
		  crearArchivo(numAgente);
	}
	
	  private void crearArchivo(int numAgente){
		    archivoAgente =  "Agente"+numAgente+".java";
		    try{
		    	//shell.ejecutaComando("cd "+directorio+" & "+"mkdir Agente"+numAgente);
		    	fileout = new FileOutputStream(directorio+"Agente"+numAgente+"\\"+archivoAgente);
		    	printout = new PrintStream(fileout);

		    }
		    catch (IOException e){
		    }
		  }
	  
	  public void escribirCodigo(String cadena) throws IOException{
		  System.out.println(cadena);
		  //escribir el archivo .java
		  printout.println(cadena);
		  //ejecutar el comando nxj para generar el archivo
		  System.out.println(archivoAgente);
		  try{
			  shell.ejecutaComando("cd "+directorio+"Agente"+numAgente+" & "+"nxjc "+archivoAgente+" & "+"nxjlink -o Agente"+numAgente+".nxj "+"Oraculo");
		  }
		  catch(Exception e){
			  System.out.println("Verifique la instalación de Lejos en su sistema");
		  }
	  }

		  private void cerrarArchivo(){
		    try{
		        output.close();
		    }
		    catch (IOException e){
		    }
		  }
}
