package comp354_calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * Control events in Calculator form(Main_GUI)
 * @author Yaroslav
 *
 */
public class CalculatorFormController {
	private static final String PI = "3.1415926535";
	private static final String EULER = "2.7182818284";
	private static final String INPUT_DEGREES = "Degrees";
	private static double firstOperand;
	
	private enum Operation{Add, Subtract, Div, Mult};
	private static Operation  executeOperation;
	
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
    protected void handleButtonAction(ActionEvent event) {
    	if(event.getSource() == zero) {
    		if(display.getText().equals("0")) {
    			display.setText("0");
    		}
    		else {
    			display.setText(display.getText() + "0");
    		}
    	}
    	else if(event.getSource() == dot) {
    		display.setText(display.getText() + ".");
    	}
    	else if(event.getSource() == one) {
    		display.setText(display.getText() + "1");
    	}
    	else if(event.getSource() == two) {
    		display.setText(display.getText() + "2");
    	}
    	else if(event.getSource() == three) {
    		display.setText(display.getText() + "3");
    	}
    	else if(event.getSource() == four) {
    		display.setText(display.getText() + "4");
    	}
    	else if(event.getSource() == five) {
    		display.setText(display.getText() + "5");
    	}
    	else if(event.getSource() == six) {
    		display.setText(display.getText() + "6");
    	}
    	else if(event.getSource() == seven) {
    		display.setText(display.getText() + "7");
    	}
    	else if(event.getSource() == eight) {
    		display.setText(display.getText() + "8");
    	}
    	else if(event.getSource() == nine) {
    		display.setText(display.getText() + "9");
    	}
    	else if(event.getSource() == plus_or_minus) {
    		if(display.getText().length() == 0) {
    			display.setText("-");
    		}
    		else if(display.getText().charAt(0) == '-') {
    			display.setText(display.getText().substring(1));
    		}
    		else {
    			display.setText("-" + display.getText());
    		}
    	}
    	else if(event.getSource() == clearAll) {
    		display.setText("");
    	}
    	else if(event.getSource() == clear) {
    		String txt = display.getText();
    		if(txt.length() > 0) {
    			display.setText(txt.substring(0, txt.length() - 1));
    		}
    	}
    	else if(event.getSource() == pi) {
    		display.setText(PI);
    	}
    	else if(event.getSource() == euler) {
    		display.setText(EULER);
    	}
    	else if(event.getSource() == plus && display.getText().length() != 0) {
    		firstOperand = Double.parseDouble(display.getText());
    		executeOperation = Operation.Add;
    		display.setText(display.getText() + " + ");
    	}
    	else if(event.getSource() == minus && display.getText().length() != 0) {
    		firstOperand = Double.parseDouble(display.getText());
    		executeOperation = Operation.Subtract;
    		display.setText(display.getText() + " - ");
    	}
    	else if(event.getSource() == divide && display.getText().length() != 0) {
    		firstOperand = Double.parseDouble(display.getText());
    		executeOperation = Operation.Div;
    		display.setText(display.getText() + " / ");
    	}
    	else if(event.getSource() == multiply && display.getText().length() != 0) {
    		firstOperand = Double.parseDouble(display.getText());
    		executeOperation = Operation.Mult;
    		display.setText(display.getText() + " * ");
    	}
    	else if(event.getSource() == equals) {
    		double secondOperand = CalculatorFormControllerHelper.processOperationInput(
    				display.getText(), executeOperation);
    		
    		switch(executeOperation) {
    			case Add:
    				display.setText(Double.toString(firstOperand + secondOperand));
    				break;
    			case Subtract:
    				display.setText(Double.toString(firstOperand - secondOperand));
    				break;
    			case Div:
    				try {
    					if(Double.compare(secondOperand, 0) == 0) {
    						throw new ArithmeticException();
    					}
    					display.setText(Double.toString(firstOperand / secondOperand));
    				}
    				catch(ArithmeticException  e){
    					display.setText("Division by zero");
    				}
    				break;
    			case Mult:
    				display.setText(Double.toString(firstOperand * secondOperand));
    				break;
    		}
    	}
    	// Transcendental  functions
    	else if(event.getSource() == sin) {
    		double value = 0.0;
    		RadioButton selectedRadioButton = (RadioButton) input_type.getSelectedToggle();
    		String toogleGroupValue = selectedRadioButton.getText();
    		value = Double.parseDouble(display.getText());
    		
    		value = toogleGroupValue.equals(INPUT_DEGREES) ? Calc.sin(value, true) : Calc.sin(value, false);

    		display.setText(Double.toString(value));
    	}
    	else if(event.getSource() == sinh) {
    		double value = 0.0;
    		RadioButton selectedRadioButton = (RadioButton) input_type.getSelectedToggle();
    		String toogleGroupValue = selectedRadioButton.getText();
    		value = Double.parseDouble(display.getText());
    		
    		value = toogleGroupValue.equals(INPUT_DEGREES) ? Calc.sinh(value, true) : Calc.sinh(value, false);

    		display.setText(Double.toString(value));
    	}
    	else if(event.getSource() == e_to_x) {
    		double value = Double.parseDouble(display.getText());;
    		
    		try {
    			value = Calc.exponential(value);
        		
        		display.setText(Double.toString(value));
    		}
    		catch(ArithmeticException e) {
    			display.setText("Infinity");
    		}	
    	}
    	else if(event.getSource() == ten_to_x) {
    		double value = Double.parseDouble(display.getText());

    		try {
    			value = Calc.decimalExp(value);
        		
        		display.setText(Double.toString(value));
    		}
    		catch(ArithmeticException e) {
    			display.setText("Infinity");
    		}
    	}
    	else if(event.getSource() == root_of_x) {
    		double value = Double.parseDouble(display.getText());;
    		
    		try {
    			value = Calc.sqrt(value);
    			
    			display.setText(Double.toString(value));
    		}
    		catch (IllegalArgumentException e){
    			display.setText("Error");
    		}
    	}
    }
    
    // Inner class for the form helpers
    private static class CalculatorFormControllerHelper{
    	
    	// Preprocess text for +, -, *, / operations
    	private static double processOperationInput(String input, Operation oper) {
    		switch(executeOperation) {
				case Add:
					return Double.parseDouble(input.split("\\+")[1].substring(1));
				case Subtract:
					return Double.parseDouble(input.split("-")[1].substring(1));
				case Div:
					return Double.parseDouble(input.split("/")[1].substring(1));
				case Mult:
					return Double.parseDouble(input.split("\\*")[1].substring(1));
    		}
    		return 0.0;
    	}
    }
}
