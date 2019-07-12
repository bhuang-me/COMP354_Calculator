/*
 * @(#)Calc.java	2 05/08/19
 * 
 * Copyright (c) 2019-2020 Team B, Comp 354
 * All rights reserved. 
 */

package comp354_calculator;

/**
 * This class contains the implementations of the five transcendental functions of Team B's ETERNITY Calculator. 
 * 
 * @version
 * 		2 05 Aug 2019 @author Team B
 */
public class CalculateTransFunctions {
	
	/**
	 	Constant value of PI */
	private static final double PI = 3.14159265359;
	
	/** 
	 	Constant serves as a stopping condition for the Taylor Series approximation */
	private static final double ACCURACY = 0.0000000001;

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

	public static double decimalExp(double power) {
		double sum = 1;
		double step = 1;
		double factorial = 1;
		final double LN10VALUE = 2.302585092994046;
		double accuracy = 0.0000000001;
		double temp = power;

		if (temp < 0) {
			power *= -1;
		}

		while (Double.compare(step, accuracy) > 0) {
			step = step * LN10VALUE * power / factorial;
			sum = sum + step;

			if (sum == Double.POSITIVE_INFINITY) {
				throw new ArithmeticException("Positive Infinity");
			}
			factorial++;
		}

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
				
				System.out.println(step);
				i++;
			}
			return (isNegative ? (1.0 / taylorSum) : taylorSum);
		}
	}
}