/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comp354_calculator;


/** SUBJECT TO CHANGE */
public class Calc {
    private static final double PI = 3.14159265359;
    
    public static double exp (double arg1, double arg2) {
        return 0.0;
    } 
    
    // Input must be in Radians (rad)
    public static double sin (double numInRad) {
    	
    	// convert x to the period from 0 to 360 degrees (0 to 2 * PI rad)
    	numInRad = numInRad % (2 * PI);
    	double sum = numInRad;
    	double step = numInRad;
    	
    	// Compute until the value of step is smaller than 9 decimal places
    	int k = 2;
    	double accuracy = 0.0000000001;
    	while(Double.compare(step >= 0 ? step : step * (-1), accuracy) > 0) {
    		step = (-1) * step * numInRad * numInRad/(k * (k + 1));
    		sum += step;
    		k += 2;
    	}
    	
    	// Round result to 6 decimal places
        return CalcHelper.roundDouble(sum, 6);
    }
    
    public static double naturalExp () {
        return 0.0;
    }
    
    public static double decimalExp (double power) {
        return exp(10, power);
    }
    
    public static double sqrt() {
        return 0.0;
    }
}


