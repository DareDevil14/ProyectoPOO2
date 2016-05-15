package com.compimac.editor;

import inage2000ver3.CompiMenuBar;
import inage2000ver3.CompiToolBar;
import com.util.Util;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.Optional;
import java.util.Vector;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javax.swing.JFileChooser;

/**
 *
 * @author ramasamy
 */
public class EditorManager {

    private static EditorManager editor = null;

    private TabPane tabPane;
    
    private Vector<Editor> editors = new Vector();
    
    private Editor currentEditor = null;
    
    static boolean ignoreNextPress = false;
    
    private int totalNoOfTabs = 1;

    public TabPane getPane() {
        return tabPane;
    }

    public static EditorManager getInstance() {
        if (editor == null) {
            editor = new EditorManager();
            editor.init();
        }
        return editor;
    }

    public void init() {
        // Add an empty editor to the tab pane
        tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends Tab> tab, Tab oldTab, Tab newTab) -> {
            // As the current tab changes, reset the var that tracks
            // the editor in view. This is used for tracking modified
            // editors as the user types
            
            if(newTab != null ) {
                currentEditor = getEditorForTextArea((TextArea)newTab.getContent());
                currentEditor.viewMsg(2);
            }    
            
            
            
        });
      
        tabPane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                String text = ke.getText();
                KeyCode code = ke.getCode();
                handleKeyPress(ke);
            }
        });

        tabPane.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                String text = ke.getText();
                KeyCode code = ke.getCode();
                if (code == KeyCode.BACK_SPACE
                        || code == KeyCode.ENTER
                        || code == KeyCode.DELETE) {
                    indicateFileModified();
                }

                // After the "s" is pressed to invoke a save action, make
                // sure the subsequent release doesn't mark the file
                // to be saved once again
                if (!(ke.isControlDown() || ke.isMetaDown())) {
                    if (text.equals("s") && ignoreNextPress) {
                        ignoreNextPress = false;
                        return;
                    }
                    handleKeyPress(ke);
                }
            }
        });

