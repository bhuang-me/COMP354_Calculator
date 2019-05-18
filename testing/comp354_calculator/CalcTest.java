package comp354_calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CalcTest {

	@Test
	void testSin() {
		assertEquals(0, Calc.sin(0));
		assertEquals(0, Calc.sin(Math.PI));
		assertEquals(-0.958924274, Calc.sin(5));
		assertEquals(0.259270701, Calc.sin(398.72));
		assertEquals(0.2592, Calc.sin(398.72));
	}

}
