/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inage2000ver3;

import Lejos.Traductor;
import com.compimac.constants.CompiConstants;
import com.compimac.editor.EditorManager;
import com.compimac.editor.Editor;
import com.util.Util;
import java.io.IOException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;

/**
 *
 * @author rob
 */
public class CompiToolBar {

    private ToolBar toolbar = new ToolBar();

    private static CompiToolBar compiToolBar = null;
   
    private final int NUEVO=0;
  private final int CERRAR=1;
  private final int CERRARTODO=2;
  private final int GUARDAR=3;
  private final int GUARDARTODO=4;
  private final int GUARDARCOMO=5;
  private final int IMPRIMIR=6;
  private final int DESHACER=7;
  private final int REHACER=8;
  private final int COPIAR=9;
  private final int PEGAR=10;
  private final int CORTAR=11;
  private final int ELIMINAR=12;
  private final int SELECCIONARTODO=13;
  private final int VERERRORES=14;
  private final int VIEWERRORES=15;
  private final int EJECUTAR=16;
  private final int SIMULAR=17;
  private final int AYUDA=18;
  private final int STATUSBAR=19;
  private final int MUNDO = 20;
  private final int OBJETOS = 21;
  private final int DESHGRAFICO = 22;
  private final int REHGRAFICO = 23;
  private final int TRADUCIR = 24;
  
  private Simulador simulador = null;
  
  private Traductor traductor;

    private CompiToolBar() {

    }

    // File toolbar buttons
    Button newFileButton = new Button("", new ImageView("/images/newFile.gif"));
    Button openFileButton = new Button("", new ImageView("/images/openFile.gif"));
    Button closeFileButton = new Button("", new ImageView("/images/closeFile.gif"));
    Button saveFileButton = new Button("", new ImageView("/images/saveFile.gif"));
    Button saveAllFileButton = new Button("", new ImageView("/images/saveAllFile.gif"));
    Button printButton = new Button("", new ImageView("/images/print.gif"));

    // Edit Menu Buttons
    Button undoButton = new Button("", new ImageView("/images/undo.gif"));
    Button redoButton = new Button("", new ImageView("/images/redo.gif"));
    Button copyButton = new Button("", new ImageView("/images/copy.gif"));
    Button pasteButton = new Button("", new ImageView("/images/paste.gif"));
    Button cutButton = new Button("", new ImageView("/images/cut.gif"));

    Button executeButton = new Button("", new ImageView("/images/executor.gif"));
    Button startSimulationButton = new Button("", new ImageView("/images/start.gif"));
    Button stopSimulationButton = new Button("", new ImageView("/images/stop.gif"));
    
    Button  wallButton= new Button("", new ImageView("/images/wall.gif"));
    Button  obstacleButton= new Button("", new ImageView("/images/obstacle.gif"));
    Button  lightButton = new Button("", new ImageView("/images/light.gif"));
    Button  temperatureButton = new Button("", new ImageView("/images/temprature.gif"));
    
    Button undoGraphicButton= new Button("", new ImageView("/images/undoGraphic.gif"));
    Button redoGraphicButton = new Button("", new ImageView("/images/redoGraphic.gif"));
    Button cleanCellButton = new Button("", new ImageView("/images/cleanCell.gif"));
    Button eraseWallButton = new Button("", new ImageView("/images/eraseWall.gif"));
    Button cleanFloorButton = new Button("", new ImageView("/images/cleanFloor.gif"));
    
    Button  helpButton= new Button("", new ImageView("/images/help.gif"));

    Button lejosButton = new Button("", new ImageView("/images/nxt.png"));
    
    private String complileSuccessErrrorMessage = CompiConstants.COMPIILATION_SUCCESS;
        
    public static CompiToolBar getInstanceOf() {

        if (compiToolBar == null) {
            compiToolBar = new CompiToolBar();
            compiToolBar.initialize();
            

        }
        return compiToolBar;
    }

    public ToolBar getToolbar() {
        return toolbar;
    }

