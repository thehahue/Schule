package at.bbrz.calculator;

public class IntCalculator {
    private final int valueA;
    private final int valueB;

    public IntCalculator(int valueA, int valueB) {
        this.valueA = valueA;
        this.valueB = valueB;
    }

    public int sum() {
        return valueA + valueB;
    }

    public int substract() {
        return valueA - valueB;
    }

    public int multiply() {
        return valueA * valueB;
    }

    public int divide() {
        if (valueB == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return valueA / valueB;
    }
}
