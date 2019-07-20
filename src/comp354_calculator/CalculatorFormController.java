/*
 * @(#)CalculatorFormController.java	2 05/08/19
 * 
 * Copyright (c) 2019-2020 Team B, Comp 354
 * All rights reserved. 
 */

package comp354_calculator;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * Control events in Calculator form(Main_GUI)
 * Handle mouse and keyboard events
 * Resolve Exceptions
 * @version
 * 		2 05 Aug 2019 @author Team B
 */
public class CalculatorFormController {
	
	/**
	 	Constant for PI */
	private static final String PI = "3.1415926535";
	
	/**
	 	Constant for E */
	private static final String EULER = "2.7182818284";
	
	/**
	 	Constant for input type Degrees */
	private static final String INPUT_DEGREES = "Degrees";
	
	/**
	 	Constant to specify precision of our calculator */
	private static final int DECIMAL_PLACE_NUMBER = 9;
	
	/**
	  Keep track of Equal sign event for proper functionality */
	private static boolean IS_EQUAL_PRESSED = false;
	
	/**
	 	Keep track of the last operation event */
	private static double operand;
	
    /**
 	Enum operators with precedence*/
	private enum Operation{
		Add(1), Subtract(2), Multiply(3), Divide(4);
		final int precedence;
		Operation(int p) { precedence = p; }
		Operation(){ precedence = 0;}
	}
	
	/**
 	Map of operators*/
    private static Map<String, Operation> ops = new HashMap<String, Operation>() {{
    	put("+", Operation.Add);
    	put("-", Operation.Subtract);
    	put("*", Operation.Multiply);
    	put("/", Operation.Divide);
    }};
    
    @FXML
    private TextField display;

    @FXML
    private Button dot;

    @FXML
    private Button plus;

    @FXML
    private Button equals;

    @FXML
    private Button one;

    @FXML
    private Button two;

    @FXML
    private Button three;

    @FXML
    private Button minus;

    @FXML
    private Button plus_or_minus;

    @FXML
    private Button four;

    @FXML
    private Button five;

    @FXML
    private Button six;

    @FXML
    private Button multiply;

    @FXML
    private Button euler;

    @FXML
    private Button seven;

    @FXML
    private Button eight;

    @FXML
    private Button nine;

    @FXML
    private Button divide;

    @FXML
    private Button pi;

    @FXML
    private Button zero;

    @FXML
    private Button sin;

    @FXML
    private Button sinh;

    @FXML
    private Button ten_to_x;

    @FXML
    private Button e_to_x;

    @FXML
    private Button root_of_x;

    @FXML
    private Button clearAll;

    @FXML
    private Button clear;

    @FXML
    private RadioButton radians;

    @FXML
    private ToggleGroup input_type;

    @FXML
    private RadioButton degrees;
    
    @FXML
    private Label smallDisplay;
    
