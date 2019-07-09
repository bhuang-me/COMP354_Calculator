/*
 * @(#)CalcHelper.java	2 05/08/19
 * 
 * Copyright (c) 2019-2020 Team B, Comp 354
 * All rights reserved. 
 */

package comp354_calculator;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * This class contains Rounding and Operator precedence processing.
 * 
 * @version
 * 		2 05 Aug 2019 @author Team B
 */
public class CalcHelper {
	
	/**
	 	Constant value for the scientific notation processing */
	private static final char SCIENTIFIC_NOTATION_E = 'E';
	
    /**
     * Round value to some specific number of decimal places or convert to scientific notation.
     * Special processing for Sin(x).
     * Calling Sin(PI) must yield 0, instead of very small accurate value.
     * @param value Value to round
     * @param places Number of places after the decimal
     * @param isSin Indication if rounding needs to be applied on sin
     * @return Returns Value with the proper rounding
     */
    public static double roundDouble(double value, int places, boolean isSin) {
        if (places < 0) {
        	throw new IllegalArgumentException();
        }

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        
        if(isSin) {
        	return bd.doubleValue();
        } else {
        	return (isInputScientific(value) ? 
            		processScientificNotation(value, places) : bd.doubleValue());
        }
    }

    /**
     * Evaluate operator precedence in complex expressions
     * Example: 4 + 5 * 2 = 14
     * @param input String expression
     * @return Return result of the expression
     */
    public static double evaluateExpression(String input) {
    	return evalRPN(postfix(input));
    }
    
    /**
     * Evaluate Reverse-Polish notation
     * @param tokens Array of tokens
     * @return Return result of the tokens
     * @throws Throws ArithmeticException for division by zero
     */
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
                	} else {
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
     	Enum operators with precedence*/
	private enum Operator{
        ADD(1), SUBTRACT(2), MULTIPLY(3), DIVIDE(4);
        final int precedence;
        Operator(int p) { precedence = p; }
    }
	
	/**
	 	Map of operators*/
	private static Map<String, Operator> ops = new HashMap<String, Operator>() {{
        put("+", Operator.ADD);
        put("-", Operator.SUBTRACT);
        put("*", Operator.MULTIPLY);
        put("/", Operator.DIVIDE);
    }};
	
    /**
     * Reduce number of decimal places in scientific notation
     * @param number Value in scientific notation
     * @param maxLength Maximum number of values after decimal place
     * @return Returns rounded scientific notation
     */
    private static double processScientificNotation(double number, int maxLength) {
	    
	      String out = null;
		   
	      for (int i = 0; i < maxLength; i++) 
	      {
	         String format = "%." + i + "G";
	         out = String.format(format, number);
	      }
		   
	      return Double.parseDouble(out);
    }
    
    /**
     * Verify if input is in scientific notation.
     * Check if it contains 'E' character
     * @param input Value
     * @return Returns true if scientific; false otherwise
     */
    private static boolean isInputScientific(double input) {
    	String inputStr = Double.toString(input);
    	return inputStr.indexOf(SCIENTIFIC_NOTATION_E) > 0;
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
    
}
