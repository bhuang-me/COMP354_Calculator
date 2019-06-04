package comp354_calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalcHelper {
	private static final char SCIENTIFIC_NOTATION_E = 'E';
	
	// Round Double value to specific number of decimal places
	// NOTE: Even cases are rounded UP
    public static double roundDouble(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        
        return isInputPositiveScientific(value) ? 
        		processScientificNotation(value, places) : bd.doubleValue();
    }
	
    private static double processScientificNotation(double number, int maxLength) {
	    
	      String out = null;
		   
	      for (int i = 0; i < maxLength; i++) 
	      {
	         String format = "%." + i + "G";
	         out = String.format(format, number);
	      }
		   
	      return Double.parseDouble(out);
    }
    
    private static boolean isInputPositiveScientific(double input) {
    	String inputStr = Double.toString(input);
    	return inputStr.indexOf(SCIENTIFIC_NOTATION_E) > 0 && inputStr.indexOf('-') == -1;
    }
}
