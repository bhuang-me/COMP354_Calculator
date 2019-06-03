/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comp354_calculator;


/** SUBJECT TO CHANGE */
public class Calc {
    private static final double PI = 3.14159265359;
    private static final int DECIMAL_PLACE_NUMBER = 9;
    private static final double SIN_SINH_ACCURACY = 0.0000000001;
    
    public static double exp (double arg1, double arg2) {
        return 0.0;
    } 
    
    
    public static double sin (double num, boolean isNumDegree) {
    	// Convert to Radian if inout is in Degrees
    	if(isNumDegree) {
    		num = num * PI / 180;
    	}
    		
    	// convert x to the period from 0 to 360 degrees (0 to 2 * PI rad)
    	num = num % (2 * PI);
    	double sum = num;
    	double step = num;
    	
    	// Compute until the value of step is smaller than 9 decimal places
    	int k = 2;
    	while(Double.compare(step >= 0 ? step : step * (-1), SIN_SINH_ACCURACY) > 0) {
    		step = (-1) * step * num * num/(k * (k + 1));
    		sum += step;
    		k += 2;
    	}
    	
    	// Round result to 6 decimal places
        return CalcHelper.roundDouble(sum, DECIMAL_PLACE_NUMBER);
    }

    public static double sinh (double num, boolean isNumDegree) {

        // Convert to Radian if inout is in Degrees
        if(isNumDegree) {
            num = num * PI / 180;
        }

        double sum = num;
        double step = num;

        int k = 2;

        while (Double.compare(step >= 0 ? step : step * (-1), SIN_SINH_ACCURACY) > 0){
            step = step * num * num/(k * (k + 1));
            sum += step;
            k += 2;
        }

        return CalcHelper.roundDouble(sum, DECIMAL_PLACE_NUMBER);
    }
    
    public static double naturalExp () {
        return 0.0;
    }
    
    public static double decimalExp (double power) {
		double sum=1;
		double step=1;
		double factorial=1;
		final double LN10VALUE=2.302585092994046;
		double accuracy=0.0000000001;
		double temp=power;
		
		if(temp<0) {
			power*=-1;
		}
		
		while(Double.compare(step, accuracy)>0) {
			step=step*LN10VALUE*power/factorial;
			sum=sum+step;
			factorial++;
		}
		
		if(temp<0) {
			sum=1.0/sum;
		}
		
		if(sum>=1.0E100) 
			throw new ArithmeticException("Error 2");
		
		return CalcHelper.roundDouble(sum, 6);
    }
    
    public static double sqrt(double arg) {
        /* Calculates the square root of a number using the Babylonian method */
        if(arg < 0) {
            throw new IllegalArgumentException("Cannot calculate negative square roots.");
        } else if (arg == 0) {
            return 0;
        }
        double guess = 0.0;
        double res = 1.0;
        /* res and arg/res are overestimates and underestimates (or vice versa) for the root of arg
        The closer the estimates are, the better the next guess will be.
        We loop this algorithm until we converge to the true root
        (or when the difference between the guess and result is lower than a certain amount) */
        while(res != guess) {
            guess = res;
            res = (res + arg / res) / 2;
        }
        
        return CalcHelper.roundDouble(res, DECIMAL_PLACE_NUMBER);
    }
    
    /* Insert an x value and e^x will be calculated through use of the Taylor Series to arrive at an approximate value */
	public static double exponential(double x)
	{
		
		double taylorSum = 1;
		
		if (x == 0)
			return taylorSum;
    
	    else
	    {	
			int i;
			
			//Taylor series is calculated through looping this equation i times 
			
			for (i = 29; i > 0; --i )
			{
				taylorSum = 1 + x * taylorSum / i;
			}
			
			//We must include exception handling when the result of the calculation is positive infinity to prevent the computer from running infinitely
			//which would overflow the system 
			
			if(taylorSum == Double.POSITIVE_INFINITY) 
				
	            throw new ArithmeticException("x value has overloaded system");
			
	        else
	        	return taylorSum;
		}
		
	}
}


