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
    
    public static double sin () {
        return 0.0;
    }
    
    public static double naturalExp () {
        return 0.0;
    }
    
    public static double decimalExp (double power) {
        return exp(10, power);
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
        
        return res;
    }
    
    
}


