package comp354_calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CalcTest {

	@Test
	void testSin() {
		assertEquals(0, Calc.sin(0));
		assertEquals(0, Calc.sin(Math.PI));
		assertEquals(1, Calc.sin(Math.PI / 2));
		assertEquals(-1, Calc.sin(3 * Math.PI / 2));
		assertEquals(0.001593, Calc.sin(3.14));
		assertEquals(-0.958924, Calc.sin(5));
		assertEquals(0.259271, Calc.sin(398.72));
		assertEquals(0.992659, Calc.sin(-17.4));
		assertEquals(-0.084271, Calc.sin(12.482));
	}

	@Test
	void testSinh() {
		assertEquals(0, Calc.sinh(0, false));
		assertEquals(11.530292, Calc.sinh(3.14, false));
		assertEquals(-74.203211, Calc.sinh(-5, false));
		assertEquals(11.548739, Calc.sinh(Math.PI, false));
		assertEquals(201.713157, Calc.sinh(6, false));
		assertEquals(11013.232875, Calc.sinh(10, false));
		assertEquals(5343237290762.231445, Calc.sinh(30, false));

	}

}