    private void initialize() {
        simulador = Simulador.getInstanceOf();
              
        newFileButton.tooltipProperty().setValue(new Tooltip(CompiConstants.TOOLTIP_NEW_FILE));
        openFileButton.tooltipProperty().setValue(new Tooltip(CompiConstants.TOOLTIP_OPEN_FILE));
        closeFileButton.tooltipProperty().setValue(new Tooltip(CompiConstants.TOOLTIP_CLOSE_FILE));
        saveFileButton.tooltipProperty().setValue(new Tooltip(CompiConstants.TOOLTIP_SAVE_FILE));
        saveAllFileButton.tooltipProperty().setValue(new Tooltip(CompiConstants.TOOLTIP_SAVEALL_FILE));
        printButton.tooltipProperty().setValue(new Tooltip(CompiConstants.TOOLTIP_PRINT_FILE));

        undoButton.tooltipProperty().setValue(new Tooltip(CompiConstants.TOOLTIP_UNDO));
        redoButton.tooltipProperty().setValue(new Tooltip(CompiConstants.TOOLTIP_REDO));
        copyButton.tooltipProperty().setValue(new Tooltip(CompiConstants.TOOLTIP_COPY));
        pasteButton.tooltipProperty().setValue(new Tooltip(CompiConstants.TOOLTIP_PASTE));
        cutButton.tooltipProperty().setValue(new Tooltip(CompiConstants.TOOLTIP_CUT));

        executeButton.tooltipProperty().setValue(new Tooltip(CompiConstants.TOOLTIP_EXECUTE));
        startSimulationButton.tooltipProperty().setValue(new Tooltip(CompiConstants.TOOLTIP_START));
        stopSimulationButton.tooltipProperty().setValue(new Tooltip(CompiConstants.TOOLTIP_STOP));
        
        wallButton.tooltipProperty().setValue(new Tooltip(CompiConstants.TOOLTIP_WALL));
        obstacleButton.tooltipProperty().setValue(new Tooltip(CompiConstants.TOOLTIP_OBSTACLE));
        lightButton.tooltipProperty().setValue(new Tooltip(CompiConstants.TOOLTIP_LIGHT));
        temperatureButton.tooltipProperty().setValue(new Tooltip(CompiConstants.TOOLTIP_TEMPERATURE));
        
        
        undoGraphicButton.tooltipProperty().setValue(new Tooltip(CompiConstants.TOOLTIP_UNDO_GRAPHIC));
        redoGraphicButton.tooltipProperty().setValue(new Tooltip(CompiConstants.TOOLTIP_REDO_GRAPHIC));
        cleanCellButton.tooltipProperty().setValue(new Tooltip(CompiConstants.TOOLTIP_CLEAN_CELL));
        eraseWallButton.tooltipProperty().setValue(new Tooltip(CompiConstants.TOOLTIP_ERASE_WALL));
        cleanFloorButton.tooltipProperty().setValue(new Tooltip(CompiConstants.TOOLTIP_CLEAN_FLOOR));

        helpButton.tooltipProperty().setValue(new Tooltip(CompiConstants.TOOLTIP_HELP));
          
        lejosButton.tooltipProperty().setValue(new Tooltip(CompiConstants.TOOLTIP_TRANSLATE_LEJOS));

        toolbar.getItems().addAll(newFileButton, openFileButton, closeFileButton, saveFileButton, saveAllFileButton, printButton, new Separator());
        toolbar.getItems().addAll(undoButton, redoButton, copyButton, pasteButton, cutButton, new Separator());
        toolbar.getItems().addAll(executeButton, lejosButton, new Separator());
        toolbar.getItems().addAll(startSimulationButton, stopSimulationButton,new Separator());
        toolbar.getItems().addAll(wallButton, obstacleButton,lightButton, temperatureButton, new Separator());
        toolbar.getItems().addAll(undoGraphicButton, redoGraphicButton,cleanCellButton, eraseWallButton, cleanFloorButton, new Separator());

                
        toolbar.getItems().addAll(helpButton);
        changeLanguage(CompiConstants.defaultLanguage);
        
        validar(0);
        setActions();
       
    }
    
