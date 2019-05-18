/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comp354_calculator;

/** SUBJECT TO CHANGE */
public class Calc {
    
    public static double exp (double arg1, double arg2) {
        return 0.0;
    } 
    
    // Input must be in Radians (rad)
    public static double sin (double numInRad) {
        double result = numInRad;
        double numerator = numInRad;
        double denominator = 1;
        
        // Precision up to 3 digits
        double precision = 0.001;
        int k = 1;
        
        // Approximate Taylor Expansion
        Long startTime = System.currentTimeMillis();
        Long resultTime = startTime + 100;
        while(k != 58) {
        	denominator = denominator * (2 * k) * (2 * k  + 1);
        	numerator = numerator * numInRad * numInRad;
        	double stepValue = numerator / denominator;
        	if(k % 2 == 0) {
        		result += stepValue;
        	}
        	else {
        		result -= stepValue;
        	}
        	k++;	
        }
        
        return result;
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


