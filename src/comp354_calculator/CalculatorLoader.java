/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comp354_calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author b_hua
 */
public class CalculatorLoader extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception{
    	
    	Parent root = FXMLLoader.load(getClass().getResource("CalculatorForm.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("comp354_calculator/CalculatorStyle.css");
        
        primaryStage.setTitle("Calculator (Team B)");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
