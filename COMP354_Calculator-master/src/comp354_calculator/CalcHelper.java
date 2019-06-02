package comp354_calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalcHelper {

	// Round Double value to specific number of decimal places
	// NOTE: Even cases are rounded UP
    public static double roundDouble(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
