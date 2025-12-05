package at.bbrz;

public class Calculator {
    private int valueA;
    private int valueB;

    public Calculator(int valueA, int valueB) {
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
        return valueA / valueB;
    }
}
