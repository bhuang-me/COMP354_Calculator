package comp354_calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Control events in Calculator form(Main_GUI)
 * @author Yaroslav
 *
 */
public class CalculatorFormController {
	private static final String PI = "3.14159265359";
	private static final String EULER = "2.71828182845";
	public boolean isSinInputInDegrees = false;
	
	@FXML
    private Button pi;

    @FXML
    private Button clearAll;
    
    @FXML
    private Button clear;

    @FXML
    private Button euler;

    @FXML
    private Button rad_or_deg;

    @FXML
    private Button seven;

    @FXML
    private Button eight;

    @FXML
    private Button nine;

    @FXML
    private Button root_of_x;

    @FXML
    private Button four;

    @FXML
    private Button five;

    @FXML
    private Button six;

    @FXML
    private Button ten_to_x;

    @FXML
    private Button one;

    @FXML
    private Button two;

    @FXML
    private Button three;

    @FXML
    private Button e_to_x;

    @FXML
    private Button zero;

    @FXML
    private Button dot;

    @FXML
    private Button plus_or_minus;

    @FXML
    private Button sin;
    
    @FXML
    private TextField display;
    
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
    	else if(event.getSource() == rad_or_deg) {
    		isSinInputInDegrees = !isSinInputInDegrees;
    		if(isSinInputInDegrees) {
    			display.setText("deg");
    		}
    		else {
    			display.setText("rad");
    		}
    	}
    	
    	// Transcendental  functions
    	else if(event.getSource() == sin) {
    		double value = 0.0;
    		
    		value = Double.parseDouble(display.getText());
    		
    		value = isSinInputInDegrees ? Calc.sin(value, true) : Calc.sin(value, false);

    		display.setText(Double.toString(value));
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
}
