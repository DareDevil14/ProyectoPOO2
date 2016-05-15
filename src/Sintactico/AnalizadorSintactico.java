package Sintactico;

import Lexico.AnalizadorLexico;

public class AnalizadorSintactico{
  private Lexico.AnalizadorLexico lexico;
  private Herramientas.Errores errores;
  private Herramientas.Errores warnings;
  private Herramientas.TablaSimbolos tabla_simbolos;
  private Herramientas.CompSociedad lista_agentes;
  private Herramientas.CompSociedad lista_conductas;
//  private Herramientas.TCompSociedad lista_conductas;
  private Herramientas.TablaSimbolos tabla_simbolos_local;
  private Herramientas.GeneracionCodigo generarCodigo;
  private String agente;
  private String nombreSociedad;
  private String nombreConducta;
  private boolean band_error;
//  band

  public AnalizadorSintactico(String directorio, String sociedadN, Lexico.AnalizadorLexico lexx, Herramientas.TablaSimbolos t_s, Herramientas.Errores err, Herramientas.Errores warn){
  agente="";
  nombreSociedad = sociedadN;
  lista_agentes = new Herramientas.CompSociedad();
  lista_conductas = new Herramientas.CompSociedad();
  generarCodigo = new Herramientas.GeneracionCodigo(directorio, err);
  lexico = lexx;
  tabla_simbolos = t_s;
  errores = err;
  warnings = warn;
  getToken();
  }

  public int renglon(){
  return lexico.renglon;
  }

  public void error(String cadena){
  errores.insertar(lexico.renglon, cadena);
  }

  public void warning(String cadena){
  warnings.insertar(lexico.renglon, cadena);
  }

  public void getToken(){
  band_error=false;
//  band=false;
  lexico.getToken();
  }

  public void comparar(int token){
    if(lexico.pr!=0){   // comparación de palabras reservadas
      if(token==lexico.pr)
        getToken();
      else
        reporta_error(token);
    }else{
      if(token==lexico.token)    // comparación de tokens
        getToken();
      else
        reporta_error(token);
      }
  }

  public void comparar(int token, int error){ // match
    if(lexico.pr!=0){   // comparación de palabras reservadas
      if(token==lexico.pr)
        getToken();
      else
        reporta_error(error);
    }else{
      if(token==lexico.token)    // comparación de tokens
        getToken();
      else
        reporta_error(error);
      }
  }

  public void compararDel(int token, int renIni, int renFin)throws Exception{
  try{
    if(token==lexico.token){    // comparación de tokens
      if(renIni!=renFin)    // comparación de tokens
        throw new ExcepcionAtras();
      else
        getToken();
    }
    else
      reporta_error(token);
  }
  catch(ExcepcionAtras ex){
	if(AnalizadorLexico.idioma ==1)
		error("El delimitador se encuentra fuera de sitio");
	else{error("The boundary is located off-site");}
    getToken();
  }
  }

// Compara el token sin lanzar ningún error
  public boolean compara(int token){  // función que verifica (compara) un token
    if(lexico.pr!=0){   // comparación de palabras reservadas
      if(token==lexico.pr)
        return true;
      else
        return false;
    }else{
      if(token==lexico.token)    // comparación de tokens
        return true;
      else
        return false;
      }
  }

  public void verifica_entrada(int token_actual, int[] follow_set, int[] header_set)throws GrupoExcepcion, Exception{
  int i;
  if(!compara(token_actual)){   // Si es diferente al token actual
    for(i=0; i<follow_set.length; i++){
      if(compara(follow_set[i]))
        return; // Sigue el procedimiento
    }
    for(i=0; i< header_set.length; i++){
      if(compara(0)){
        System.out.println("Lanza la excep TExcpTerminar");
        throw new ExcepcionTerminar();}
      if(compara(102) && header_set[i]==102){
        System.out.println("Lanza la excep 102");
        throw new ExcepcionSalirParte();}
      if(compara(header_set[i])){
        System.out.println("Lanza la excep ExceptionAtras");
        throw new ExcepcionAtras();}
/*      if(compara(106) && header_set[i]==106){
        System.out.println("Lanza la excep TExcpSalir");
        throw new TExcepcionSalirParte();}*/
    }
    if(lexico.pr!=0){
    	if(AnalizadorLexico.idioma==1)
    	      error("Identificador no válido \"" + lexico.cadena + "\"");
    	else{
    		error("Identify is not valid \"" + lexico.cadena2 + "\"");   		
    	}
    	    	
    }    
    else
      identificador();
    getToken();
    verifica_entrada(token_actual, follow_set, header_set);
  }
  else
    return ;   // Entrada correcta
  }// fin del método verifica_entrada

