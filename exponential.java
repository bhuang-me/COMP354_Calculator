import java.io.*;

public class exponential {

	// e^x is being calculated through use of the Taylor Series to arrive at an approximate value
	public static float exponential(float x) {
		
		//Pass in an x value
		
		float taylorSum = 1;
		
		int i;
		
		for (i = 29; i > 0; --i )
		{
			taylorSum = 1 + x * taylorSum / i;
		}
		
		return taylorSum;
	}
}