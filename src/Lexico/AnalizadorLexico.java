package Lexico;

/* ********************
 La clase AnalizadorLexico se encarga de realizar el anï¿½lisis lï¿½xico como primera
 fase de un compilador.  A ella se encuentra relacionada el codigo fuente, la
 identificaciï¿½n de palabras reservadas, la tabla de transiciones.
 ******************** */

public class AnalizadorLexico {
	public int pr;
	public int token;
	public int renglon;
	public static int idioma = 1; //default idioma 1 = español
	public static String cadena2;
	public String cadena;
	private CodigoFuente archivo;
	private TablaTransiciones tabla;
	private PalabrasReservadas palabras;
	private Herramientas.TablaSimbolos tabla_simbolos;

	public AnalizadorLexico(String ruta, Herramientas.Errores error,
			Herramientas.TablaSimbolos t_s) {
		archivo = new CodigoFuente(ruta, error);
		if (error.vacio()) {
			tabla = new TablaTransiciones(error);
			palabras = new PalabrasReservadas(error);
			tabla_simbolos = t_s;
		}
	}
	
//	public void setIdioma(int valor){
//		idioma = valor;
//	}
//	public int getIdioma(){
//		return idioma;
//	}
//	
	/*
	 * getToken(), funciï¿½n pï¿½blica que envia el siguiente token, peticiï¿½n que le
	 * hace el Analizador Sintï¿½ctico
	 */
	public boolean getToken() {
		boolean lee = true;

		renglon = 0;
		pr = 0;
		tabla.limpiar();
		do {
			if (archivo.lee_caracter() == 1) { // Si lee un caracter
				if (archivo.caracter == '\r' || archivo.caracter == '\n') { // si
																			// lee
																			// un
																			// <enter>
																			// (
																			// <return>
																			// )
					tabla.renglon++; // se encuentra en un nuevo renglï¿½n
					archivo.lee_caracter();
					if (tabla.estado == 0 || tabla.estado == 2)
						tabla.limpiar();
					else {
						renglon = -1;
						tabla.estado_final = true;
					}
				} else {
					tabla.transicion(archivo.caracter);
				} // realiza la transiciï¿½n del caracter
			} else {
				tabla.estado_final = true;
				if (tabla.estado == 0)
					lee = false;
			}
		} while (tabla.estado_final == false);
		cadena = new String(tabla.cadena);
		if(idioma == 2)
		{
		//{
			cadena2 = cadena;
			cadena=TranslateCod(cadena);
		}
			//cadena2 = TranslateCod(cad);
		//}
		//System.out.println("-----------Analizador: "+cadena);
		renglon = renglon + tabla.renglon;
		token = tabla.estado;
		if (tabla.reducir == true)
			archivo.i--;
		if (tabla.estado == 22) { // verifica si se trata de una palabra
									// reservada
			pr = palabras.buscar(cadena, cadena.length());
			if (pr == 0 & tabla_simbolos.buscar(cadena) == false) // No es
																	// palabra
																	// reservada
				tabla_simbolos.insertar(cadena, 0, 0); // Y no se ha incluï¿½do en
														// la tabla de sï¿½mbolos
		}
		return lee;
	}// Fin del Mï¿½todo getToken

	public void finalize() {
		archivo = null;
		tabla = null;
		palabras = null;
		// tabla_simbolos = null;
		System.out.println("Finalizador de TLexico.");
	}
	