  private void identificador(){
  switch(lexico.token){
  case 2:   break;
  case 4:  if(AnalizadorLexico.idioma==1)error("Identificador no válido \"-\"");
  			else{error("Identify is not valid \"-\"");}
            break;
  case 6:  if(AnalizadorLexico.idioma==1)error("Identificador no válido \"&\"");
	else{error("Identify is not valid \"&\"");}
            break;
  case 7:  if(AnalizadorLexico.idioma==1)error("Identificador no válido \"|\"");
	else{error("Identify is not valid \"|\"");}
            break;
  case 10:  if(AnalizadorLexico.idioma==1)error("Identificador no válido \"!\"");
	else{error("Identify is not valid \"!\"");}
            break;
  case 14:  if(AnalizadorLexico.idioma==1)error("Identificador no válido \"=\"");
	else{error("Identify is not valid \"=\"");}
            break;
  case 15: if(AnalizadorLexico.idioma==1)error("Identificador no válido \"==\"");
	else{error("Identify is not valid \"==\"");}  
            break;
  case 16: if(AnalizadorLexico.idioma==1)error("Identificador no válido \"(\"");
	else{error("Identify is not valid \"(\"");}  
            break;
  case 17: if(AnalizadorLexico.idioma==1)error("Identificador no válido \")\"");
	else{error("Identify is not valid \")\"");}  
            break;
  case 18: if(AnalizadorLexico.idioma==1)error("Identificador no válido \":\"");
	else{error("Identify is not valid \":\"");}  
            break;
  case 19:  if(AnalizadorLexico.idioma==1)error("Identificador no válido \";\"");
	else{error("Identify is not valid \";\"");}
            break;
  case 20:  if(AnalizadorLexico.idioma==1)error("Identificador no válido \",\"");
			else{error("Identify is not valid \",\"");}
            break;
  case 21:case 22: case 25: if(AnalizadorLexico.idioma==1)error("Identificador no válido \""+ lexico.cadena +"\"");
	else{error("Identify is not valid \""+lexico.cadena2+"\"");}
	  
            break;
  case 23:  if(AnalizadorLexico.idioma==1)error("Identificador no válido \""+ lexico.cadena +"\"");
	else{error("Identify is not valid \""+lexico.cadena2+"\"");}
            break;
  default: if(AnalizadorLexico.idioma==1)error("Identificador no válido SIMBOLO NO ESPECIFICADO: " + lexico.token);
	else{error("Identify is not valid symbol unspecified: "+ lexico.token );}  
	  
            break;
  }
  }

  public void tabla_global_local_inicia(){
  tabla_simbolos_local = new Herramientas.TablaSimbolos();
  }

  public int actualiza_tsimbolo_uso(String nom_var){
  if(tabla_simbolos_local.buscar(nom_var)==true)
    return tabla_simbolos_local.actualiza_uso(nom_var,true);
  else
    return tabla_simbolos.actualiza_uso(nom_var,true);
  }

  public void tabla_global_local(int tipo, int nvotipo){
  String variable;
  int valor;
  double valor2;
  int id;
//  tabla_simbolos_local = new Herramientas.TTSimbolos();
  while(tabla_simbolos.buscar_tipo(tipo)==true){
      variable = new String(tabla_simbolos.buscar_nombre(tipo));
      valor = tabla_simbolos.buscar_var_valor(variable);
      valor2 = tabla_simbolos.buscar_var_valor2(variable);
      id = tabla_simbolos.buscar_var_id(variable);
      tabla_simbolos_local.insertar(variable, nvotipo, valor, valor2, id);
      tabla_simbolos.eliminar(variable);
  }
  }

  public int getIdTablaSimbolos(){
    int id =0;
    id = tabla_simbolos_local.buscar_var_id(lexico.cadena);
    if(id==-1){
      id = tabla_simbolos.buscar_var_id(lexico.cadena);
    }
    return id;
  }

  public int es_variable(){
  if(lexico.pr==0){
    if(lexico.token==22)
      return tabla_simbolos.buscar_var_tipo(lexico.cadena);
    else
      return -2; // no es una variable
  }
  else
    return -1; // es una palabra reservada
  }

