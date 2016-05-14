/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inage2000ver3;

import Lejos.Traductor;
import com.compimac.constants.CompiConstants;
import com.compimac.editor.Editor;
import com.compimac.editor.EditorManager;
import com.util.Util;
import inage2000ver3.Simulador;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.ImageView;

/**
 *
 * @author Tiwari
 */
public class CompiMenuBar {

    private MenuBar compiMenuBar = new MenuBar();
    
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

  // File menu
    private Menu fileMenu = new Menu(CompiConstants.FILE_MENU);
    private MenuItem newMenuItem = new MenuItem(CompiConstants.new_menuItem);
    private MenuItem openMenuItem = new MenuItem(CompiConstants.open_menuItem);
    private MenuItem closeMenuItem = new MenuItem(CompiConstants.close_menuItem);
    private MenuItem closeAllMenuItem = new MenuItem(CompiConstants.closeAll_menuItem);
    private MenuItem saveMenuItem = new MenuItem(CompiConstants.save_menuItem);
    private MenuItem saveAllMenuItem = new MenuItem(CompiConstants.saveAll_menuItem);
    private MenuItem saveAsMenuItem = new MenuItem(CompiConstants.saveAs_menuItem);
    private MenuItem printMenuOption = new MenuItem(CompiConstants.printer_menuItem);
    private MenuItem closeAppMenuOption = new MenuItem(CompiConstants.closeApp_menuItem);

    
    

    private Menu editMenu = new Menu(CompiConstants.EDIT_MENU);
    private MenuItem undoMenuItem = new MenuItem(CompiConstants.undo_menuItem);
    private MenuItem redoMenuItem = new MenuItem(CompiConstants.redo_menuItem);
    private MenuItem cutMenuItem = new MenuItem(CompiConstants.cut_menuItem);
    private MenuItem copyMenuItem = new MenuItem(CompiConstants.copy_menuItem);
    private MenuItem pasteMenuItem = new MenuItem(CompiConstants.paste_menuItem);
    private MenuItem removeMenuItem = new MenuItem(CompiConstants.remove_menuItem);
    private MenuItem selectAllMenuItem = new MenuItem(CompiConstants.selectAll_menuItem);

    
    
    private Menu seeMenu = new Menu(CompiConstants.SEE_MENU);
    private MenuItem  erroMenuItem= new MenuItem(CompiConstants.errors_menuItem);
    
    private Menu compilerMenu = new Menu(CompiConstants.COMPILAR_MENU);
    private MenuItem  executorMenuItem= new MenuItem(CompiConstants.executor_menuItem);
    private MenuItem tranlsateLejos = new MenuItem(CompiConstants.TRANSLATE_LEJO_MENU_TEXT);

    private Menu virtualMenu = new Menu(CompiConstants.VIRTUAL_MENU);
    private MenuItem newWorldMenuItem = new MenuItem(CompiConstants.newWorld_menuItem);
    private MenuItem openWorlMenuItem = new MenuItem(CompiConstants.openWorld_menuItem);
    private MenuItem saveWorldMenuItem= new MenuItem(CompiConstants.saveWorld_menuItem);
    private MenuItem saveAllWorldMenuItem = new MenuItem(CompiConstants.saveAllWorld_menuItem);
    private MenuItem startSimulationMenuItem = new MenuItem(CompiConstants.startSimulation_menuItem);
    private MenuItem stopSimulationMenuItem = new MenuItem(CompiConstants.stopSimulation_menuItem);
    private MenuItem closeSimulationMenuItem = new MenuItem(CompiConstants.closeSimulation_menuItem);
    
    

    private Menu helpMenu = new Menu(CompiConstants.HELP_MENU);
    private MenuItem  helpMenuItem= new MenuItem(CompiConstants.help_menuItem);
    private MenuItem  aboutMenuItem= new MenuItem(CompiConstants.about_menuItem);

    private static CompiMenuBar compiMenu = null;
    
