package Lejos;

import java.io.IOException;
import java.util.Vector;

import Herramientas.TablaSimbolos;


public class Traductor {
	
	String directorio;
	String nombre;
	
	String claseInicio = "";//almacena nombre de clase
	String claseAcciones = "";//almacena la clase de Lejos GeneraObjetoLejos
	String sensor = ""; //almacena un sensor
	String sensores = "";//almacena todos los sensores
	
	//GENERA LOS METODOS PARA CADA COMPORTAMIENTO
	String comportamientoInicio = "";//almacena nombre metodos comportamientos
	String ejecutaAccion = "";//almacena una llamada a una accion()
	//String ejecutaAcciones = "";//almacena todas las llamadas a acciones
	String comportamientoFin = "";//cierra llave de comportamientos
	String comportamientos = "";//almacena  todos los comportamientos
	String ejecutarComp = "";
	
	//GENERA LOS METODOS ACCIONES
	String accionInicio = "";//crea los métodos de las acciones
	String generaAccion = "";//manda a llamar el método de la clase GeneraObjetoLejos dependiendo del caso
	String accionFIn = "";//cierra llave de acciones
	String acciones = "";//almacena todas los metodos de acciones
	
	//GENERA EL MAIN PRINCIPAL
	String mainInicio= ""; //método principal main
	String mainFin = "";//cierra llave del main
	String claseFin = "";
	
	String claseAgenteN="";
	String claseORACULO="";
	
	
	
	SociedadAgentes sociedad;
	//Siempre va a haber un solo Agente
	//Agente agente;
	Vector agentes;
	Conducta conductaN;
	Accion accionN;
	TablaSimbolos tablaConductas;
	TablaSimbolos tablaAcciones;
	TablaSimbolos tablaEDglobal;
	TablaSimbolos tablaSensores;
	boolean discoExt = false;
	
	int n = 0;
	GeneraObjetoLejos generar = new GeneraObjetoLejos();
	Archivo archivo;
	
