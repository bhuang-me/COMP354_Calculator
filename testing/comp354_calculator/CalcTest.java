package comp354_calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CalcTest {

	@Test
	void testSin() {
		// Test input in Radians
		assertEquals(0, Calc.sin(0, false));
		assertEquals(0, Calc.sin(Math.PI, false));
		assertEquals(1, Calc.sin(Math.PI / 2, false));
		assertEquals(-1, Calc.sin(3 * Math.PI / 2, false));
		assertEquals(0.001592653, Calc.sin(3.14, false));
		assertEquals(-0.958924275, Calc.sin(5, false));
		assertEquals(0.259270701, Calc.sin(398.72, false));
		assertEquals(0.99265938, Calc.sin(-17.4, false));
		assertEquals(-0.084270553, Calc.sin(12.482, false));
		
		// Test input in degrees
		assertEquals(1, Calc.sin(90, true));
		assertEquals(0, Calc.sin(180, true));
		assertEquals(-1, Calc.sin(270, true));
		assertEquals(0, Calc.sin(360, true));
		assertEquals(1, Calc.sin(450, true));
		assertEquals(0.939692621, Calc.sin(790, true));
	}

	@Test
	void testSinh() {
		// Test input in Radians
		assertEquals(0, Calc.sinh(0, false));
		assertEquals(11.53029203, Calc.sinh(3.14, false));
		assertEquals(-74.203210578, Calc.sinh(-5, false));
		assertEquals(11.548739357, Calc.sinh(Math.PI, false));
		assertEquals(7.017961089E216, Calc.sinh(500, false));
		assertEquals(5.34323729E12, Calc.sinh(30, false));
		
		// Test input in Degrees
		assertEquals(0, Calc.sinh(0, true));
		assertEquals(2.301298902, Calc.sinh(90, true));
		assertEquals(414.208297966, Calc.sinh(385, true));
		assertEquals(-0.496657511, Calc.sinh(-27.4, true));
	}
	
	@Test
	void testDecimalExp() {
		assertEquals(1, Calc.decimalExp(0));
		assertEquals(0.002884032, Calc.decimalExp(-2.54));
		assertEquals(3.16227766E41, Calc.decimalExp(41.5));
		assertEquals(0.0, Calc.decimalExp(-10));
		assertEquals(0.0, Calc.decimalExp(-44.3256)); // Rounds to 0 too fast
	}
	
	@Test
	void testSqrt() {
		assertEquals(0, Calc.sqrt(0));
		assertEquals(9, Calc.sqrt(81));
		assertEquals(1.462668794, Calc.sqrt(2.1394));
		assertEquals(67.544355797, Calc.sqrt(4562.24));
		assertThrows(IllegalArgumentException.class, () -> {
	        Calc.sqrt(-6);
	    });
	}
	
	@Test
	void testExponential() {
		assertEquals(1, Calc.exponential(0));
		assertEquals(31.090428297, Calc.exponential(3.4369));
		assertEquals(0.070018205, Calc.exponential(-2.659));
		assertEquals(5.834617425E14, Calc.exponential(34));
		assertEquals(2.61138141036066E13, Calc.exponential(172.39)); // Does not round to 0
	}

}