     // Ashish - for internationalization
    private Menu languageMenu = new Menu(CompiConstants.getValue(
            CompiConstants.defaultLanguage, CompiConstants.LANGUAGE));
    private MenuItem englishMenuItem = new MenuItem(CompiConstants.getValue(
            CompiConstants.defaultLanguage, CompiConstants.LANGUAGE_ENG));
    private MenuItem spanishMenuItem = new MenuItem(CompiConstants.getValue(
            CompiConstants.defaultLanguage, CompiConstants.LANGUAGE_MX));
    
    
    private Traductor traductor;
    
    private String complileSuccessErrrorMessage = CompiConstants.COMPIILATION_SUCCESS;
            
    private CompiMenuBar(){
        
    }

    public static CompiMenuBar getInstanceOf() {
        if (compiMenu == null) {
            compiMenu = new CompiMenuBar();
            compiMenu.initialize();
        }

        return compiMenu;
    }

    public MenuBar getMenuBar() {

        return compiMenuBar;
    }

    private void initialize() {
        
        newMenuItem.setGraphic(new ImageView("/images/newFile.gif"));
        openMenuItem.setGraphic(new ImageView("/images/openFile.gif"));
        closeMenuItem.setGraphic(new ImageView("/images/closeFile.gif"));
        
        saveMenuItem.setGraphic(new ImageView("/images/saveFile.gif"));
        saveAllMenuItem.setGraphic(new ImageView("/images/saveAllFile.gif"));
        saveAsMenuItem.setGraphic(new ImageView("/images/saveAllFile.gif"));
        printMenuOption.setGraphic(new ImageView("/images/print.gif"));

        
        fileMenu.getItems().addAll(newMenuItem,new SeparatorMenuItem(),openMenuItem, closeMenuItem, closeAllMenuItem,
            new SeparatorMenuItem(),saveMenuItem,saveAllMenuItem,saveAsMenuItem,new SeparatorMenuItem(),printMenuOption, 
            new SeparatorMenuItem(),closeAppMenuOption);
        
        undoMenuItem.setGraphic(new ImageView("/images/undo.gif"));
        redoMenuItem.setGraphic(new ImageView("/images/redo.gif"));
        cutMenuItem.setGraphic(new ImageView("/images/cut.gif"));
        copyMenuItem.setGraphic(new ImageView("/images/copy.gif"));
        pasteMenuItem.setGraphic(new ImageView("/images/paste.gif"));
        
        editMenu.getItems().addAll(undoMenuItem,redoMenuItem,new SeparatorMenuItem(), cutMenuItem, copyMenuItem, pasteMenuItem,
            removeMenuItem, new SeparatorMenuItem(),selectAllMenuItem);
        
        erroMenuItem.setDisable(true);
        seeMenu.getItems().addAll(erroMenuItem);
        
        executorMenuItem.setGraphic(new ImageView("/images/executor.gif"));
        tranlsateLejos.setGraphic(new ImageView("/images/nxt.png"));
        compilerMenu.getItems().addAll(executorMenuItem, tranlsateLejos);
        
        startSimulationMenuItem.setGraphic(new ImageView("/images/start.gif"));
        stopSimulationMenuItem.setGraphic(new ImageView("/images/stop.gif"));

        virtualMenu.getItems().addAll(newWorldMenuItem,openWorlMenuItem,saveWorldMenuItem,saveAllWorldMenuItem,
            new SeparatorMenuItem(),startSimulationMenuItem,stopSimulationMenuItem,new SeparatorMenuItem(),closeSimulationMenuItem);
        
        helpMenuItem.setGraphic(new ImageView("/images/help.gif"));
        aboutMenuItem.setGraphic(new ImageView("/images/info.gif"));

        helpMenu.getItems().addAll(helpMenuItem, aboutMenuItem);
        languageMenu.getItems().addAll(englishMenuItem, spanishMenuItem);
        
        compiMenuBar.getMenus().addAll(fileMenu, editMenu, seeMenu, compilerMenu, virtualMenu, helpMenu, languageMenu);
        changeLanguage(CompiConstants.defaultLanguage);
        
        setActions();
        
        validar(0);

    }
    
    /**
     * This methods contains the actions for all toolbar menu items.
     */
    private void setActions(){
        executorMenuItem.setOnAction(new EventHandler<ActionEvent>() {
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
       
       openMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                boolean isFileOpened = EditorManager.getInstance().chooseAndLoadFile();
                if(isFileOpened)
                    validar(1);
            }
        });              
       
       newWorldMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                Simulador.getInstanceOf().nuevoMundo();                                    
                
            }
        });              
       
       openWorlMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                Simulador.getInstanceOf().abrirMundo();                                
            }
        });              
       
       saveAllWorldMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                Simulador.getInstanceOf().guardarMundoComo();                
            }
        });
       
       startSimulationMenuItem.setOnAction(new EventHandler<ActionEvent> () {
            @Override
            public void handle(ActionEvent event) {
                Simulador.getInstanceOf().startSociedadAgentes();
            }
        }
        );
       
       stopSimulationMenuItem.setOnAction((ActionEvent event) -> {
            Simulador.getInstanceOf().stopSociedadAgentes();
        });
       
       closeSimulationMenuItem.setOnAction((ActionEvent event) -> {
            Simulador.getInstanceOf().nuevoMundo();
        });
       
        newMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                EditorManager.getInstance().createNew();
                validar(1);
                validar(3);
            }
        });
        
        closeMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                
                if(EditorManager.getInstance().getCurrentEditor().isModified()) {
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Confirmación");
                    //alert.setHeaderText("?Desea guardar los cambios de ");
                    alert.setContentText("?Desea guardar los cambios de "
                            + Util.getInstanceOf().getOnlyName(EditorManager.getInstance().getCurrentEditor().getFileName()));

                    ButtonType buttonTypeOne = new ButtonType("Si");
                    ButtonType buttonTypeTwo = new ButtonType("No");
                    ButtonType buttonTypeCancel = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);

                    alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == buttonTypeOne){
                        EditorManager.getInstance().saveFileRev();
                    } else if (result.get() == buttonTypeTwo) {
                         EditorManager.getInstance().closeTab();
                    }  else {
                       // do nothing
                    }
                }else {
                    EditorManager.getInstance().closeTab();
                    validar(5);
                }    
                
            }
        });
        
        closeAllMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                EditorManager.getInstance().closeAllTabs();
                validar(5);
            }
        });
        
        saveAsMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                EditorManager.getInstance().saveFileRev();
            }
        });
        
        saveAllMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                EditorManager.getInstance().stop();
            }
        });
        
        closeAppMenuOption.setOnAction(new EventHandler<ActionEvent>() {
                   
            public void handle(ActionEvent t) {
                EditorManager.getInstance().stop();
            }
        });
        
         selectAllMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                   
            public void handle(ActionEvent t) {
               EditorManager.getInstance().getCurrentEditor().getRoot().selectAll();
            }
        });
         
         helpMenuItem.setOnAction(new EventHandler<ActionEvent>() {                   
            public void handle(ActionEvent t) {
                try {
                    String url = "C:\\Users\\gatoher\\Documents\\NetBeansProjects\\TutoDos\\Ayuda_InAge.htm";
                    ProcessBuilder p = new ProcessBuilder();
                    p.command("cmd.exe", "/c", url);
                    p.start();
                } catch (IOException ex) {
                    Logger.getLogger(CompiMenuBar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
         
         removeMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
               EditorManager.getInstance().getCurrentEditor().getRoot().replaceSelection("");
            }
        });
         
         redoMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
               //EditorManager.getInstance().getCurrentEditor().getRoot().re
            }
        });
         
         spanishMenuItem.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent t) {

                changeLanguage(CompiConstants.languageSpanish);
                CompiToolBar.getInstanceOf().changeLanguage(CompiConstants.languageSpanish);

            }

        });
        englishMenuItem.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent t) {

                changeLanguage(CompiConstants.laguageEnglish);
                CompiToolBar.getInstanceOf().changeLanguage(CompiConstants.laguageEnglish);

            }

        });

  
        
        
    }
    
    
  public void validar(int fase){
      validarAllComponents(fase);
      CompiToolBar.getInstanceOf().validarAllComponents(fase);
  }
    
  public void validarAllComponents(int fase){
  Editor currentEditor = EditorManager.getInstance().getCurrentEditor();
  switch(fase){
    case 0:// VALORES INICIALES DE VALIDACIÓN - NO SOBRE BOTÓN
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
    case 1:// VALORES DE UN NUEVO PROGRAMA DE VALIDACIÓN - NO SOBRE BOTÓN
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
    case 2:// VALORES CUANDO EXISTEN CAMBIOS Y CLICK SOBRE BOTÓN GUARDAR
            if(!currentEditor.isModified()){
                Enabled(GUARDAR, false);
                /*bGuardar.setBorderPainted(false);
                if(!currentEditor.getGuardarTodo()){
                    Enabled(GUARDARTODO, false);
                    //bGuardarTodo.setBorderPainted(false);
                }*/
            }
            break;
    case 3:// VALORES CUANDO MODIFICADO = TRUE - NO SOBRE BOTÓN
            Enabled(GUARDAR, true);
            Enabled(GUARDARTODO, true);
            Enabled(SIMULAR, false);
            break;
    case 4:// VALORES CUANDO EXISTEN CAMBIOS - NO SOBRE BOTÓN
            if(currentEditor.isModified()){
                Enabled(GUARDAR, true);
            }
            else{// VALORES CUANDO LOS CAMBIOS SE GUARDAN
                Enabled(GUARDAR, false);
            }
            break;
    case 5:// VALORES CUANDO SE CIERRAN PROGRAMAS - CLICK SOBRE BOTÓN CERRAR
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
    case 14:// VALORES CUANDO SE CARGA POR PRIMERA VEZ LA SIMULACIÓN DE UN PROGRAMA EN AGE
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
            newMenuItem.setDisable(!valor);
            //jMenuFileNuevo.setEnabled(valor);
            break;
    case CERRAR: // CERRAR
            closeMenuItem.setDisable(!valor);
            //jMenuFileCerrar.setEnabled(valor);
            break;
    case CERRARTODO: // CERRAR
            closeAllMenuItem.setDisable(!valor);
            break;
    case GUARDAR: // GUARDAR
            saveMenuItem.setDisable(!valor);
            //jMenuFileGuardar.setEnabled(valor);
            break;
    case GUARDARTODO: // GUARDAR TODO
            saveAllMenuItem.setDisable(!valor);
            //jMenuFileGuardarTodo.setEnabled(valor);
            break;
    case GUARDARCOMO: // GUARDAR COMO...
            saveAsMenuItem.setDisable(!valor);
            break;
    case IMPRIMIR: // GUARDAR TODO
        printMenuOption.setDisable(!valor);
            
            //jMenuFileImprimir.setEnabled(valor);
            break;
    case DESHACER: // DESHACER
        undoMenuItem.setDisable(!valor);
           
            //jMenuEditarDesh.setEnabled(valor);
            break;
    case REHACER: // REHACER
        redoMenuItem.setDisable(!valor);
           
           // jMenuEditarReh.setEnabled(valor);
            break;
    case COPIAR: // COPIAR
            copyMenuItem.setDisable(!valor);
            //jMenuEditarCopiar.setEnabled(valor);
            break;
    case PEGAR: // PEGAR
            pasteMenuItem.setDisable(!valor);
            //jMenuEditarPegar.setEnabled(valor);
            break;
    case CORTAR: // CORTAR
            cutMenuItem.setDisable(!valor);
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
        executorMenuItem.setDisable(!valor);
           
           // jMenuCompCompilar.setEnabled(valor);
            break;
    case SIMULAR:
            //jMenuMVStart.setEnabled(valor);
            startSimulationMenuItem.setDisable(!valor);
            //jMenuMVStop.setEnabled(valor);
            stopSimulationMenuItem.setDisable(!valor);

            break;
    case TRADUCIR:
        tranlsateLejos.setDisable(!valor);
        //bTraducir.setEnabled(valor);
        //jMenuTraduceTraducir.setEnabled(valor);
        break;
    case MUNDO:
           //jMenuNuevoMundo.setEnabled(valor);
            //jMenuAbrirMundo.setEnabled(valor);
            //jMenuGuardarMundo.setEnabled(valor);
            //jMenuMVCerrar.setEnabled(valor);
            break;
    case OBJETOS:
               
            break;
    case REHGRAFICO:
            //redoGraphicButton.setDisable(!valor);
            break;
    case DESHGRAFICO:
            //undoGraphicButton.setDisable(!valor);
            break;
    case AYUDA:// AYUDA
        helpMenuItem.setDisable(!valor);
           
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

    private void changeLanguage(String language) {

        fileMenu.setText(CompiConstants.getValue(language, CompiConstants.FILE_MENU));
        newMenuItem.setText(CompiConstants.getValue(language, CompiConstants.new_menuItem));
        openMenuItem.setText(CompiConstants.getValue(language, CompiConstants.open_menuItem));
        closeMenuItem.setText(CompiConstants.getValue(language, CompiConstants.close_menuItem));
        closeAllMenuItem.setText(CompiConstants.getValue(language, CompiConstants.closeAll_menuItem));
        saveMenuItem.setText(CompiConstants.getValue(language, CompiConstants.save_menuItem));
        saveAllMenuItem.setText(CompiConstants.getValue(language, CompiConstants.saveAll_menuItem));
        saveAsMenuItem.setText(CompiConstants.getValue(language, CompiConstants.saveAs_menuItem));
        printMenuOption.setText(CompiConstants.getValue(language, CompiConstants.printer_menuItem));
        closeAppMenuOption.setText(CompiConstants.getValue(language, CompiConstants.closeApp_menuItem));

        editMenu.setText(CompiConstants.getValue(language, CompiConstants.EDIT_MENU));
        undoMenuItem.setText(CompiConstants.getValue(language, CompiConstants.undo_menuItem));
        redoMenuItem.setText(CompiConstants.getValue(language, CompiConstants.redo_menuItem));
        cutMenuItem.setText(CompiConstants.getValue(language, CompiConstants.cut_menuItem));
        copyMenuItem.setText(CompiConstants.getValue(language, CompiConstants.copy_menuItem));
        pasteMenuItem.setText(CompiConstants.getValue(language, CompiConstants.paste_menuItem));
        removeMenuItem.setText(CompiConstants.getValue(language, CompiConstants.remove_menuItem));
        selectAllMenuItem.setText(CompiConstants.getValue(language, CompiConstants.selectAll_menuItem));

        seeMenu.setText(CompiConstants.getValue(language, CompiConstants.SEE_MENU));
        erroMenuItem.setText(CompiConstants.getValue(language, CompiConstants.errors_menuItem));
        compilerMenu.setText(CompiConstants.getValue(language, CompiConstants.COMPILAR_MENU));
        executorMenuItem.setText(CompiConstants.getValue(language, CompiConstants.executor_menuItem)); 
        virtualMenu.setText(CompiConstants.getValue(language, CompiConstants.VIRTUAL_MENU));
        newWorldMenuItem.setText(CompiConstants.getValue(language, CompiConstants.newWorld_menuItem));
        openWorlMenuItem.setText(CompiConstants.getValue(language, CompiConstants.openWorld_menuItem));
        saveWorldMenuItem.setText(CompiConstants.getValue(language, CompiConstants.saveWorld_menuItem));
        saveAllWorldMenuItem.setText(CompiConstants.getValue(language, CompiConstants.saveAllWorld_menuItem));
        startSimulationMenuItem.setText(CompiConstants.getValue(language, CompiConstants.startSimulation_menuItem));
        stopSimulationMenuItem.setText(CompiConstants.getValue(language, CompiConstants.stopSimulation_menuItem));
        closeSimulationMenuItem.setText(CompiConstants.getValue(language, CompiConstants.closeSimulation_menuItem));
        helpMenu.setText(CompiConstants.getValue(language, CompiConstants.HELP_MENU));
        helpMenuItem.setText(CompiConstants.getValue(language, CompiConstants.help_menuItem));
        aboutMenuItem.setText(CompiConstants.getValue(language, CompiConstants.about_menuItem));

        // language
        languageMenu.setText(CompiConstants.getValue(language, CompiConstants.LANGUAGE));
        englishMenuItem.setText(CompiConstants.getValue(language, CompiConstants.LANGUAGE_ENG));
        spanishMenuItem.setText(CompiConstants.getValue(language, CompiConstants.LANGUAGE_MX));

        complileSuccessErrrorMessage = CompiConstants.getValue(language, CompiConstants.COMPIILATION_SUCCESS);
        //compiMenuBar.
}

}