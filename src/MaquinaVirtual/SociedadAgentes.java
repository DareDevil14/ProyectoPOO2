package MaquinaVirtual;

import Herramientas.TablaSimbolos;
import java.util.Vector;

public class SociedadAgentes {

  String directorio;
  String archivo;
  int numAge;
  ArchivoObjeto in;
  TablaSimbolos tablaSensores; // Sensores de los agentes
  TablaSimbolos tablaEDGlobal; // Enteros y Decimales globales
  TablaSimbolos tablaAcciones;
  public Vector agentes;
  SueloCanvas suelo;

  public SociedadAgentes(String directorio, String archivo, SueloCanvas suelo){
    this.directorio = directorio;
    this.archivo = archivo;
    numAge =0;
    tablaSensores = new Herramientas.TablaSimbolos(); // inicia la tabla de Sensores
    tablaEDGlobal = new Herramientas.TablaSimbolos(); // inicia la tabla de Enteros y Decimales globales
    tablaAcciones = new Herramientas.TablaSimbolos(); // inicia la tabla de las Acciones
    this.suelo = suelo;
//    in = new ArchivoObjeto(directorio+archivo+".Conductas.Age2000.obj");
    in = new ArchivoObjeto(directorio+"\\"+archivo+".age"+".Sociedad.Age2000.obj");
    initTablaSensores();
    initTablaEDGlobal();
    initTablaAcciones();
    initAgentes();
  }

  public int countAgentes(){
    return numAge;
  }

  private void initAgentes(){
    int i=-1;
    String nombreAge = "", nombreCond = "";
    numAge = in.getCodigo();
    agentes = new Vector(numAge);
    int caracter= in.getCodigo();
    if(caracter == 92){ // "\" Iniciar la búsqueda de los agentes con sus condudctas
      do{
          i++;
          nombreAge = in.getVariable(); // contiene el nombre del agente
          Agente temp = new Agente(nombreAge, directorio, tablaSensores, tablaAcciones, tablaEDGlobal,i, suelo);
          agentes.insertElementAt(temp,i);
          TablaSimbolos tablaConductas = new TablaSimbolos();
          caracter = in.getCodigo();
          if(caracter==94){ // si inicia la declaración de las conductas
            do {
              nombreCond = in.getVariable(); // contiene el nombre del agente
              tablaConductas.insertar(nombreCond,0,0);
              caracter = in.getCodigo();
            }while (caracter != -1 & caracter == 94); // "^" conductas
          }
          temp.setTablaConductas(tablaConductas);
      }while(caracter != -1 & caracter == 92);
      suelo.setAgentes(i+1);
    }
  }

  private void initTablaSensores(){
    String variable= "";
    int id=0;
    int tipo = 0;
    int caracter= in.getCodigo();
    if(caracter == '?'){ // Si se declararon los sensores
      caracter = in.getCodigo();
      do{
          if (caracter == '&') { // Indicador de sensores
            variable = in.getVariable();
            id = in.getCodigo();
            tipo = in.getCodigo();
            tablaSensores.insertar(variable,tipo,0,0.0,id);
          }
          caracter = in.getCodigo();
      }while(caracter != -1 & caracter != '?');
      tablaSensores.imprimir(); // Sólo para depurar
      in.getCodigo(); //  conocer el siguiente caracter, para el reconocimiento de las siguientes tablas
    }
  }

  private void initTablaEDGlobal(){
    String variable= "";
    int id=0, tipo = 0;
    double valor= 0.0;
//    int caracter= in.getCodigo();
    int caracter = in.getOnlyCodigo();
    if(caracter == 64){ // '@'  Si se declaran las variables tipo Entero y Decimal
      caracter = in.getCodigo();
      do{
          if (caracter == '$') { // Indicador de sensores
            variable = in.getVariable();
            id = in.getCodigo();
            tipo = in.getCodigo();
            valor = in.getNumero();
            tablaEDGlobal.insertar(variable,tipo,0,valor,id);
          }
          caracter = in.getCodigo();
      }while(caracter != -1 & caracter != 64);
      tablaEDGlobal.imprimir(); // Sólo para depurar
      in.getCodigo();
    }
  }

  private void initTablaAcciones(){
    String variable= "";
    int id=0;
//    int caracter= in.getCodigo();
    int caracter = in.getOnlyCodigo();
    if(caracter == 126){ // "~"  Si se declararon las acciones (mínimo debe haber una)
      caracter = in.getCodigo();
      do{
          if (caracter == '*') { // Indicador de sensores
            variable = in.getVariable();
            id = in.getCodigo();
            tablaAcciones.insertar(variable,0,0,0.0,id); //String n, int t, int v, double v2, int ccid
          }
          caracter = in.getCodigo();
      }while(caracter != -1 & caracter == '*');
      System.out.println("IMPRIMIR TABLA ACCIONES----------------------------->>>>>>");
      tablaAcciones.imprimir(); // Sólo para depurar
      System.out.println("------------------------------------------->>>>>>");
    }
  }

  public String getNombreAgente(int i){
    Agente aux;
    aux = (Agente) agentes.elementAt(i);
    return aux.getNombreAgente();
  }

  public void startAgentes(){ // Iniciar el funcionamiento de los agentes
    for (int i = 0; i < agentes.capacity(); i++) {  // iniciar el funcionamiento de los sensores
      Agente aux;
      aux = (Agente) agentes.elementAt(i);
      aux.start();
    }
  }

  public void suspendAgentes(){ // Parar a los agentes
    for (int i = 0; i < agentes.capacity(); i++) { // Suspende el funcionamiento de los sensores
      Agente aux;
      aux = (Agente) agentes.elementAt(i);
      aux.suspend();
    }
  }

  public void resumeAgentes(){ // Parar a los agentes
    for (int i = 0; i < agentes.capacity(); i++) { // Suspende el funcionamiento de los sensores
      Agente aux;
      aux = (Agente) agentes.elementAt(i);
      aux.resume();
    }
  }

  public void stopAgentes(){ // Parar a los agentes
    for (int i = 0; i < agentes.capacity(); i++) { // Parar el funcionamiento de los sensores
      Agente aux;
      aux = (Agente) agentes.elementAt(i);
      aux.stop();
    }
    tablaEDGlobal.imprimir();
  }


}
