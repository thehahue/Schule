package at.bbrz.calculator;

public class DoubleCalculator {
    private final double valueA;
    private final double valueB;

    public DoubleCalculator(double valueA, double valueB) {
        this.valueA = valueA;
        this.valueB = valueB;
    }

    public double sum() {
        return valueA + valueB;
    }

    public double substract() {
        return valueA - valueB;
    }

    public double multiply() {
        return valueA * valueB;
    }

    public double divide() {
        if (valueB == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return valueA / valueB;
    }
}
