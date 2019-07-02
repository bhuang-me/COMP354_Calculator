package comp354_calculator;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalcHelper {
	private static final char SCIENTIFIC_NOTATION_E = 'E';

	// Round Double value to specific number of decimal places
	// NOTE: Even cases are rounded UP
    public static double roundDouble(double value, int places, boolean isSin) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        
        if(isSin) {
        	return bd.doubleValue();
        }
        else {
        	return isInputScientific(value) ? 
            		processScientificNotation(value, places) : bd.doubleValue();
        }
    }

    // Evaluate result of the complex expression 
    // Example: (1 + 3 / 3 * 4) = 5.0
    public static double evaluateExpression(String input) {
    	return evalRPN(postfix(input));
    }
    
    // Evaluate Reverse Polish Notation
    // Throws Division by zero exception
    private static double evalRPN(String[] tokens) {
        Deque<Double> deque = new LinkedList<>();
        
        for (String s: tokens) {
            if (s.equals("*") || s.equals("/") || s.equals("+") || s.equals("-")) {
            	double right = deque.removeFirst();
            	double left = deque.removeFirst();
                
                if (s.equals("*")) {
                    deque.addFirst(left * right);
                }
                if (s.equals("/")) {
                	if(Double.compare(right, 0.0) == 0) {
                		throw new ArithmeticException();
                	}
                	else {
                		deque.addFirst(left / right);
                	}   
                }
                
                if (s.equals("+")) {
                    deque.addFirst(left + right);
                }
                
                if (s.equals("-")) {
                    deque.addFirst(left - right);
                }
                
            } else {
                deque.addFirst(Double.valueOf(s));
            }
        }
        return deque.peekFirst();
    }
    
    // Convert input from infix to postfix
    private static String[] postfix(String infix)
    {
        StringBuilder output = new StringBuilder();
        Deque<String> stack  = new LinkedList<>();

        for (String token : infix.split("\\s")) {
            // operator
            if (ops.containsKey(token)) {
                while ( ! stack.isEmpty() && isHigerPrecision(token, stack.peek()))
                    output.append(stack.pop()).append(' ');
                stack.push(token);

            // digit
            } else {
                output.append(token).append(' ');
            }
        }

        while ( ! stack.isEmpty())
            output.append(stack.pop()).append(' ');

        return output.toString().split("\\s");
    }
    
	private enum Operator{
        ADD(1), SUBTRACT(2), MULTIPLY(3), DIVIDE(4);
        final int precedence;
        Operator(int p) { precedence = p; }
    }
	
	private static Map<String, Operator> ops = new HashMap<String, Operator>() {{
        put("+", Operator.ADD);
        put("-", Operator.SUBTRACT);
        put("*", Operator.MULTIPLY);
        put("/", Operator.DIVIDE);
    }};
	
    private static double processScientificNotation(double number, int maxLength) {
	    
	      String out = null;
		   
	      for (int i = 0; i < maxLength; i++) 
	      {
	         String format = "%." + i + "G";
	         out = String.format(format, number);
	      }
		   
	      return Double.parseDouble(out);
    }
    
    private static boolean isInputScientific(double input) {
    	String inputStr = Double.toString(input);
    	return inputStr.indexOf(SCIENTIFIC_NOTATION_E) > 0;
    }
    
    private static boolean isHigerPrecision(String op, String sub){
        return (ops.containsKey(sub) && ops.get(sub).precedence >= ops.get(op).precedence);
    }
    
}
