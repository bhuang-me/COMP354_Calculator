package comp354_calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalcHelper {
	private static final char SCIENTIFIC_NOTATION_E = 'E';
	private static final int ADD_PLACES_FOR_SCIENTIFIC_NOTATION = 2;
	
	// Round Double value to specific number of decimal places
	// NOTE: Even cases are rounded UP
    public static double roundDouble(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        
        return isInputPositiveScientific(value) ? 
        		processScientificNotation(value, places + ADD_PLACES_FOR_SCIENTIFIC_NOTATION) : bd.doubleValue();
    }

    //We put the cutoff for the display of the value to 9 decimal places
    public static final int MAX_LENGTH = 9;
	
    private static double processScientificNotation(double number) {
	    
	      String out = null;
		   
	      for (int i = 0; i < MAX_LENGTH; i++) 
	      {
	         String format = "%." + i + "G";
	         out = String.format(format, number);
	      }
		   
	      return out;
    }
    
    private static boolean isInputPositiveScientific(double input) {
    	String inputStr = Double.toString(input);
    	return inputStr.indexOf(SCIENTIFIC_NOTATION_E) > 0 && inputStr.indexOf('-') == -1;
    }
}
