/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inage2000ver3;

import com.compimac.editor.EditorManager;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;

/**
 *
 * @author rob
 */
public class CompiStatusPane {
    
    private static CompiStatusPane compiStatusPane = null;
    
    SplitPane sp = new SplitPane();
    StackPane sp1 = new StackPane();
    StackPane sp2 = new StackPane();
    
    TextArea logs = new TextArea();
    
    private CompiStatusPane(){
       
    }
    
    public SplitPane getPane(){
        return sp;
    }
    public static CompiStatusPane getInstance(){
        if (compiStatusPane == null) {
            compiStatusPane = new CompiStatusPane();  
            compiStatusPane.initContentPane();
        }
        return compiStatusPane;
    }
    
    private void initContentPane() {     
        logs.setEditable(false);
        sp1.getChildren().add(logs) ; 
        sp.getItems().addAll(sp1);
    
    }
    
    public void setStatusMessages(String text) {
        logs.setText(text);
    }


}
