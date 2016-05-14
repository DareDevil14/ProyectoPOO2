/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inage2000ver3;

import com.compimac.editor.EditorManager;
import javafx.geometry.Pos;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author rob
 */
public class CompiContentPane {
    private static CompiContentPane compiContentPane = null;
    
    SplitPane sp = new SplitPane();
    StackPane sp1 = new StackPane();
    StackPane sp2 = new StackPane();
    private CompiContentPane(){
       
    }
    
    public SplitPane getPane(){
        return sp;
    }
    public static CompiContentPane getInstance(){
        if (compiContentPane == null) {
            compiContentPane = new CompiContentPane();  
            compiContentPane.initContentPane();
        }
        return compiContentPane;
    }
    
    private void initContentPane() {
        sp1.getChildren().add(EditorManager.getInstance().getPane()) ;   
        sp2.getChildren().add(Simulador.getInstanceOf().getPane());   
       
        sp.setMinHeight(500);
        sp1.setMinWidth(500);
        sp2.setMinWidth(300);
        
        sp.getItems().addAll(sp1,sp2);
        
        sp.setDividerPositions(0.3f, 0.6f);
    }


    
}
