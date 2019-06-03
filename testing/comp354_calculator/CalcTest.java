package comp354_calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CalcTest {

	@Test
	void testSin() {
		// Test input in radian
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
		assertEquals(0, Calc.sinh(0, false));
		assertEquals(11.530292, Calc.sinh(3.14, false));
		assertEquals(-74.203211, Calc.sinh(-5, false));
		assertEquals(11.548739, Calc.sinh(Math.PI, false));
		assertEquals(201.713157, Calc.sinh(6, false));
		assertEquals(11013.232875, Calc.sinh(10, false));
	}

}