	public Traductor(String nom, String direct){
		nombre = nom;
		directorio = direct;
		
		//Crea la sociedad de Agentes especificando la ruta y el nombre del archivo
		//también dentro de la clase se inicializan las tablas
		sociedad = new SociedadAgentes(directorio, nombre);
		
		
		agentes = sociedad.agentes;
//		if(directorio.contains("\\"))
//		{
//				directorio = directorio.replace("\\", " ");
//				discoExt = true;
//		}
//			
		Shell shell = new Shell();
		for(int i = 1; i <= agentes.capacity(); i++)
		{
			//if(discoExt)
			shell.ejecutaComando("cd "+directorio+" & "+"mkdir Agente"+i);
//			else
//			{
//				//directorio = directorio.replace("\\", " ");
//				//System.out.println(directorio);
//				shell.ejecutaComando(directorio+" & "+"mkdir Agente"+i);
//			}
				
			
		}
	}
	
	
	public void traduce() throws IOException{
		//comportamientos+= "\n\t/*****ACCIONES****/";
		System.out.println("SOCIEDAD DE AGENTES");
		for(int i = 1; i <= sociedad.tablaAcciones.count(); i++){
			System.out.println(sociedad.tablaAcciones.getNombre(i));
			Accion temp = new Accion();
			temp.initial2(directorio, sociedad.tablaAcciones.getNombre(i), sociedad.tablaEDGlobal);
			temp.start();
			acciones +="\t" + temp.getAccionRobot();
		}
		System.out.println("SOCIEDAD DE AGENTES");
		//for para los agentes
		for(int x = 0; x < agentes.capacity(); x++){	
			Agente agente = (Agente)agentes.elementAt(x);
				
			//En esta parte se declaran la cabecera y los paquetes a importar
			claseInicio = ""
			+ "/*****************************************************************\n"
			+ "* Esta clase contiene el código fuente de Lejos generado a partir	*\n"
			+ "* de código objeto del compilador AGE								*\n"
			+ "* El sensor tactil debe estar conectado en el puerto 1				*\n"
			+ "* El sensor luz debe estar conectado en el puerto 2					*\n"
			+ "**********************************************************************/\n\n"
			+"import lejos.nxt.*;\n"
//			+"import java.util.*;\n"
			+ "import lejos.robotics.subsumption.*;\n\n"
			+ "";
			
			//Se crea la clase Oráculo que va a ir verificando los valores de los sensores en su propio Thread
			claseORACULO =""+
			"\nclass Oraculo{"+
			"\n			"+
			"\n		int velocidad = 200; //velociad Motor"+
			"\n		static Behavior avanza = new Avanzar();"+
			"\n		static Behavior giraIzquierda = new GiraIzquierda();"+
			"\n		static Behavior giraDerecha = new GiraDerecha();"+
			"\n		static Behavior retrocede = new Retroceder();"+
			"\n		static Behavior detiene = new Detener();"+
			"\n		static Behavior [] bArray = { detiene,avanza,retrocede,giraIzquierda,giraDerecha};"+
			"\n		static Arbitrator oraculo = new Arbitrator(bArray);"+
			"\n		"+
			"\n		public static void main(String args[]){"+
			"\n			AgeLejos agente = new AgeLejos();"+
			"\n			agente.start();"+
			"\n			oraculo.start();"+
			"\n		}"+
			"\n	}\n\n";		
			
			//Se crea la clase de Agentes(uno por cada Agente declarado)		
			claseAgenteN=""+
			"class AgeLejos extends Thread{\n"+
			"\n\tstatic TouchSensor sensorTactil =new TouchSensor(SensorPort.S1);"+
			"\n\tstatic LightSensor sensorLuz = new LightSensor(SensorPort.S2);"+
			"\n\tstatic TemperatureSensor sensorTemp = new TemperatureSensor(SensorPort.S3);";
			
			
			comportamientos += "\n\t/******COMPORTAMIENTOS*****/";
			for(int i= 0; i < agente.conductas.size();i++)
			{
				conductaN = (Conducta) agente.conductas.get(i);
				//Ejecutar la conducta n para generar su código correspondientes métodos...
				conductaN.run();
				
				comportamientoInicio	= "\n\t"
				+ "static void "+conductaN.getName()+"(){\n";	
				//casos de repite,mientras,si
				ejecutaAccion = "\t\t"+conductaN.getCondicion();
				ejecutarComp += "\t\t\t"+conductaN.getName()+"();\n";
				comportamientoFin="\n\t}\n";
				comportamientos += comportamientoInicio + ejecutaAccion+comportamientoFin;
			}
		
			accionInicio += "\n\n/************ACCIONES*****************/\n";
			mainInicio = "\t\tpublic void run(){\n"+
			"\n\t\twhile(!Button.ENTER.isPressed()){\n"+
			ejecutarComp +"\n\t\t}\n\t\tThread.yield();\n";
			mainFin = "\n\t}\n";
			claseFin = "\n}";
		
			//código final que se va a generar
			String codigoFinal = claseInicio+claseORACULO+claseAgenteN+claseAcciones+comportamientos+accionInicio+acciones+mainInicio+mainFin+claseFin+claseAvanza+claseRetrocede+claseGiraDerecha+claseGiraIzquierda+claseDetener;
			archivo = new Archivo(directorio,(x+1));
			archivo.escribirCodigo(codigoFinal);
			claseInicio = "";
			claseORACULO = "";
			claseAgenteN = "";
			claseAcciones = "";
			ejecutarComp = "";
			comportamientos = "";
			accionInicio = "";
			//acciones = "";
			mainInicio = "";
			mainFin = "";
			claseFin = "";
		}
	}
	
	
/****************************************************************************************|
 * En esta parte están declaradas las clases estáticas que contienen los comportamientos |
 * Avanzar, Retroceder, GiraIzquierda, GiraDerecha y Detener							 |
 * *************************************************************************************/
	
	String claseAvanza=""+			
	"\nclass Avanzar implements Behavior{"+
	"\n	static boolean condicion = false;\n"+
	"\n	public boolean takeControl() {"+
	"\n		//String st = String.valueOf(condicion);"+
	"\n			if(condicion)"+
	"\n				return true;"+
	"\n			return false;"+
	"\n		}"+
	"\n		"+
	"\n		public void action() {"+
	"\n			Motor.A.rotate(360,true);"+
	"\n			Motor.C.rotate(360,true);"+
	"\n			"+
	"\n			try{"+
	"\n				Motor.A.waitComplete();"+
	"\n				Thread.sleep(10);"+
	"\n			}catch(Exception e){}"+
	"\n"+
	"\n			Motor.A.stop();"+
	"\n			try{"+
	"\n				Motor.C.waitComplete();"+
	"\n				Thread.sleep(10);"+
	"\n			}catch(Exception e){}	"+
	"\n			Motor.C.stop();"+
	"\n			GiraIzquierda.condicion = false;"+
	"\n			GiraDerecha.condicion = false;"+
	"\n			Retroceder.condicion = false;"+
	"\n			condicion =false;"+
	"\n		}"+
	"\n	"+
	"\n		public void suppress() {"+
	"\n			//condicion = false;"+
	"\n			Motor.A.stop();"+
	"\n			Motor.B.stop();"+
	"\n		}"+
	"\n		"+
	"\n}";
	