  public void actualiza_tipo(int tipo)  {
    tabla_simbolos.actualiza_tipo(lexico.cadena, tipo);
  }


  public void variables(int tipo, int nvo_tipo, int[] follow_set, int[] header_set)throws Exception{
  int tipo_var=0;
  tipo_var = es_variable();
  switch(tipo_var){
    case -1:verifica_entrada(22, follow_set, header_set); // es una palabra reservada
            throw new ExcepcionPR();
    case -2:throw new ExcepcionAtras(); // no es variable ni palabra reservada
    default: if(tipo==tipo_var){ // si aún no se le ha registrado el tipo de la variable
                actualiza_tipo(nvo_tipo); // se actualiza el tipo de la variable
                if(nvo_tipo==1){
                  agente=lexico.cadena;
                  lista_agentes.insertar_lista(lexico.cadena);
                }
                if(nvo_tipo==3){ // inserta en la lista de componentes (conductas y agentes)
                    // verifica que se trate del agente actual a analizar
                    if(nombreConducta.compareTo(lista_conductas.getAgente())==0 & nombreConducta!=""){
                      lista_conductas.insertar_elemento(lexico.cadena);
                    }
                }
            }
            else{
             if(nvo_tipo==1){
              agente=lexico.cadena;
              if(AnalizadorLexico.idioma==1)
            	  error("Ha multiplicado la declaración del agente \""+lexico.cadena+"\"");
              else{error("It has multiple declaration agent \""+lexico.cadena2+"\"");}
              }
              if((nvo_tipo<10 & tipo_var>50) | (nvo_tipo>50 & tipo_var<10)){
            	  if(AnalizadorLexico.idioma==1)error("Es confuso el uso de la variable \""+lexico.cadena+"\" declarada local y global ");
            	  else{error("It is confusing to use the variable \""+lexico.cadena2+"\" local and global declared ");}
              }                 
              else
                  if(nvo_tipo!=tipo_var){
                	  if(AnalizadorLexico.idioma==1)error("La variable \""+lexico.cadena+"\" está declarada con otro tipo");
                	  else{error("the variable \""+lexico.cadena2+"\" is declared with other type");}
                  }
            }
            if(nvo_tipo==2){ // inserta en la lista de componentes (agente y sus conductas)
                // verifica que se trate del agente actual a analizar
                if(agente.compareTo(lista_agentes.getAgente())==0 & agente!="")
                  if(lista_agentes.insertar_elemento(lexico.cadena)==false){
                	  if(AnalizadorLexico.idioma==1)error("No debe repetir las conductas en la declaración del agente \""+lexico.cadena+"\"");
                	  else{error("Not repeat conducts in the declaration agent \""+lexico.cadena2+"\"");}
                  }
              }
            if(nvo_tipo==3){ // cuando se hace un llamado a una acción, independientemente ssi es la primera vez o no
              setCodigoObjeto(getIdTablaSimbolos()); // Id de la variable
            }
/*             else
              band=true;*/
            getToken();
            break;
  }
  }

/* Función exclusiva para los sensores
 * Ya esta dada de alta la variable del sensor (SENSOR_123)
 * actualiza el valor del sensor (ya sea SENSOR_1, SENSOR_2, SENSOR_3)
 */
  private void actualiza_valor(String dato, int valor)throws Exception{
  int temp=0;
  if(valor>0){
    temp=tabla_simbolos.buscar_var_valor(dato);
    if(temp==0){
      if(tabla_simbolos.buscar_tipo_valor(4, valor)==false &
         tabla_simbolos.buscar_tipo_valor(5, valor)==false &
         tabla_simbolos.buscar_tipo_valor(6, valor)==false )
         tabla_simbolos.actualiza_valor(dato, valor);
      else{
        if(AnalizadorLexico.idioma==1)error("El SENSOR_"+ valor +" ya ha sido ocupado");
        else{error("El SENSOR_"+ valor +" is busy");}
      }
    }
    else{
      if(temp==valor){
        if(AnalizadorLexico.idioma==1)warning("Ha multiplicado la declaración del sensor \""+ dato +"\"");
        else{warning("It has multiply the declaration sensor \""+ dato +"\"");}
      }
      else{
    	  if(AnalizadorLexico.idioma==1)error("No se puede realizar la asociación con el SENSOR_"+valor);
    	  else{error("You can not make the association with the SENSOR_"+valor);}
      }
    }
  }
  else
    throw new ExcepcionAtras();
  }