    @FXML
    protected void handleButtonAction(ActionEvent event) {
    	if(event.getSource() == zero) {
    		if(display.getText().equals("0")) {
    			display.setText("0");
    		} else {
    			display.setText(display.getText() + "0");
    		}
    	} else if(event.getSource() == dot) {
    		display.setText(display.getText() + ".");
    	} else if(event.getSource() == one) {
    		display.setText(display.getText() + "1");
    	} else if(event.getSource() == two) {
    		display.setText(display.getText() + "2");
    	} else if(event.getSource() == three) {
    		display.setText(display.getText() + "3");
    	} else if(event.getSource() == four) {
    		display.setText(display.getText() + "4");
    	} else if(event.getSource() == five) {
    		display.setText(display.getText() + "5");
    	} else if(event.getSource() == six) {
    		display.setText(display.getText() + "6");
    	} else if(event.getSource() == seven) {
    		display.setText(display.getText() + "7");
    	} else if(event.getSource() == eight) {
    		display.setText(display.getText() + "8");
    	} else if(event.getSource() == nine) {
    		display.setText(display.getText() + "9");
    	} else if(event.getSource() == plus_or_minus) {
    		if(display.getText().length() == 0) {
    			display.setText("-");
    		} else if(display.getText().charAt(0) == '-') {
    			display.setText(display.getText().substring(1));
    		} else {
    			display.setText("-" + display.getText());
    		}
    	} else if(event.getSource() == clearAll) {
    		display.setText("");
    		smallDisplay.setText("");
    	} else if(event.getSource() == clear) {
    		String txt = display.getText();
    		if(txt.length() > 0) {
    			display.setText(txt.substring(0, txt.length() - 1));
    		}
    	} else if(event.getSource() == pi) {
    		display.setText(PI);
    	} else if(event.getSource() == euler) {
    		display.setText(EULER);
    	} else if(event.getSource() == plus && !isEmptyDisplay(display)) {
    		executeMinusPlusDivideMultiply(Operation.Add, display, smallDisplay);
    	} else if(event.getSource() == minus && !isEmptyDisplay(display)) {
    		executeMinusPlusDivideMultiply(Operation.Subtract, display, smallDisplay);
    	} else if(event.getSource() == divide && !isEmptyDisplay(display)) {
    		executeMinusPlusDivideMultiply(Operation.Divide, display, smallDisplay);
    	} else if(event.getSource() == multiply && !isEmptyDisplay(display)) {
    		executeMinusPlusDivideMultiply(Operation.Multiply, display, smallDisplay);
    	}
    	/**
    	 * Process input string from the small display.
    	 * Set boolean IS_EQUAL_PRESSED equal true, to signal that small display can be cleared.
    	 */
    	else if(event.getSource() == equals && !isEmptyDisplay(display)) {
    		if(IS_EQUAL_PRESSED) {
    			smallDisplay.setText(display.getText());
    		} else {
        		operand = Double.parseDouble(display.getText());
        		smallDisplay.setText(smallDisplay.getText() + Double.toString(operand));
        		
        		try {
        			Double result = CalculateFunctions.evalRPN(postfix(smallDisplay.getText()));
        			
            		display.setText(Double.toString(CalculateFunctions.roundDouble(result, DECIMAL_PLACE_NUMBER, false)));
        		} catch(ArithmeticException e){
        			smallDisplay.setText("");
    				display.setText("Division by zero");
        		}
    		}
    		IS_EQUAL_PRESSED = true;
    	} else if(event.getSource() == sin) {
    		double value = 0.0;
    		RadioButton selectedRadioButton = (RadioButton) input_type.getSelectedToggle();
    		String toogleGroupValue = selectedRadioButton.getText();
    		value = Double.parseDouble(display.getText());
    		value = toogleGroupValue.equals(INPUT_DEGREES) ? 
    				CalculateFunctions.sin(value, true) : CalculateFunctions.sin(value, false);
    		
    		display.setText(Double.toString(CalculateFunctions.roundDouble(value, DECIMAL_PLACE_NUMBER, true)));
    	} else if(event.getSource() == sinh) {
    		double value = 0.0;
    		RadioButton selectedRadioButton = (RadioButton) input_type.getSelectedToggle();
    		String toogleGroupValue = selectedRadioButton.getText();
    		value = Double.parseDouble(display.getText());
    		try {
    			value = toogleGroupValue.equals(INPUT_DEGREES) ? 
    					CalculateFunctions.sinh(value, true) : CalculateFunctions.sinh(value, false);
    			display.setText(Double.toString(CalculateFunctions.roundDouble(value, DECIMAL_PLACE_NUMBER, false)));
    		} catch(ArithmeticException e) {
    			if(e.getMessage().equals("Positive Infinity")) {
    				display.setText("Positive Infinity");
    			} else if(e.getMessage().equals("Negative Infinity")) {
    				display.setText("Negative Infinity");
    			}
    			smallDisplay.setText("");
    		}	
    	} else if(event.getSource() == e_to_x) {
    		double value = Double.parseDouble(display.getText());;
    		
    		try {
    			value = CalculateFunctions.exponential(value);
        		
    			display.setText(Double.toString(CalculateFunctions.roundDouble(value, DECIMAL_PLACE_NUMBER, false)));
    		} catch(ArithmeticException e) {
    			if(e.getMessage().equals("Positive Infinity")) {
    				display.setText("Positive Infinity");
    			}
    			smallDisplay.setText("");
    		}	
    	} else if(event.getSource() == ten_to_x) {
    		double value = Double.parseDouble(display.getText());

    		try {
    			value = CalculateFunctions.decimalExp(value);
    			
    			display.setText(Double.toString(CalculateFunctions.roundDouble(value, DECIMAL_PLACE_NUMBER, false)));
    		} catch(ArithmeticException e) {
    			if(e.getMessage().equals("Positive Infinity")) {
    				display.setText("Positive Infinity");
    			}
    			smallDisplay.setText("");
    		}
    	} else if(event.getSource() == root_of_x) {
    		double value = Double.parseDouble(display.getText());;
    		
    		try {
    			value = CalculateFunctions.sqrt(value);
    			
    			display.setText(Double.toString(CalculateFunctions.roundDouble(value, DECIMAL_PLACE_NUMBER, false)));
    		} catch (IllegalArgumentException e){
    			smallDisplay.setText("");
    			display.setText("Negative root error");
    		}
    	}
    }
    
