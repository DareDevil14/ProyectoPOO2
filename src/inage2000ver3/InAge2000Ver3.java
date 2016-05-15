package inage2000ver3;

import com.compimac.constants.CompiConstants;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InAge2000Ver3 extends Application {
    
    @Override
    public void start(Stage primaryStage) {                
        
        VBox mainBox = new VBox();
        mainBox.getChildren().addAll(CompiMenuBar.getInstanceOf().getMenuBar(),
        CompiToolBar.getInstanceOf().getToolbar(), CompiContentPane.getInstance().getPane(),
        CompiStatusPane.getInstance().getPane());
        
        Scene scene = new Scene(mainBox,780,600);
        //scene.getStylesheets().add("/styles/Styles.css");
        
        primaryStage.setTitle(CompiConstants.APP_NAME);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
