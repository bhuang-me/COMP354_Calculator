/*
 * @(#)CalculatorLoader.java	2 05/08/19
 * 
 * Copyright (c) 2019-2020 Team B, Comp 354
 * All rights reserved. 
 */
package comp354_calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Load main application
 * @version
 * 		2 05 Aug 2019 @author Team B
 */
public class CalculatorLoader extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception{
    	
    	/**
    	 	Load representation of the Calculator via fxml*/
    	Parent root = FXMLLoader.load(getClass().getResource("CalculatorForm.fxml"));
        Scene scene = new Scene(root);
        
        /**
         	Load styling */
        scene.getStylesheets().add("comp354_calculator/CalculatorStyle.css");
        
        primaryStage.setTitle("Calculator (Team B)");
        primaryStage.setScene(scene);
        
        /**
         	Run application*/
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     * Call start function from main
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