  public void sensores(int tipo, int nvo_tipo, int[] follow_set, int[] header_set)throws Exception{
  try{
  String var_sensor;
  var_sensor = new String(lexico.cadena);
  if(var_sensor.compareTo("")!=0)
    variables(0,nvo_tipo, follow_set, header_set);// es un sensor
  else
     error("No especificó el nombre de la variable del sensor");
  comparar(16); // "("
  verifica_entrada(136, follow_set, header_set);
  actualiza_valor(var_sensor, num_sensor());
  comparar(17); // ")"
  }
  catch(ExcepcionPR ex){
    error("No se permite usar Palabras Reservadas como variables");
  }
  }

  public boolean existe_tipo(int tipo){
    return tabla_simbolos.buscar_tipo(tipo);
  }

  public int getTipoVariable(){
   int tipo_var=0;
   tipo_var = tabla_simbolos_local.buscar_var_tipo(lexico.cadena); // lo busca inicialmente en la tabla de simbolos locales
   if(tipo_var==-1)
     tipo_var=es_variable(); // lo busca inicialmente en la tabla de simbolos locales
   return tipo_var;
  }

  public void existe_var_numero(int tipo, int[] follow_set, int[] header_set)throws Exception{
  int tipo_var=0;
  tipo_var = tabla_simbolos_local.buscar_var_tipo(lexico.cadena); // lo busca inicialmente en la tabla de simbolos locales
  if(tipo_var==-1) // si no lo encuentra en variables locales, entonces busca en la tabla de símbolos global
     existe_var_tipo(tipo, follow_set, header_set);
  else{
     tabla_simbolos.eliminar(lexico.cadena);
     actualiza_tsimbolo_uso(lexico.cadena);
     getToken();
     }
  }

  public int getTipo(int tipo){
  int tipo_var=0;
  tipo_var = tabla_simbolos_local.buscar_var_tipo(lexico.cadena); // lo busca inicialmente en la tabla de simbolos locales
  if(tipo_var==-1){ // no lo encuentra en la tabla de símbolos local
      tipo_var = tabla_simbolos.buscar_var_tipo(lexico.cadena); // lo busca inicialmente en la tabla de simbolos global
      if(tipo_var==-1){ // no lo encuentra en la tabla de símbolos global
        switch(tipo){
          case 7: error("La variable \""+lexico.cadena+"\" no está declarada (entero)");
                  break;
          case 8: error("La variable \""+lexico.cadena+"\" no está declarada (decimal)");
                  break;
        }
      }
  }
  if(tipo_var!=tipo){
    switch(tipo){
      case 7: error("La variable \""+lexico.cadena+"\" no está declarada como tipo entero");
              break;
      case 8: error("La variable \""+lexico.cadena+"\" no está declarada como tipo decimal)");
              break;
    }
    getToken();
  }
  return tipo_var;
  }

