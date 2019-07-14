package comp354_calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CalculateFunctionsTester {

	@Test
	void testSin() {
		// Test input in Radians
		assertEquals(0, CalculateFunctions.sin(0, false));
		assertEquals(0, CalculateFunctions.sin(Math.PI, false));
		assertEquals(1, CalculateFunctions.sin(Math.PI / 2, false));
		assertEquals(-1, CalculateFunctions.sin(3 * Math.PI / 2, false));
		assertEquals(0.001592653, CalculateFunctions.sin(3.14, false));
		assertEquals(-0.958924275, CalculateFunctions.sin(5, false));
		assertEquals(0.259270701, CalculateFunctions.sin(398.72, false));
		assertEquals(0.99265938, CalculateFunctions.sin(-17.4, false));
		assertEquals(-0.084270553, CalculateFunctions.sin(12.482, false));
		
		// Test input in degrees
		assertEquals(1, CalculateFunctions.sin(90, true));
		assertEquals(0, CalculateFunctions.sin(180, true));
		assertEquals(-1, CalculateFunctions.sin(270, true));
		assertEquals(0, CalculateFunctions.sin(360, true));
		assertEquals(1, CalculateFunctions.sin(450, true));
		assertEquals(0.939692621, CalculateFunctions.sin(790, true));
	}

	@Test
	void testSinh() {
		// Test input in Radians
		assertEquals(0, CalculateFunctions.sinh(0, false));
		assertEquals(11.53029203, CalculateFunctions.sinh(3.14, false));
		assertEquals(-74.203210578, CalculateFunctions.sinh(-5, false));
		assertEquals(11.548739357, CalculateFunctions.sinh(Math.PI, false));
		assertEquals(7.0179611E216, CalculateFunctions.sinh(500, false));
		assertEquals(5.3432373E12, CalculateFunctions.sinh(30, false));
		
		// Test input in Degrees
		assertEquals(0, CalculateFunctions.sinh(0, true));
		assertEquals(2.301298902, CalculateFunctions.sinh(90, true));
		assertEquals(414.208297966, CalculateFunctions.sinh(385, true));
		assertEquals(-0.496657511, CalculateFunctions.sinh(-27.4, true));
	}
	
	@Test
	void testDecimalExp() {
		assertEquals(1, CalculateFunctions.decimalExp(0));
		assertEquals(0.002884032, CalculateFunctions.decimalExp(-2.54));
		assertEquals(3.1622777E41, CalculateFunctions.decimalExp(41.5));
		assertEquals(0.0, CalculateFunctions.decimalExp(-10));
		assertEquals(0.0, CalculateFunctions.decimalExp(-44.3256)); // Rounds to 0 too fast
	}
	
	@Test
	void testSqrt() {
		assertEquals(0, CalculateFunctions.sqrt(0));
		assertEquals(9, CalculateFunctions.sqrt(81));
		assertEquals(1.462668794, CalculateFunctions.sqrt(2.1394));
		assertEquals(67.544355797, CalculateFunctions.sqrt(4562.24));
		assertThrows(IllegalArgumentException.class, () -> {
			CalculateFunctions.sqrt(-6);
	    });
	}
	
	@Test
	void testExponential() {
		assertEquals(1, CalculateFunctions.exponential(0));
		assertEquals(31.090428297, CalculateFunctions.exponential(3.4369));
		assertEquals(0.070018205, CalculateFunctions.exponential(-2.659));
		assertEquals(5.8346174E14, CalculateFunctions.exponential(34));
		assertEquals(7.3794796E74, CalculateFunctions.exponential(172.39)); // Does not round to 0
	}

}
