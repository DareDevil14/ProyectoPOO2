package Lexico;


/* ********************
TTransiciones es la clase que se encarga de realizar la transición
de un caracter, reconociendo caracteres inválidos, o reconociento
una transición válida, lo que en consecuencia puede llevar a un token.
******************** */
class TablaTransiciones{
  // tabla de transiciones[24][19]
   private  int transiciones[][]={
  {0,1,3,4,5,6,7,8,12,14,10,16,17,18,19,20,21,22,-2,-2},
  {1,2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
  {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
  {8,-1,-1,-1,-1,-1,-1,-1,-1,9,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
  {10,-1,-1,-1,-1,-1,-1,-1,-1,11,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
  {12,-1,-1,-1,-1,-1,-1,-1,-1,13,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
  {14,-1,-1,-1,-1,-1,-1,-1,-1,15,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,21,-2,-2,24},
  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,22,22,23,-1}, // 22.- palabra
  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,22,22,-2,-1}, // 23.- palabra con _
  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,25,-2,-2,-2}, // 24.- números decimales
  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,25,-2,-2,-2}  // 25.- números decimales
  };
  public int i;
  public int estado;
  public int renglon;
  public boolean reducir;
  public StringBuffer cadena;
  public boolean estado_final;
  private int estado_anterior;
  private Herramientas.Errores error;

  public TablaTransiciones(Herramientas.Errores er){
    i=0;
    estado=0;
    estado_anterior=0;
    estado_final=false;
    cadena=new StringBuffer();
    reducir=false;
    renglon=1;
    error=er;
  }

  public void limpiar(){
    int y;
    i=0;
    estado=0;
    reducir=false;
    estado_final=false;
    cadena = new StringBuffer();
  }

  public void transicion(char caracter){
    recorre_caracter(caracter);
   switch(estado){
   // case 1, 2, 8,10,12
    case 3: case 4: case 5: case 6: case 7:
    case 9: case 11: case 13: case 15: case 16: case 17:
    case 18: case 19: case 20:
                    estado_final=true;
                    break;
    case -1: estado_final=true;
             estado=estado_anterior;
             reducir=true;
             break;
    case -2:
            cadena.insert(i,caracter);
            i++;
            error.insertar(renglon,"No es una expresión válida  '" + cadena+"'");
            limpiar();
            break;

    case 21: case 22: case 23: case 24: case 25:
                 estado_final=false;
                 cadena.insert(i,caracter);
                 i++;
                 break;
//       default: estado_final=false;
   }

  }


  private void recorre_caracter(char caracter){
    int j=0; // "j" es el valor de las columnas en la tabla transiciones[estado][j]
    estado_anterior=estado;
    switch (caracter){
      case '\t': case ' ': j=0;
                      estado=transiciones[estado][j];
                      break;
      case '/':j=1;
              estado=transiciones[estado][j];
              break;
      case '*':j=2;
              estado=transiciones[estado][j];
              break;
      case '-':j=3;
              estado=transiciones[estado][j];
              break;
      case '+':j=4;
              estado=transiciones[estado][j];
              break;
      case '&':j=5;
              estado=transiciones[estado][j];
              break;
      case '|':j=6;
              estado=transiciones[estado][j];
              break;
      case '>':j=7;
              estado=transiciones[estado][j];
              break;
      case '<':j=8;
              estado=transiciones[estado][j];
              break;
      case '=':j=9;
              estado=transiciones[estado][j];
              break;
      case '!':j=10;
              estado=transiciones[estado][j];
              break;
      case '(':j=11;
              estado=transiciones[estado][j];
              break;
      case ')':j=12;
              estado=transiciones[estado][j];
              break;
      case ':':j=13;
              estado=transiciones[estado][j];
              break;
      case ';':j=14;
              estado=transiciones[estado][j];
              break;
      case ',':j=15;
              estado=transiciones[estado][j];
              break;
      case '0': case '1': case '2': case '3':
      case '4': case '5': case '6': case '7':
      case '8': case '9':
                        j=16;
                        estado=transiciones[estado][j];
                        break;
      case 'a': case 'b': case 'c': case 'd': case 'e': case 'f': case 'g':
      case 'h': case 'i': case 'j': case 'k': case 'l': case 'm': case 'n':
      case 'o': case 'p': case 'q': case 'r': case 's': case 't': case 'u':
      case 'v': case 'w': case 'x': case 'y': case 'z': case 'A': case 'B':
      case 'C': case 'D': case 'E': case 'F': case 'G': case 'H': case 'I':
      case 'J': case 'K': case 'L': case 'M': case 'N': case 'O': case 'P':
      case 'Q': case 'R': case 'S': case 'T': case 'U': case 'V': case 'W':
      case 'X': case 'Y': case 'Z':
                j=17;
                estado=transiciones[estado][j];
                break;
      case '_':j=18;
              estado=transiciones[estado][j];
              break;
      case '.':j=19;
              estado=transiciones[estado][j];
              break;
      default:
              if(estado!=2)
                error.insertar(renglon,"El caracter: '" + caracter + "' no se encuentra definido dentro de la gramática");
              break;
   }

  }
}