  public void existe_var_tipo(int tipo, int[] follow_set, int[] header_set)throws Exception{
  int tipo_var=0;
  tipo_var = es_variable();
  switch(tipo_var){
    case -1:verifica_entrada(22, follow_set, header_set);
            throw new ExcepcionPR();
    case -2:throw new ExcepcionAtras();
    default:if(tipo_var!=tipo & tipo_var!=0)
              switch(tipo){
                case 2:error("La variable \""+lexico.cadena+"\" no es de tipo conducta");
                        break;
                case 4:case 5:case 6:error("La variable \""+lexico.cadena+"\" no es de tipo sensor");
                        break;
                case 7:error("La variable \""+lexico.cadena+"\" no es de tipo entero");
                        break;
                case 8:error("La variable \""+lexico.cadena+"\" no es de tipo decimal");
                        break;

                default:error("NO HA SIDO DECLARADO - SINTACTICO.EXISTE_VAR_TIPO() tipo="+ tipo);
                        break;
              }
             if(tipo_var==0 & tipo!=2 & tipo!=3) // especifica tipos de variables que se deben de declarar expresamente
                error("La variable \""+lexico.cadena+"\" no ha sido declarada");
             if(tipo_var==0 & tipo==2)
                warning("La conducta \""+lexico.cadena+"\" no ha sido solicitada por ningún agente");
             if(tipo_var==0 & tipo==3)
                warning("La acción \""+lexico.cadena+"\" no ha sido solicitada por ninguna conducta");
              if(tipo_var==tipo){
                int valor;
                valor=actualiza_tsimbolo_uso(lexico.cadena);
                if((tipo_var==2 | tipo_var==3) & valor==2){ // conductas y acciones especificadas más de una vez en su espacio
                  error("Ha multiplicado la declaración de \"" + lexico.cadena + "\"");
                }
                else{
                  if((tipo_var==2 | tipo_var==3) & valor==1){ // conductas y acciones al declararse sus secciones
                    if (tipo_var == 2) {
                      nombreConducta = lexico.cadena; // inicia la lista de conducta con sus acciones
                      lista_conductas.insertar_lista(lexico.cadena);
                    }
                    if (errores.getCountNodo() == 0)
                      generarCodigo.IniciaSeccion(lexico.cadena);
                  }
                }
              }
              getToken();
              break;
  }// fin del 1er. switch
  }// fin de la función

// Ingresa los 8 sensores naturales que tiene cada agente
  public void ingresarSensores(){
    String cadSensor [] = {"tactilIzq","tactilDer","tactilTra","infrarrojo","fotoCeldaFI","fotoCeldaFD","fotoCeldaTI","fotoCeldaTD"};
    int tipos []= {10,11,12,9,13,14,15,16};
    for(int i=0; i<8; i++)
      tabla_simbolos.insertar(cadSensor[i],tipos[i],0);
  }



  public void var_entero(int tipo, int nvo_tipo, int[] follow_set, int[] header_set)throws Exception{
  try{
  String var_entero;
  int temp = 1;
  var_entero = new String(lexico.cadena);
  variables(0,nvo_tipo, follow_set, header_set);// es un entero
  if(compara(14)){  // "=" -- Si le asignan valor a la variable entero
    comparar(14);
    verifica_entrada(21, follow_set, header_set);
    if(compara(4)){  // "-" --si es un número declarado negativo
      comparar(4);
      temp=-1;
      }
    if(compara(21)){ // espera el valor
      temp = Integer.parseInt(lexico.cadena)*temp;
//      actualiza_valor_entero(var_entero, Integer.parseInt(lexico.cadena));
      actualiza_valor_entero(var_entero, temp);
      getToken();
      }
    else
      error("No especificó el valor inicial de la variable de tipo entero");
  }
/*  if(band==true)
    warning("Ha multiplicado la declaración del entero \""+ var_entero +"\"");*/
  }
  catch(ExcepcionPR ex){
    error("No se permite usar Palabras Reservadas como variables");
  }
  }

  public void var_decimal(int tipo, int nvo_tipo, int[] follow_set, int[] header_set)throws Exception{
  try{ // para decimales 8 es su tipo
  String var_decimal;
  int temp = 1;
  double valor = 0.0;
  var_decimal = new String(lexico.cadena);
  variables(0,nvo_tipo, follow_set, header_set);// es un entero
  if(compara(14)){  // "=" -- Si le asignan valor a la variable entero
    comparar(14);
    verifica_entrada(25, follow_set, header_set);
    if(compara(4)){  // "-" --si es un número declarado negativo
      comparar(4);
      temp=-1;
      }
    if(compara(25) || compara(21)){ // espera el valor decimal
      valor = Double.parseDouble(lexico.cadena)*temp;
//      actualiza_valor_entero(var_entero, Integer.parseInt(lexico.cadena));
      actualiza_valor_decimal(var_decimal, valor);
      getToken();
      }
    else
      error("No especificó el valor inicial de la variable de tipo decimal");
  }
/*  if(band==true)
    warning("Ha multiplicado la declaración del entero \""+ var_decimal +"\"");*/
  }
  catch(ExcepcionPR ex){
    error("No se permite usar Palabras Reservadas como variables");
  }
  }


  private void actualiza_valor_entero(String dato, int valor)throws Exception{
  int temp=0;
   temp=tabla_simbolos.buscar_var_valor(dato);
    if(temp==0){
         tabla_simbolos.actualiza_valor(dato, valor);
//         getToken();
    }
    else{
      if(temp==valor)
        warning("Ha multiplicado la declaración de la variable tipo entero \""+ dato +"\"");
      else
        error("El entero \""+ dato +"\" ya ha sido declarado con otro valor de inicio");
    }

  }

