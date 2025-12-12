package at.bbrz.calculator;

import java.math.BigDecimal;

public class BigDecimalCalculator {
    private final BigDecimal valueA;
    private final BigDecimal valueB;

    public BigDecimalCalculator(BigDecimal valueA, BigDecimal valueB) {
        this.valueA = valueA;
        this.valueB = valueB;
    }

    public BigDecimal sum() {
        return valueA.add(valueB);
    }

    public BigDecimal substract() {
        return valueA.subtract(valueB);
    }

    public BigDecimal multiply() {
        return valueA.multiply(valueB);
    }

    public BigDecimal divide() {
        if (valueB.equals(BigDecimal.ZERO)) {
            throw new ArithmeticException("Division by zero");
        }
        try {
            return valueA.divide(valueB);
        } catch (ArithmeticException e) {
            return valueA.divide(valueB, 2, BigDecimal.ROUND_HALF_UP);
        }
    }

}