	public String TranslateCod(String d){
		String var=d;
		switch(d){
			case "action":
				var="accion";
				break;
			case "actions":
				var="acciones";
				break;
			case "move":
				var="avanza";
				break;
			case "conduct":
				var="conducta";
				break;
			case "int":
				var="entero";
				break;
			case "then":
				var="entonces";
				break;
			case "end":
				var="fin";
				break;
			case "endAction":
				var="finAccion";
				break;
			case "endActions":
				var="finAcciones";
				break;
			case "endConduct":
				var="finConducta";
				break;
			case "endBehavior":
				var="finComportamiento";
				break;
			case "float":
				var="decimal";
				break;
			case "turnRight":
				var="giraDer";
				break;
			case "turnLeft":
				var="giraIzq";
				break;
			case "do":
				var="hacer";
				break;
			case "star":
				var="inicio";
				break;
			case "while":
				var="mientras";
				break;
			case "else":
				var="otro";
				break;
			case "repeat":
				var="repite";
				break;
			case "back":
				var="retrocede";
				break;
			case "solicit":
				var="solicita";
				break;
			case "if":
				var="si";
				break;
			case "light":
				var="luz";
				break;
			case "temperature":
				var="temperatura";
				break;
			case "times":
				var="veces";
				break;			
			case "walk":
				var="caminar";
				break;	
			case "agent1":
				var="agente1";
				break;
			case "agent2":
				var="agente2";
				break;
			case "avoid_obstacle":
				var="evitar_obstaculos";
				break;
			case "society":
				var="sociedad";
				break;
			case "agent":
				var="agente";
				break;
			case "endAgent":
				var="finAgente";
				break;
			case "sensor_front":
				var="sensor_frente";
				break;
			case "endSociety":
				var="finSociedad";
				break;
			case "behavior":
				var="comportamiento";
				break;
			case "tactile":
				var="tactil";
				break;	
			case "sensor_light":
				var="sensor_luz";
				break;
			case "step_forward":
				var="dar_un_paso";
				break;
			case "turn":
				var="gira";
				break;
		}
		return var;
	}
	//Español-inglés
	public String TranslateCodSE(String d){
		String var=d;
		switch(d){
				case "accion":
					var = "action";	
				break;
				case "acciones":
					var = "actions";
				break;
				case "avanza":
					var = "move";
				break;
				case "conducta":
					var = "conduct";
				break;
				case "entero":
					var = "int";
					
					break;
				case "entonces":
					var = "then";
					
					break;
				case "fin":
					var = "end";
					
					break;
				case "finAccion":
					var = "endAction";
					
					break;
				case "finAcciones":
					var = "endActions";
					
					break;
				case "finConducta":
					var = "endConduct";
					
					break;
				case "finComportamiento":
					var = "endBehavior";
					
					break;
				case "decimal":
					var = "float";
					
					break;
				case "giraDer":
					var = "turnRight";
					
					break;
				case "giraIzq":
					var = "turnLeft";
					
					break;
				case "hacer":
					var = "do";
					
					break;
				case "inicio":
					var = "star";
					
					break;
				case "mientras":
					var = "while";
					
					break;
				case "otro":
					var = "else";
					
					break;
				case "repite":
					var = "repeat";
					
					break;
				case "retrocede":
					var = "back";
					
					break;
				case "solicita":
					var = "solicit";
					
					break;
				case "si":
					var = "if";
					
					break;
				case "luz":
					var = "light";
					
					break;
				case "temperatura":
					var = "temperature";
					
					break;
				case "veces":
					var = "times";
					
					break;			
				case "caminar":
					var = "walk";
					
					break;	
				case "agente1":
					var = "agent1";
					
					break;
				case "agente2":
					var = "agent2";
					
					break;
				case "evitar_obstaculos":
					var = "avoid_obstacle";
					
					break;
				case "sociedad":
					var = "society";
					
					break;
				case "agente":
					var = "agent";
					
					break;
				case "finAgente":
					var = "endAgent";
					
					break;
				case "sensor_frente":
					var = "sensor_front";
					
					break;
				case "finSociedad":
					var = "endSociety";
					
					break;
				case "comportamiento":
					var = "behavior";
					
					break;
				case "tactil":
					var = "tactile";
					
					break;	
				case "sensor_luz":
					var = "sensor_light";
					
					break;
				case "dar_un_paso":
					var = "step_forward";
					
					break;
				case "gira":
					var = "turn";
					
					break;
		}
		return var;
	}
	
	

}// fin de la clase TLexico