  private void actualiza_valor_decimal(String dato, double valor)throws Exception{
  double temp=0.0;
   temp=tabla_simbolos.buscar_var_valor2(dato);
    if(temp==0.0){
         tabla_simbolos.actualiza_valor2(dato, valor);
//         getToken();
    }
    else{
      if(temp==valor)
        warning("Ha multiplicado la declaración de la variable tipo decimal \""+ dato +"\"");
      else
        error("El decimal \""+ dato +"\" ya ha sido declarado con otro valor de inicio");
    }

  }

  private int num_sensor(){
  if(compara(136)){
    comparar(136);
    return 1;}// SENSOR_1
  if(compara(137)){
    comparar(137);
    return 2;}// SENSOR_2
  if(compara(138)){
    comparar(138);
    return 3;}// SENSOR_3
  return -1;
  }

  public void motores(int tipo, int nvo_tipo, int[] follow_set, int[] header_set)throws Exception{
  try{
  String var_motor;
  var_motor = new String(lexico.cadena);
  variables(0,nvo_tipo, follow_set, header_set);// es un motor
  comparar(16);
  verifica_entrada(139, follow_set, header_set);
  actualiza_valor_motor(var_motor, num_motor());
  comparar(17);
  }
  catch(ExcepcionPR ex){
    error("No se permite usar Palabras Reservadas como variables");
  }
  }

  private void actualiza_valor_motor(String dato, int valor)throws Exception{
  int temp=0;
  if(valor>0){
    temp=tabla_simbolos.buscar_var_valor(dato);
    if(temp==0){
      if(tabla_simbolos.buscar_tipo_valor(8, valor)==false)
         tabla_simbolos.actualiza_valor(dato, valor);
      else{
            switch(valor){
              case 1: error("El MOTOR_A ya ha sido ocupado");
                      break;
              case 2: error("El MOTOR_B ya ha sido ocupado");
                      break;
              case 3: error("El MOTOR_C ya ha sido ocupado");
                      break;
            }}
    }
    else{
      if(temp==valor)
        warning("Ha multiplicado la declaración del motor \""+ dato +"\"");
      else
        error("El motor \""+ dato +"\" ya ha sido declarado con otro parámetro de MOTOR");
    }
  }
  else
    throw new ExcepcionAtras();
  }

  private int num_motor(){
  if(compara(139)){
    comparar(139);
    return 1;}// MOTOR_A
  if(compara(140)){
    comparar(140);
    return 2;}// MOTOR_B
  if(compara(141)){
    comparar(141);
    return 3;}// MOTOR_C
  return -1;
  }


  public void reporta_error(int error){
    switch(error){
      case 0: error("El resto del programa no es válido");
              break;
      case 16: if(band_error==false)
              error("No especificó \"(\"");
              break;
      case 17: if(band_error==false)
              error("No especificó \")\"");
              break;
      case 18: if(band_error==false)
              error("No especificó \":\"");
              break;
      case 19: if(band_error==false)
              error("No especificó \";\"");
              break;
      case 20: if(band_error==false)
              error("No especificó \",\"");
              break;
      case 103: error("No especificó la palabra clave \"agente\"");
              break;
      case 108: error("No especificó la palabra clave \"entonces\"");
              break;
      case 109: error("No especificó la palabra clave \"fin\"");
              break;
      case 112: error("No especificó la palabra clave \"finAgente\"");
              break;
      case 115: error("No finalizó la declaración de la sociedad de agentes");
              break;
      case 130: error("No inició correctamente la declaración de la sociedad de agentes");
              break;
      default: error("NO HA SIDO DECLARADO EL ERROR " + error);
              break;
      }
     band_error=true;
 }

  public void reporte_variables_locales(){
    int num_var = tabla_simbolos_local.count();
    for(int i=1; i<=num_var; i++){
      if(tabla_simbolos_local.getUso(i)==false)
        warning("La variable \""+tabla_simbolos_local.getNombre(i)+"\" no ha sido usada");
    }
  }

  public void reporte_globales(int tipo){
    int num_var = tabla_simbolos.count();
    if(tipo!=2 & tipo!=3){
        for(int i=1; i<=num_var; i++){
          if(tabla_simbolos.getUsoTipo(i,tipo)==0)// si la variable buscada con el tipo = x, es 0 -> no ha sido usada
            warning("La variable \""+tabla_simbolos.getNombre(i)+"\" no ha sido usada");
        }
    }
    else{
        for(int i=1; i<=num_var; i++){
          if(tabla_simbolos.getUsoTipo(i,tipo)==0){// si la variable buscada con el tipo = x, es 0 -> no ha sido usada
            if(tipo==2) error("La conducta \""+tabla_simbolos.getNombre(i)+"\" no ha sido declarada");
            if(tipo==3) error("La acción \""+tabla_simbolos.getNombre(i)+"\" no ha sido declarada");
          }
        }
    }
  }


