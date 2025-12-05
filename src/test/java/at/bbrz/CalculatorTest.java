package at.bbrz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator(5,7);
    }

    @Test
    void calculateSumOfTwoNumbers_shouldReturnCorrectResult() {
        assertEquals(12, calculator.sum());
    }

    @Test
    void calculateSubstractOfTwoNumbers_shouldReturnCorrectResult() {
        assertEquals(-2, calculator.substract());
    }

    @Test
    void calculateMultiplyOfTwoNumbers_shouldReturnCorrectResult() {
        assertEquals(35, calculator.multiply());
    }

    @Test
    void calculateSumOfTwoNegativeNumbers_shouldReturnCorrectResult() {
        calculator = new Calculator(-5,-7);
        assertEquals(-12, calculator.sum());
    }

    @Test
    void calculateSubstractOfTwoNegativeNumbers_shouldReturnCorrectResult() {
        calculator = new Calculator(-5,-7);
        assertEquals(2, calculator.substract());
    }

    @Test
    void calculateMultiplyOfTwoNegativeNumbers_shouldReturnCorrectResult() {
        calculator = new Calculator(-5,-7);
        assertEquals(35, calculator.multiply());
    }
}