//        scene.setOnKeyTyped(new EventHandler<KeyEvent>() {
//                public void handle(KeyEvent ke) {
//                    handleKeyPress(ke);
//                }
//            });
//
        // Make sure one new editor is open by default
        //createNew();
    }

    public void createNew() {
        Tab tab = new Tab();
        Editor content = new Editor();
        content.setText("sociedad:\n\n\n\n\nfinSociedad\n\ncomportamiento:\n\n\n\n\nfinComportamiento\n\nacciones:\n\n\n\n\nfinAcciones");
        content.setFileName("Sociedad"+ totalNoOfTabs); 
        this.currentEditor = content;
        //content.setModified(true);
        editors.add((Editor) content);
        tab.setText("Sociedad"+totalNoOfTabs);
        tab.setContent(content.getRoot());
        tabPane.getTabs().add(tab);
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        selectionModel.select(tab);
        
        // increase total no. of editors count.
        totalNoOfTabs+=1;
              
    }
    
    private void indicateFileModified() {
        if (currentEditor != null && currentEditor.isModified()) {
            return;
        }

        // Get current tab, add an "*" to its name to indicate modified
        //System.out.println("Indicating text modified");
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        Tab selectedTab = selectionModel.getSelectedItem();
        TextArea area = (TextArea) selectedTab.getContent();
        currentEditor = getEditorForTextArea(area);
        String modName = selectedTab.getText();
        if (!modName.endsWith("*")) {
            modName += "*";
            selectedTab.setText(modName);
        }
        currentEditor.setModified(true); 
        
        CompiToolBar.getInstanceOf().validar(3);
        CompiMenuBar.getInstanceOf().validar(3);
    }

    private Editor getEditorForTextArea(TextArea area) {
        Iterator<Editor> iter = editors.iterator();
        while (iter.hasNext()) {
            Editor editor = iter.next();
            if (area == (TextArea) editor.getRoot()) {
                return editor;
            }
        }

        return null;
    }

    public boolean chooseAndLoadFile() {
        boolean isFileOpened = false;
        FileChooser fc = new FileChooser();
        
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Age Files (*.age)", "*.age");
        fc.getExtensionFilters().add(extFilter);                                
        
        File fileToOpen = fc.showOpenDialog(null);
        
        
        
        if (fileToOpen != null) {
            isFileOpened = true ;
            // Read the file, and set its contents within the editor
            String openFileName = fileToOpen.getAbsolutePath();
            StringBuffer sb = new StringBuffer();
            try (FileInputStream fis = new FileInputStream(fileToOpen);
                    BufferedInputStream bis = new BufferedInputStream(fis)) {
                while (bis.available() > 0) {
                    sb.append((char) bis.read());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Create the editor with this content and store it
            Editor editor = new Editor();
            editor.setText(sb.toString());
            editor.setFileName(fileToOpen.getName()); 
            editor.setDirectoryName(fileToOpen.getParent());
            editors.add(editor);

            // Create a tab to house the new editor
            Tab tab = new Tab();
            tab.setText(fileToOpen.getName());
            tab.setContent(editor.getRoot());
            tabPane.getTabs().add(tab);
            // Make sure the new tab is selected
            SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
            selectionModel.select(tab);
        }
        
        return isFileOpened;
    }

    public void saveFileRev() {
        
        boolean success = false;
        Editor editor = null;
        File file = null;

        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        Tab selectedTab = selectionModel.getSelectedItem();
        editor = getEditorForTextArea((TextArea) selectedTab.getContent());
        if (editor == null) {
            return;
        }
        
        String openFileName = editor.getDirectoryName() + "\\" + editor.getFileName();

        if (editor.getDirectoryName() == null || editor.getFileName() == null) {
            // No file was opened. The user just started typing
            // Save new file now
            FileChooser fc = new FileChooser();
            File newFile = fc.showSaveDialog(null);
            if (newFile != null) {
                // Check for a file extension and add ".age" if missing
                if (!newFile.getName().contains(".")) {
                    String newFilePath = newFile.getAbsolutePath();
                    newFilePath += ".age";
                    newFile.delete();
                    newFile = new File(newFilePath);
                }
                file = newFile;
                //openFileName = new String(newFile.getAbsolutePath());
                editor.setFileName(newFile.getName());
                selectedTab.setText(newFile.getName());
            }
        } else {
            // User is saving an existing file
            file = new File(openFileName);
        }

        // Write the content to the file
        try (FileOutputStream fos = new FileOutputStream(file);
                BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            String text = editor.getText();
            bos.write(text.getBytes());
            bos.flush();
            success = true;
        } catch (Exception e) {
            success = false;
            System.out.println("File save failed (error: " + e.getLocalizedMessage() + ")");
            e.printStackTrace();
        } finally {
            if (success) {
                if (editor != null) {
                    editor.setModified(false);
                }

                // The the tab's filename
                selectedTab.setText(file.getName());
            }
        }
    }

    private void handleKeyPress(KeyEvent ke) {
        boolean modifier = false;
        String text = ke.getText();
        KeyCode code = ke.getCode();
        if (ke.isControlDown() || ke.isMetaDown()) {
            modifier = true;
        }

        if (modifier && text.equalsIgnoreCase("s")) {
            saveFileRev();
            ignoreNextPress = true;
        } else if (!ignoreNextPress) {
            if (code == KeyCode.BACK_SPACE
                    || code == KeyCode.ENTER
                    || code == KeyCode.DELETE) {
                indicateFileModified();
            } else if (text != null && text.length() > 0) {
                if (!modifier) {
                    indicateFileModified();
                }
            }
        }
    }
    
    public void saveAllFiles() {
        
    }
    
    
    
    public void stop() {
        // Go through all open files and save, then exit
        //Iterator<Tab> iter = tabPane.getTabs().iterator();
        for (Tab tab : tabPane.getTabs()) {
            try {
                // Each file is saved by making each tab active then saving
                //Tab tab = iter.next();
                Node node = tab.getContent();
                if (node != null) {
                    TextArea area = (TextArea) node;
                    currentEditor = getEditorForTextArea(area);
                    if (currentEditor.isModified()) {
                        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
                        selectionModel.select(tab);
                       
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmación");
                    //alert.setHeaderText("?Desea guardar los cambios de ");
                    alert.setContentText("?Desea guardar los cambios de "
                            + Util.getInstanceOf().getOnlyName(EditorManager.getInstance().getCurrentEditor().getFileName()));

                    ButtonType buttonTypeOne = new ButtonType("Si");
                    ButtonType buttonTypeTwo = new ButtonType("No");
                    ButtonType buttonTypeCancel = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);

                    alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == buttonTypeOne){
                        saveFileRev();
                    } else if (result.get() == buttonTypeTwo) {
                         closeTab();
                    }  else {
                        // do nothing
                    }
                    }
                }
            } catch (Exception e) {
                System.err.println("Exeption occurred in EditorManager class during application closing");
            }
        }
        if (getNoOfEditors() == 0) {
            System.exit(0);
        }    
    }
    
    public Editor getCurrentEditor(){
        return currentEditor;
    }
    
    public int getNoOfEditors(){
        if(editors != null) 
            return editors.size();
        else 
            return 0;
    }
    
    public void closeTab() {
        editors.remove(currentEditor);
   
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        Tab selectedTab = selectionModel.getSelectedItem();
  
        selectedTab.getTabPane().getTabs().remove(selectedTab); 
    }

    public void closeAllTabs() {
        // Go through all open files and save, then exit
        editors.removeAllElements();
        tabPane.getTabs().removeAll(tabPane.getTabs());
        
    }
    
    public void selectNext(){
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        selectionModel.selectNext();
    }

    public void selectPrevious(){
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        selectionModel.selectPrevious();       
    }
    
}