    private void guardarProg(){
        //editor.guardarPrograma();
        //validar(2);
     }
    
    /**
     * Sets the actions for the toolbar buttons.
     */
    private void setActions(){
        executeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                  Editor currentEditor = EditorManager.getInstance().getCurrentEditor();
                 if(currentEditor.isFileSaved()) {
                if(currentEditor.ejecutar() == 0) {
                    String strFileName = Util.getInstanceOf().getOnlyName(currentEditor.getFileName());
                    String directoryName = currentEditor.getDirectoryName() + "\\" ;
                    Simulador.getInstanceOf().abrirNuevoArcAge(strFileName,directoryName);
                    
                    validar(25);
                    traductor = new Traductor(strFileName, directoryName);
                    
                    validar(14);
                    validar(18);
                    
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, complileSuccessErrrorMessage);
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                    }
                 }else{
                      validar(15); // deshabilita la opcin de MV
                      validar(17);// muestra la ventana de errores y warnings
                      currentEditor.viewMsg(2);
                  }
                 } else {
                      EditorManager.getInstance().saveFileRev();
                      
                 }
            }
        });
        
        
       openFileButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                boolean isFileOpened = EditorManager.getInstance().chooseAndLoadFile();
                if(isFileOpened)
                    validar(1);
            }
        });
       
        newFileButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                EditorManager.getInstance().createNew();
                validar(1);
                validar(3);
            }
            
        });
        
         closeFileButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
               Editor currentEditor = EditorManager.getInstance().getCurrentEditor();
               if(currentEditor.isModified()) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmación");
                    alert.setHeaderText("?Desea guardar los cambios de " + 
                            Util.getInstanceOf().getOnlyName(currentEditor.getFileName()));
                    //alert.setContentText("?Desea guardar los cambios de ");

                    ButtonType buttonTypeOne = new ButtonType("Si");
                    ButtonType buttonTypeTwo = new ButtonType("No");
                    ButtonType buttonTypeCancel = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);

                    alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == buttonTypeOne){
                        EditorManager.getInstance().saveFileRev();
                    } else if (result.get() == buttonTypeTwo) {
                         EditorManager.getInstance().closeTab();
                         validar(5);
                    }  else {
                       // do nothing
                    }
                }else {
                    EditorManager.getInstance().closeTab();
                    validar(5);
                }    
                
            }
        });
         
        saveFileButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                EditorManager.getInstance().saveFileRev();
            }
        });
        
        startSimulationButton.setOnAction(new EventHandler<ActionEvent> () {
            @Override
            public void handle(ActionEvent event) {
                Simulador.getInstanceOf().startSociedadAgentes();
            }
        }
        );
        
        stopSimulationButton.setOnAction((ActionEvent event) -> {
            Simulador.getInstanceOf().stopSociedadAgentes();
        });
        
        wallButton.setOnAction((ActionEvent event) -> {
            simulador.setPared();
            validar(19);
        });
        
         obstacleButton.setOnAction((ActionEvent event) -> {
              simulador.setObstaculo();
              validar(19);
        });
         
         lightButton.setOnAction((ActionEvent event) -> {
              simulador.setLuz();
              validar(19);
        });
         
          temperatureButton.setOnAction((ActionEvent event) -> {
              simulador.setTemperatura();
              //validar(19);
        });
          
          undoGraphicButton.setOnAction((ActionEvent event) -> {
              System.out.println("undoButton clicked");
             int i=0;
            i = simulador.undo();
           validar(21);
           if(i==0) validar(20);
        });
          
           redoGraphicButton.setOnAction((ActionEvent event) -> {
             int i=0;
            i = simulador.redo();
            validar(19);
            if(i==0) validar(22);
        });
           
           cleanCellButton.setOnAction((ActionEvent event) -> {
               simulador.quitarObjeto();
                validar(19);
        });
           
           eraseWallButton.setOnAction((ActionEvent event) -> {
            simulador.quitarPared();
            validar(19);
        });
           
           cleanFloorButton.setOnAction((ActionEvent event) -> {
            simulador.nuevoMundo();
        });
           
        helpButton.setOnAction((ActionEvent event) -> {
            simulador.nuevoMundo();
        });
        
        saveAllFileButton.setOnAction((ActionEvent event) -> {
            EditorManager.getInstance().saveAllFiles();
        });
        
        lejosButton.setOnAction((ActionEvent event) -> {
        try {   
            traductor.traduce();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "La traducción se realizó de manera correcta");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
            }
				
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
	}    
        });
        
        
        
 
    }    
    
  public void validar(int fase){
      validarAllComponents(fase);
      CompiMenuBar.getInstanceOf().validarAllComponents(fase);
  }
    
  public void validarAllComponents(int fase){
  Editor currentEditor = EditorManager.getInstance().getCurrentEditor();
  switch(fase){
    case 0:// VALORES INICIALES DE VALIDACI?N - NO SOBRE BOT?N
            Enabled(NUEVO, true);
            Enabled(CERRAR, false);
            Enabled(CERRARTODO, false);
            Enabled(GUARDAR, false);
            Enabled(GUARDARCOMO, false);
            Enabled(GUARDARTODO, false);
            Enabled(IMPRIMIR, false);
            Enabled(DESHACER, false);
            Enabled(REHACER, false);
            Enabled(COPIAR, false);
            Enabled(PEGAR, false);
            Enabled(CORTAR, false);
            Enabled(ELIMINAR, false);
            Enabled(SELECCIONARTODO, false);
            Enabled(VERERRORES, false);
            Enabled(VIEWERRORES, false);
            Enabled(EJECUTAR, false);
            Enabled(SIMULAR, false);
            Enabled(TRADUCIR, false);
            Enabled(MUNDO, false);
            Enabled(OBJETOS, false);
            Enabled(DESHGRAFICO, false);
            Enabled(REHGRAFICO, false);
            break;
    case 1:// VALORES DE UN NUEVO PROGRAMA DE VALIDACI?N - NO SOBRE BOT?N
            Enabled(CERRAR, true);
            Enabled(CERRARTODO, true);
            Enabled(GUARDAR, false);
            Enabled(GUARDARCOMO, true);
            Enabled(IMPRIMIR, true);
            Enabled(SELECCIONARTODO, true);
            Enabled(VERERRORES, true);
            Enabled(EJECUTAR, true);
//            Enabled(SIMULAR, false);
            break;
    case 2:// VALORES CUANDO EXISTEN CAMBIOS Y CLICK SOBRE BOT?N GUARDAR
            if(!currentEditor.isModified()){
                Enabled(GUARDAR, false);
                /*bGuardar.setBorderPainted(false);
                if(!currentEditor.getGuardarTodo()){
                    Enabled(GUARDARTODO, false);
                    //bGuardarTodo.setBorderPainted(false);
                }*/
            }
            break;
    case 3:// VALORES CUANDO MODIFICADO = TRUE - NO SOBRE BOT?N
            Enabled(GUARDAR, true);
            Enabled(GUARDARTODO, true);
            Enabled(SIMULAR, false);
            break;
    case 4:// VALORES CUANDO EXISTEN CAMBIOS - NO SOBRE BOT?N
            if(currentEditor.isModified()){
                Enabled(GUARDAR, true);
            }
            else{// VALORES CUANDO LOS CAMBIOS SE GUARDAN
                Enabled(GUARDAR, false);
            }
            break;
    case 5:// VALORES CUANDO SE CIERRAN PROGRAMAS - CLICK SOBRE BOT?N CERRAR
            System.out.println("Editors count :: " + EditorManager.getInstance().getNoOfEditors());
            if( EditorManager.getInstance().getNoOfEditors() == 0){
                Enabled(CERRAR, false);
                //bCerrar.setBorderPainted(false);
                Enabled(CERRARTODO, false);
                Enabled(GUARDAR, false);
                Enabled(GUARDARTODO, false);
                Enabled(GUARDARCOMO, false);
                Enabled(IMPRIMIR, false);
                Enabled(DESHACER, false);
                Enabled(REHACER, false);
                Enabled(COPIAR, false);
                Enabled(PEGAR, false);
                Enabled(CORTAR, false);
                Enabled(ELIMINAR, false);
                Enabled(SELECCIONARTODO, false);
                Enabled(EJECUTAR, false);
                Enabled(STATUSBAR, false);
                Enabled(SIMULAR, false);
                Enabled(TRADUCIR, false);
                Enabled(MUNDO, false);
                Enabled(OBJETOS, false);
                Enabled(DESHGRAFICO, false);
                Enabled(REHGRAFICO, false);
            }
            //OVERTYPEMODE = false;
            break;
    case 6:// VALORES DE MENU EDITAR DESHACER - TRUE
            Enabled(DESHACER, true);
            break;
    case 7:// VALORES DE MENU EDITAR DESHACER - FALSE
            Enabled(DESHACER, false);
            //bDeshacer.setBorderPainted(false);
            break;
    case 8:// VALORES DE MENU EDITAR REHACER - TRUE
            Enabled(REHACER, true);
            break;
    case 9:// VALORES DE MENU EDITAR RESHACER - FALSE
            Enabled(REHACER, false);
            //bRehacer.setBorderPainted(false);
            break;
    case 10:// VALORES DE MENU EDITAR COPIAR - CORTAR
            Enabled(COPIAR, true);
            Enabled(CORTAR, true);
            Enabled(ELIMINAR, true);
            break;
    case 11:// VALORES DE MENU EDITAR COPIAR - CORTAR
            Enabled(COPIAR, false);
            ///bCopiar.setBorderPainted(false);
            Enabled(CORTAR, false);
            //bCortar.setBorderPainted(false);
            Enabled(ELIMINAR, false);
            break;
    case 12:// VALORES DE MENU EDITAR COPIAR - PEGAR - CORTAR
            Enabled(PEGAR, true);
            break;
    case 13:// VALORES DE MENU EDITAR COPIAR - PEGAR - CORTAR
            Enabled(PEGAR, false);
            //bPegar.setBorderPainted(false);
            break;
    case 14:// VALORES CUANDO SE CARGA POR PRIMERA VEZ LA SIMULACI?N DE UN PROGRAMA EN AGE
            Enabled(SIMULAR, true);
            Enabled(MUNDO, true);
            Enabled(OBJETOS, true);
            Enabled(DESHGRAFICO, false);
            Enabled(REHGRAFICO, false);
            break;
    case 15:// VALORES QUE CIERRAN LA POSIBILIDAD DE SIMULAR -- O INICIA EL PROGRAMA InAGE
            Enabled(SIMULAR, false);
            Enabled(MUNDO, false);
            Enabled(OBJETOS, false);
            Enabled(DESHGRAFICO, false);
            Enabled(REHGRAFICO, false);
            break;
    case 16:
            Enabled(VIEWERRORES, false);
            break;
    case 17:
            Enabled(VIEWERRORES, true);
            break;
    case 18:
           currentEditor.viewMsg(2);
           //viewViewMsg(2);
            
            break;
    case 19:// DESHACER GRAFICO
            Enabled(DESHGRAFICO, true);
            break;
    case 20:
            Enabled(DESHGRAFICO, false);
            break;
    case 21:// REHACER GRAFICO
            Enabled(REHGRAFICO, true);
            break;
    case 22:
            Enabled(REHGRAFICO, false);
            break;
    case 25:
    	Enabled(TRADUCIR,true);
  	}
  }


  private void Enabled(int boton, boolean valor){
  switch(boton){
    case NUEVO: // NUEVO
            newFileButton.setDisable(!valor);
            //jMenuFileNuevo.setEnabled(valor);
            break;
    case CERRAR: // CERRAR
            closeFileButton.setDisable(!valor);
            //jMenuFileCerrar.setEnabled(valor);
            break;
    case CERRARTODO: // CERRAR
            //jMenuFileCerrarTodo.setEnabled(valor);
            break;
    case GUARDAR: // GUARDAR
        saveFileButton.setDisable(!valor);
            //jMenuFileGuardar.setEnabled(valor);
            break;
    case GUARDARTODO: // GUARDAR TODO
            saveAllFileButton.setDisable(!valor);
            //jMenuFileGuardarTodo.setEnabled(valor);
            break;
    case GUARDARCOMO: // GUARDAR COMO...
            //jMenuFileGuardarComo.setEnabled(valor);
            break;
    case IMPRIMIR: // GUARDAR TODO
        printButton.setDisable(!valor);
            
            //jMenuFileImprimir.setEnabled(valor);
            break;
    case DESHACER: // DESHACER
        undoGraphicButton.setDisable(!valor);
           
            //jMenuEditarDesh.setEnabled(valor);
            break;
    case REHACER: // REHACER
        redoGraphicButton.setDisable(!valor);
           
           // jMenuEditarReh.setEnabled(valor);
            break;
    case COPIAR: // COPIAR
            copyButton.setDisable(!valor);
            //jMenuEditarCopiar.setEnabled(valor);
            break;
    case PEGAR: // PEGAR
            pasteButton.setDisable(!valor);
            //jMenuEditarPegar.setEnabled(valor);
            break;
    case CORTAR: // CORTAR
            cutButton.setDisable(!valor);
            //jMenuEditarCortar.setEnabled(valor);
            break;
    case ELIMINAR: // CORTAR
            //jMenuEditarEliminar.setEnabled(valor);
            break;
    case SELECCIONARTODO:
            //jMenuEditarSelTodo.setEnabled(valor);
            break;
    case VERERRORES:
            //jCheckBoxVerErrores.setEnabled(valor);
            break;
    case VIEWERRORES:
            /*scroll.setVisible(valor);
            jCheckBoxVerErrores.setSelected(valor);
            if(valor)
              jSplitPane1.setDividerLocation(400);
            break;*/
    case EJECUTAR:
        executeButton.setDisable(!valor);
           
           // jMenuCompCompilar.setEnabled(valor);
            break;
    case SIMULAR:
            //jMenuMVStart.setEnabled(valor);
            startSimulationButton.setDisable(!valor);
            //jMenuMVStop.setEnabled(valor);
            stopSimulationButton.setDisable(!valor);

            break;
    case TRADUCIR:
        lejosButton.setDisable(!valor);
        //bTraducir.setEnabled(valor);
        //jMenuTraduceTraducir.setEnabled(valor);
        break;
    case MUNDO:
           // jMenuNuevoMundo.setEnabled(valor);
            //jMenuAbrirMundo.setEnabled(valor);
            //jMenuGuardarMundo.setEnabled(valor);
            //jMenuMVCerrar.setEnabled(valor);
            break;
    case OBJETOS:
            wallButton.setDisable(!valor);
            obstacleButton.setDisable(!valor);
            lightButton.setDisable(!valor);
            temperatureButton.setDisable(!valor);
            cleanCellButton.setDisable(!valor);
            eraseWallButton.setDisable(!valor);
            cleanFloorButton.setDisable(!valor);
       
            break;
    case REHGRAFICO:
            redoGraphicButton.setDisable(!valor);
            break;
    case DESHGRAFICO:
            undoGraphicButton.setDisable(!valor);
            break;
    case AYUDA:// AYUDA
        helpButton.setDisable(!valor);
           
           // jMenuHelpAyuda.setEnabled(valor);
            break;
    case STATUSBAR:// AYUDA
                 /* statusBar.setVisible(valor);
                  text1.setText("");
                  text2.setText("");
                  text3.setText("");*/
                  break;
  }
  }

    public void changeLanguage(String language) {
        newFileButton.tooltipProperty().setValue(new Tooltip(CompiConstants.getValue(language, CompiConstants.TOOLTIP_NEW_FILE)));
        openFileButton.tooltipProperty().setValue(new Tooltip(CompiConstants.getValue(language, CompiConstants.TOOLTIP_OPEN_FILE)));
        closeFileButton.tooltipProperty().setValue(new Tooltip(CompiConstants.getValue(language, CompiConstants.TOOLTIP_CLOSE_FILE)));
        saveFileButton.tooltipProperty().setValue(new Tooltip(CompiConstants.getValue(language, CompiConstants.TOOLTIP_SAVE_FILE)));
        saveAllFileButton.tooltipProperty().setValue(new Tooltip(CompiConstants.getValue(language, CompiConstants.TOOLTIP_SAVEALL_FILE)));
        printButton.tooltipProperty().setValue(new Tooltip(CompiConstants.getValue(language, CompiConstants.TOOLTIP_PRINT_FILE)));

        undoButton.tooltipProperty().setValue(new Tooltip(CompiConstants.getValue(language, CompiConstants.TOOLTIP_UNDO)));
        redoButton.tooltipProperty().setValue(new Tooltip(CompiConstants.getValue(language, CompiConstants.TOOLTIP_REDO)));
        copyButton.tooltipProperty().setValue(new Tooltip(CompiConstants.getValue(language, CompiConstants.TOOLTIP_COPY)));
        pasteButton.tooltipProperty().setValue(new Tooltip(CompiConstants.getValue(language, CompiConstants.TOOLTIP_PASTE)));
        cutButton.tooltipProperty().setValue(new Tooltip(CompiConstants.getValue(language, CompiConstants.TOOLTIP_CUT)));

        executeButton.tooltipProperty().setValue(new Tooltip(CompiConstants.getValue(language, CompiConstants.TOOLTIP_EXECUTE)));
        startSimulationButton.tooltipProperty().setValue(new Tooltip(CompiConstants.getValue(language, CompiConstants.TOOLTIP_START)));
        stopSimulationButton.tooltipProperty().setValue(new Tooltip(CompiConstants.getValue(language, CompiConstants.TOOLTIP_STOP)));

        wallButton.tooltipProperty().setValue(new Tooltip(CompiConstants.getValue(language, CompiConstants.TOOLTIP_WALL)));
        obstacleButton.tooltipProperty().setValue(new Tooltip(CompiConstants.getValue(language, CompiConstants.TOOLTIP_OBSTACLE)));
        lightButton.tooltipProperty().setValue(new Tooltip(CompiConstants.getValue(language, CompiConstants.TOOLTIP_LIGHT)));
        temperatureButton.tooltipProperty().setValue(new Tooltip(CompiConstants.getValue(language, CompiConstants.TOOLTIP_TEMPERATURE)));

        undoGraphicButton.tooltipProperty().setValue(new Tooltip(CompiConstants.getValue(language, CompiConstants.TOOLTIP_UNDO_GRAPHIC)));
        redoGraphicButton.tooltipProperty().setValue(new Tooltip(CompiConstants.getValue(language, CompiConstants.TOOLTIP_REDO_GRAPHIC)));
        cleanCellButton.tooltipProperty().setValue(new Tooltip(CompiConstants.getValue(language, CompiConstants.TOOLTIP_CLEAN_CELL)));
        eraseWallButton.tooltipProperty().setValue(new Tooltip(CompiConstants.getValue(language, CompiConstants.TOOLTIP_ERASE_WALL)));
        cleanFloorButton.tooltipProperty().setValue(new Tooltip(CompiConstants.getValue(language, CompiConstants.TOOLTIP_CLEAN_FLOOR)));

        helpButton.tooltipProperty().setValue(new Tooltip(CompiConstants.getValue(language, CompiConstants.TOOLTIP_HELP)));

        complileSuccessErrrorMessage = CompiConstants.getValue(language, CompiConstants.COMPIILATION_SUCCESS);
    }
       
}
