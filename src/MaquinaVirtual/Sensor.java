package MaquinaVirtual;


class Sensor extends Thread{
  // Sensores de variables declaradas
  private final int TACTIL = 4;
  private final int LUZ = 5;
  private final int TEMPERATURA = 6;

  // Sensores naturales de los agentes
  private final int INFRARROJO = 9;
  private final int TACTILIZQ = 10;
  private final int TACTILDER = 11;
  private final int TACTILTRA = 12;
  private final int FOTOCELDAFI = 13;
  private final int FOTOCELDAFD = 14;
  private final int FOTOCELDATI = 15;
  private final int FOTOCELDATD = 16;

//  Orientación
  static final int IZQUIERDA = 4; //tactil
  static final int DERECHA = 5;  //tactil
  static final int ATRAS = 7;  //tactil
  static final int FRENTE = 2;

  static final int ATRASDER = 8; // luz
  static final int ATRASIZQ = 6; // luz
  static final int FRENTEDER = 1; // luz
  static final int FRENTEIZQ = 3; // luz

  Agente agente;
  private int tipo;
  private int id;
  private double valor;
  Herramientas.TablaSimbolos tablaSensores;

  public Sensor(Agente agente, String nombre, int tipo, int id, Herramientas.TablaSimbolos tablaSensores){
    super(nombre);
    this.agente = agente;
    this.tipo = tipo;
    this.id = id;
    this.tablaSensores = tablaSensores;
  }

  public void run(){
    int valor=0;
    for(;;){ // ciclo para mantener activos los sensores permanentemente
      switch (tipo) {
        case TACTIL:
          if (agente.getTactil(FRENTE))
            setValor(1);
          else setValor(0);
          System.out.println("TACTIL "+ getName()+"--> "+ valor);
          break;
        case LUZ:
          setValor(agente.getLuz(FRENTE));
          break;
        case TEMPERATURA:
          setValor(agente.getTemperatura(FRENTE));
          break;
        case INFRARROJO:
          break;
        case TACTILIZQ:
          if (agente.getTactil(IZQUIERDA))
            setValor(1);
          else setValor(0);
          System.out.println("TACTILIZQ "+ valor);
          break;
        case TACTILDER:
          if (agente.getTactil(DERECHA))
            setValor(1);
          else setValor(0);
          System.out.println("TACTILDER " + valor);
          break;
        case TACTILTRA:
          if (agente.getTactil(ATRAS))
            setValor(1);
          else setValor(0);
          System.out.println("ATRAS "+ valor);
          break;
        case FOTOCELDAFI:
          valor = agente.getLuz(FRENTEIZQ);
          if(valor==0) valor = agente.getLuz(IZQUIERDA);
          setValor(valor);
          break;
        case FOTOCELDAFD:
          valor = agente.getLuz(FRENTEDER);
          if(valor==0) valor = agente.getLuz(DERECHA);
          setValor(valor);
          break;
        case FOTOCELDATI:
          valor = agente.getLuz(ATRASIZQ);
          if(valor==0) valor = agente.getLuz(IZQUIERDA);
          setValor(valor);
          break;
        case FOTOCELDATD:
          valor = agente.getLuz(ATRASDER);
          if(valor==0) valor = agente.getLuz(DERECHA);
          setValor(valor);
          break;
      }
    }
  }


  private void setValor(double valor){
    this.valor= valor;
    tablaSensores.actualiza_Id_valor2(id,valor);
  }


}