	String claseRetrocede=""+			
	"\nclass Retroceder implements Behavior{"+
	"\n		static boolean condicion = false;\n"+
	"\n		public boolean takeControl() {"+
	"\n			//String st = String.valueOf(condicion);"+
	"\n			if(condicion)"+
	"\n				return true;"+
	"\n			return false;"+
	"\n		}"+
	"\n		"+
	"\n		public void action() {"+
	"\n			Motor.A.backward();"+
	"\n			Motor.C.backward();"+
//	"\n			Motor.A.rotate(-360,true);"+
//	"\n			Motor.C.rotate(-360,true);"+
//	"\n			"+
//	"\n			try{"+
//	"\n				Motor.A.waitComplete();"+
//	"\n				Thread.sleep(10);"+
//	"\n			}catch(Exception e){}"+
//	"\n"+
//	"\n			Motor.A.stop();"+
//	"\n			try{"+
//	"\n				Motor.C.waitComplete();"+
//	"\n				Thread.sleep(10);"+
//	"\n			}catch(Exception e){}	"+
//	"\n			Motor.C.stop();"+
	"\n			Avanzar.condicion = false;"+
	"\n			GiraIzquierda.condicion = false;"+
	"\n			GiraDerecha.condicion = false;"+
	"\n			condicion =false;"+
	"\n		}"+
	"\n	"+
	"\n		public void suppress() {"+
	"\n			//condicion = false;"+
	"\n			Motor.A.stop();"+
	"\n			Motor.B.stop();"+
	"\n		}"+
	"\n		"+
	"\n}";
	
	String claseGiraIzquierda=""+			
	"\nclass GiraIzquierda implements Behavior{"+
	"\n"+
	"\n		static boolean condicion = false;\n"+
	"\n		public boolean takeControl() {"+
	"\n			String st = String.valueOf(condicion);"+
	"\n			if(condicion)"+
	"\n			return true;"+
	"\n		return false;"+
	"\n		}"+
	"\n		"+
	"\n		public void action() {"+
	"\n			Motor.A.backward();       "+
	"\n			Motor.C.backward();       "+
	"\n			try{Thread.sleep(300);}catch(Exception e) {}  "+     
	"\n			// Rotate by causing only one wheel to stop:"+       
	"\n			Motor.A.stop();       "+
	"\n			try{Thread.sleep(1000);}catch(Exception e) {}"+       	
	"\n			Motor.C.stop();"+ 
	"\n			Avanzar.condicion = false;"+
	"\n			GiraDerecha.condicion = false;"+
	"\n			Retroceder.condicion = false;"+
	"\n			condicion = false;"+
	"\n		}"+
	"\n		"+
	"\n		public void suppress() {"+
	"\n			//condicion = false;"+
	"\n			Motor.A.stop();"+
	"\n			Motor.C.stop();"+
	"\n		}"+
	"\n		"+
	"\n}";
	
	String claseGiraDerecha=""+			
	"\nclass GiraDerecha implements Behavior{"+
	"\n"+
	"\n		static boolean condicion = false;\n"+
	"\n		public boolean takeControl() {"+
	"\n			String st = String.valueOf(condicion);"+
	"\n			if(condicion)"+
	"\n			return true;"+
	"\n		return false;"+
	"\n		}"+
	"\n		"+
	"\n		public void action() {"+
	"\n			Motor.A.backward();       "+
	"\n			Motor.C.backward();       "+
	"\n			try{Thread.sleep(300);}catch(Exception e) {}  "+     
	"\n			// Rotate by causing only one wheel to stop:"+       
	"\n			Motor.C.stop();       "+
	"\n			try{Thread.sleep(1000);}catch(Exception e) {}"+       	
	"\n			Motor.A.stop();"+ 
	"\n			Avanzar.condicion = false;"+
	"\n			GiraIzquierda.condicion = false;"+
	"\n			Retroceder.condicion = false;"+
	"\n			condicion = false;"+
	"\n		}"+
	"\n		"+
	"\n		public void suppress() {"+
	"\n			//condicion = false;"+
	"\n			Motor.A.stop();"+
	"\n			Motor.C.stop();"+
	"\n		}"+
	"\n		"+
	"\n}";

	String claseDetener =""+
	"\n	class Detener implements Behavior{"+	
	"\n		public boolean takeControl() {"+
	"\n			if(Button.ENTER.isPressed())"+
	"\n				return true;"+
	"\n			return false;"+
	"\n		}"+
	"\n			"+
	"\n		public void action() {"+    
	"\n			Motor.A.stop();     "+     	
	"\n			Motor.C.stop(); "+
	"\n			Avanzar.condicion = false;"+
	"\n			GiraDerecha.condicion = false;"+
	"\n			GiraIzquierda.condicion = false;"+
	"\n			Retroceder.condicion = false;"+
	"\n			System.exit(0);"+
	"\n		}"+
	"\n			"+
	"\n		public void suppress() {"+
	"\n			Motor.A.stop();"+
	"\n			Motor.C.stop();"+
	"\n		}"+
	"\n}";
	
}