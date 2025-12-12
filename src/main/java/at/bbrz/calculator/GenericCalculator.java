package at.bbrz.calculator;

// https://www.baeldung.com/java-generics
// Not a good idea to use generics in this case
public class GenericCalculator<T extends Number> {
    private T valueA;
    private T valueB;

    public GenericCalculator(T valueA, T valueB) {
        this.valueA = valueA;
        this.valueB = valueB;
    }

    public Double sum() {
        if (valueA instanceof Integer && valueB instanceof Integer) {
            return (double) ((Integer) valueA.intValue() + valueB.intValue());
        }
        return valueA.doubleValue() + valueB.doubleValue();
    }

    public Double substract() {
        return valueA.doubleValue() - valueB.doubleValue();
    }

    public Double multiply() {
        return valueA.doubleValue() * valueB.doubleValue();
    }

    public Double divide() {
        if (valueB.doubleValue() == 0) {
            throw new ArithmeticException("Division by zero");
        }
        if (valueA instanceof Integer && valueB instanceof Integer) {
            return (double) ((Integer) valueA.intValue() / valueB.intValue());
        }
        return valueA.doubleValue() / valueB.doubleValue();
    }
}
