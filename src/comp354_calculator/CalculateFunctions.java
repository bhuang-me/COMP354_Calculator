/*
 * @(#)CalculateFunctions.java	2 05/08/19
 * 
 * Copyright (c) 2019-2020 Team B, Comp 354
 * All rights reserved. 
 */

package comp354_calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Deque;
import java.util.LinkedList;

/**
 * This class contains the implementations of the five transcendental functions of Team B's ETERNITY Calculator. 
 * 
 * @version
 * 		2 05 Aug 2019 @author Team B
 */
public class CalculateFunctions {
	
	/**
	 	Constant value of PI */
	private static final double PI = 3.14159265359;
	
	/**
 		Constant value for the scientific notation processing */
	private static final char SCIENTIFIC_NOTATION_E = 'E';

	/** 
	 	Constant serves as a stopping condition for the Taylor Series approximation */
	private static final double ACCURACY = 0.0000000001;

	
	/**
	 * Evaluate sin(x) of the input
	 * @param num Value to evaluate
	 * @param isNumDegree Indicates if input is in DEG or RAD
	 * @return returns sin value of the num
	 */
	public static double sin(double num, boolean isNumDegree) {
		
		/* Convert to Radian if input is in Degrees */
		if (isNumDegree) {
			num = num * PI / 180;
		}
		
		/* Convert x to the period from 0 to 360 degrees (0 to 2 * PI rad) */
		num = num % (2 * PI);
		double sum = num;
		double step = num;

		/* Compute until the value of step is smaller than 9 decimal places */
		int k = 2;
		while (Double.compare((step >= 0 ? step : step * (-1)), ACCURACY) > 0) {
			step = (-1) * step * num * num / (k * (k + 1));
			sum += step;
			k += 2;
		}

		return sum;
	}

	/**
	 * Evaluate sinh(x) of the input
	 * @param num Value to evaluate
	 * @param isNumDegree Indicates if input is in DEG or RAD
	 * @return returns sinh value of the num
	 * @throws ArithmeticException if input is Positive or Negative infinity
	 */
	public static double sinh(double num, boolean isNumDegree) {

		/* Convert to Radian if input is in Degrees */
		if (isNumDegree) {
			num = num * PI / 180;
		}

		double sum = num;
		double step = num;

		/* Compute until the value of step is smaller than 9 decimal places */
		int k = 2;
		while (Double.compare(step >= 0 ? step : step * (-1), ACCURACY) > 0) {
			step = step * num * num / (k * (k + 1));
			sum += step;

			if (sum == Double.POSITIVE_INFINITY) {
				throw new ArithmeticException("Positive Infinity");
			} else if (sum == Double.NEGATIVE_INFINITY) {
				throw new ArithmeticException("Negative Infinity");
			}
			k += 2;
		}
		
		return sum;
	}
	

	/*
	 * Calculates 10 to the power of a given exponent through the use of its Taylor Series	
	 * @param power is the value of the exponent in the calculation
	 */
	public static double decimalExp(double power) {
		double sum = 1;
		double step = 1;
		double factorial = 1;
		final double LN10VALUE = 2.302585092994046;
		double accuracy = 0.0000000001;
		double temp = power;
		
		/*Checks if the power was negative and converts the power to a positive number*/
		if (temp < 0) {
			power *= -1;
		}
		
		/*Calculates the taylor series until the step is less than 9 decimal places*/
		while (Double.compare(step, accuracy) > 0) {
			step = step * LN10VALUE * power / factorial;
			sum = sum + step;
			
			/*Throws an exception if the sum is already recognized as positive infinity*/
			if (sum == Double.POSITIVE_INFINITY) {
				throw new ArithmeticException("Positive Infinity");
			}
			factorial++;
		}
		
		/*Adjusts sum to its expected value if the power was initially negative*/
		if (temp < 0) {
			sum = 1.0 / sum;
		}

		return sum;
	}

        /*
        * Calculates the square root of a number using the Babylonian method.
	* @param res and arg/res are overestimates and underestimates
        * @param guess is a correction brought to our previous estimate
        * @param arg is the argument that we wish to calculate the root for
	* The closer the estimates are, the better the next guess will be.
	*/
	public static double sqrt(double arg) {
		
		if (arg < 0) {
			throw new IllegalArgumentException("Cannot calculate negative square roots.");
		} else if (arg == 0) {
			return 0;
		}
		double guess = 0.0;
		double res = 1.0;

                //Loop until we converge to the true root
		while (res != guess) {
			guess = res;
			res = (res + arg / res) / 2;
		}

		return res;
	}

	
	/*
	 * Insert an x value and e^x will be calculated through use of the Taylor Series
	 * to arrive at an approximate value	
	 * @param x is the value that the exponential function will calculate
	 */
	public static double exponential(double x) {
		boolean isNegative = false;
		double taylorSum = 1;
		double step = 1;
		int i = 1;
		
		if (Double.compare(x, 0.0) < 0) {
			x *= -1;
			isNegative = true;
		}

		if (x == 0) {
			return taylorSum;
		}
		else {
			
			/* Taylor series is calculated through looping this equation i times */
			while (Double.compare(step, ACCURACY) > 0) {
				step = step * x / i;
				taylorSum += step;

				if (taylorSum == Double.POSITIVE_INFINITY) {
					throw new ArithmeticException("Positive Infinity");
				}
				
				i++;
			}
			return (isNegative ? (1.0 / taylorSum) : taylorSum);
		}
	}
    
    /**
     * Evaluate Reverse-Polish notation
     * @param tokens Array of tokens
     * @return Return result of the tokens
     * @throws Throws ArithmeticException for division by zero
     */
    public static double evalRPN(String[] tokens) {
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
	
}