    /**
     * Execute operator. Options: +, -, /, *
     * @param op Operator
     * @param display Big display reference
     * @param smallDisplay Small display reference
     */
    private static void executeMinusPlusDivideMultiply(Operation op, TextField display, Label smallDisplay) {
    	operand = Double.parseDouble(display.getText());
    	
    	/* Clear small display if operation follows *Equal* button */
    	if(IS_EQUAL_PRESSED) {
    		smallDisplay.setText("");
    		IS_EQUAL_PRESSED = false;
    	}
    	
		switch(op) {
		case Add:
			smallDisplay.setText(smallDisplay.getText() + Double.toString(operand) + " + ");
			break;
		case Subtract:
			smallDisplay.setText(smallDisplay.getText() + Double.toString(operand) + " - ");
			break;
		case Divide:
			smallDisplay.setText(smallDisplay.getText() + Double.toString(operand) + " / ");
			break;
		case Multiply:
			smallDisplay.setText(smallDisplay.getText() + Double.toString(operand) + " * ");
			break;
		}
		display.setText("");
    }
    
    /**
     * Convert input from infix to postfix notation
     * @param Expression in infix notation
     * @return Returns expression in postfix notation 
     */
    private static String[] postfix(String infix)
    {
        StringBuilder output = new StringBuilder();
        Deque<String> stack  = new LinkedList<>();

        for (String token : infix.split("\\s")) {
            
        	/* Process operator */
            if (ops.containsKey(token)) {
                while ( ! stack.isEmpty() && isHigerPrecision(token, stack.peek()))
                    output.append(stack.pop()).append(' ');
                stack.push(token);

            /* Process digit */
            } else {
                output.append(token).append(' ');
            }
        }

        while ( ! stack.isEmpty()) {
        	output.append(stack.pop()).append(' ');
        }
          
        return output.toString().split("\\s");
    }

    /**
     * Compare precedence of operators
     * @param op Operator one
     * @param sub Operator two
     * @return Returns true if second operator has higher precedence; false otherwise
     */
    private static boolean isHigerPrecision(String op, String sub){
        return (ops.containsKey(sub) && ops.get(sub).precedence >= ops.get(op).precedence);
    }
    
    /**
     * Verify that display is empty
     * @param display Big display reference
     * @return Return true if display is empty; false otherwise
     */
    private static boolean isEmptyDisplay(TextField display) {
    	return display.getText().length() == 0;
    }
}