  public void eliminar_tabla_simbolos(int tipo){
    tabla_simbolos.eliminar_tipo(tipo);
  }

/* Función que puede ser eliminada -
   Imprimir la Tabla Local por cada bloque*/
   public void imprimir_tabla_local(){
   tabla_simbolos_local.imprimir();
  }

  /* Función que puede ser eliminada -
     Imprimir la Tabla Local por cada bloque*/
     public void imprimir_tabla_simbolos(){
     tabla_simbolos.imprimir();
    }

   /* Función que puede ser eliminada -
     Imprimir la Tabla Local por cada bloque*/
     public void imprimir_sociedad(){
     lista_agentes.imprimir();
     }

/* *********************************************************************  */

     public void setCodigoObjeto(int codigo){
       generarCodigo.setCodigo(codigo);
     }

/*
 * Las siguientes funciones procesan los números, enteros y decimales, citados
 * en el programa, convirtiéndolos en codigo objeto
*/
     public void processNumeroObjeto(){
       for(int i=0; i<lexico.cadena.length();i++){
         setCodigoObjeto(lexico.cadena.charAt(i)); // Sólo para depurar
//         setCodigoObjeto(lexico.cadena.indexOf(i)); // quitar los comentarios, es el correcto
       }
     }

     public void processNumeroObjeto(String cadena){
       for(int i=0; i<cadena.length();i++){
         setCodigoObjeto(cadena.charAt(i)); // Sólo para depurar
//         setCodigoObjeto(cadena.indexOf(i)); // quitar los comentarios, es el correcto
       }
     }

/* *********************************************************************  */
//     Genera codigo de las tablas de simbolos de sensores
     public void generaCodigoSensores(){
       String cad = tabla_simbolos.getCodigoSensores();
       if(cad != ""){ // Si hay sensores en uso dentro del programa
         generarCodigo.setCodigoSociedad('?');
         generarCodigo.setCodigoSociedad(cad);
         generarCodigo.setCodigoSociedad('?');
       }
     }

     /* *********************************************************************  */
//     Genera codigo de las tablas de simbolos de acciones
          public void generaCodigoAcciones(){
            String cad = tabla_simbolos.getCodigoAcciones();
            if(cad != ""){ // Si hay acciones en uso dentro del programa
              generarCodigo.setCodigoSociedad(126);
              generarCodigo.setCodigoSociedad(cad);
              generarCodigo.setCodigoSociedad(126);
            }
          }


     /* *********************************************************************  */
//     Genera codigo de las tablas de simbolos (locales y globales) Entero y Decimal
          public void generaCodigoEnteroDecimal(){
            String cad = tabla_simbolos.getCodigoEnteroDecimal();
            if(cad != ""){ // Si hay variables globales declaradas en uso dentro del programa
              generarCodigo.setCodigoSociedad(64);
              generarCodigo.setCodigoSociedad(cad);
              generarCodigo.setCodigoSociedad(64);
            }
          }

          public void generaCodigoEnteroDecimalLocal(){
            String cad = tabla_simbolos_local.getCodigoEnteroDecimal();
            if(cad != ""){ // Si hay variables locales declaradas en uso dentro del programa
              generarCodigo.setVariableLocal('@'+cad+'@');
            }
          }

/* *********************************************************************  */
     public void generarArchivosCabecera(){
       // Es importante mantener el orden de las siguientes dos instrucciones
       generaCodigoAcciones();
       generarCodigo.archivoSociedad(lista_agentes, nombreSociedad+".Sociedad");
       generarCodigo.archivoSociedad(lista_conductas, nombreSociedad+".Conductas");
     }

     public void setLongCodigoObjeto(int codigo){
       generarCodigo.setLongCodigo(codigo);
     }


     public void setLongCondicion(){
       generarCodigo.setLongCondicion();
     }

     public void finalizaSeccion(){
       generarCodigo.FinalizaSeccion();
     }


}// fin de la clase TSintactico
