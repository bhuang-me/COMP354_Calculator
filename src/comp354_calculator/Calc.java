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
        if(arg <= 0) {
            return 0.0;
        }
        double guess = 0.0;
        double res = 1.0;
        while(res != guess) {
            guess = res;
            res = (res + arg / res) / 2;
            System.out.println("Next guess is " + guess + ", result approximation is " + res);
        }
        
        return res;
    }
    
    
}


