package com.compimac.editor;

import Herramientas.Errores;
import Herramientas.TablaSimbolos;
import Lexico.AnalizadorLexico;
import Sintactico.Sociedad.ProgramaAge;
import inage2000ver3.CompiStatusPane;
import javafx.scene.control.TextArea;

/**
 *
 * @author rob
 */
public class Editor {
    private boolean modified = false;
    private TextArea textArea = new TextArea();
    private String fileName = null;
    private String direcoryName =null;
    
    private Errores errores = new Errores();
    private Errores warnings = new Errores();
   
  int iError[][] = new int [50][2];
  int iWarning[][] = new int [50][2];

    public boolean isModified() {
        return modified;
    }
    
    public void setModified(boolean modified) {
        this.modified = modified;
    }

    public TextArea getRoot() {
        return textArea;
    }

    public void setText(String text) {
        textArea.setText(text);
    }
    
    public String getText() {
        return textArea.getText();
    }
  
    public void setFileName(String fileName){
        this.fileName =fileName;
    }
    
    public void setDirectoryName(String directoryName){
        this.direcoryName = directoryName;
    }
    
    public String getFileName(){
        return fileName;
    }
    
    public String getDirectoryName(){
        return direcoryName;
    }
    
    public int ejecutar() {
    TablaSimbolos tabla_simbolos = new TablaSimbolos();
    errores = new Errores();
    warnings = new Errores();
    try {
      AnalizadorLexico lexico = new AnalizadorLexico(direcoryName + "\\" + fileName
               , errores, tabla_simbolos); // constructor de la clase TLexico
      if (errores.vacio()) {
        ProgramaAge programa = new ProgramaAge(direcoryName, fileName, lexico, tabla_simbolos, errores, warnings);
        programa.analisis();
      }
      lexico = null;
      System.gc(); // llamada al recolector de basura
    }
    catch (ArrayIndexOutOfBoundsException e) { // Excepcin : no se especifica el nombre del archivo fuente
      errores.insertar(0, "No especific el nombre del archivo fuente");
    }
    tabla_simbolos = null;
    System.gc(); // llamada al recolector de basura
    return errores.getCountNodo();
  }
    
   
    public boolean isFileSaved(){
      if(direcoryName!= null && fileName !=null) {
          return true;
      } else {
          return false;
      }
    }
    
public void viewMsg(int op){
  try{
    int countW = warnings.getCountNodo();
    int countE = 0;
    String temp = "";
    CompiStatusPane.getInstance().setStatusMessages("");
    temp = setWarnings(temp, countW);
    if(op > 1){ // Si va a desplegar tambin los Errores
      countE = errores.getCountNodo();
      temp = setErrores(temp, countW, countE);
    }
    temp = temp.substring(0,temp.length()-1);
    CompiStatusPane.getInstance().setStatusMessages(temp);
    /*for (int i = 0; i < countW; i++) {
      doc.insertString(iWarning[i][0], "W", styleWarning); // Coloca la imagen
    }
    for (int i = 0; i < countE; i++) {
      doc.insertString(iError[i][0], "E", styleError); // Coloca la imagen
    }
    viewMsg.setCaretPosition(0); // Muestra los primeros Errores/Warnings, el scroll hasta arriba
    */
  } catch(java.lang.StringIndexOutOfBoundsException er) {}

  }

private String setWarnings(String cadTemp, int count){
//    int indWarning[][] = new int[count+1][2]; // indice [imagen][renglon]
    iWarning[0][0] = 0; // Ubicacin de la imagen de error/warning
//    iWarning[0][1] = 0; // Ubicacin del rengln en donde se encuentra el error/warning
    for (int i = 1; i < count + 1; i++) { // numEW es la cantidad de errores/warnings que se conocen
      cadTemp = cadTemp + warnings.getRowDesc(i) + "\n";
      iWarning[i][0] = cadTemp.length() + i; // Ubicacin de la imagen de error/warning
      iWarning[i-1][1] = warnings.getRow(i); // Ubicacin del rengln en donde se encuentra el error/warning
    }
    return cadTemp;
}


  private String setErrores(String cadTemp, int countW, int countE){
//   int indError[][] = new int[count+1][2]; // indice [imagen][renglon]
   iError[0][0] = cadTemp.length() + countW; // Ubicacin de la imagen de error/warning
//   iError[0][1] = 0; // Ubicacin del rengln en donde se encuentra el error/warning
   for (int i = 1; i < countE + 1; i++) { // numEW es la cantidad de errores/warnings que se conocen
     cadTemp = cadTemp + errores.getRowDesc(i) + "\n";
     iError[i][0] = cadTemp.length() + i + countW; // Ubicacin de la imagen de error/warning
     iError[i-1][1] = errores.getRow(i); // Ubicacin del rengln en donde se encuentra el error/warning
   }
   return cadTemp;
  }
   
}
