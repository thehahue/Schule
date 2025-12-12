package at.bbrz.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoubleCalculatorTest {
    GenericCalculator<Double> doubleCalculator;

    @BeforeEach
    void setUp() {
        doubleCalculator = new GenericCalculator(5,7);
    }

    @Test
    void calculateSumOfTwoNumbers_shouldReturnCorrectResult() {
        assertEquals(12, doubleCalculator.sum());
    }

    @Test
    void calculateSubstractOfTwoNumbers_shouldReturnCorrectResult() {
        assertEquals(-2, doubleCalculator.substract());
    }

    @Test
    void calculateMultiplyOfTwoNumbers_shouldReturnCorrectResult() {
        assertEquals(35, doubleCalculator.multiply());
    }

    @Test
    void calculateSumOfTwoNegativeNumbers_shouldReturnCorrectResult() {
        doubleCalculator = new GenericCalculator(-5,-7);
        assertEquals(-12, doubleCalculator.sum());
    }

    @Test
    void calculateSubstractOfTwoNegativeNumbers_shouldReturnCorrectResult() {
        doubleCalculator = new GenericCalculator(-5,-7);
        assertEquals(2, doubleCalculator.substract());
    }

    @Test
    void calculateMultiplyOfTwoNegativeNumbers_shouldReturnCorrectResult() {
        doubleCalculator = new GenericCalculator(-5,-7);
        assertEquals(35, doubleCalculator.multiply());
    }

    @Test
    void calculateWithMaxValues_shouldReturnCorrectResult() {
        doubleCalculator = new GenericCalculator(Double.MAX_VALUE,1000);
        assertEquals(Double.MAX_VALUE, doubleCalculator.sum());
    }

    @Test
    void divisionByZero_shouldThrowException() {
        doubleCalculator = new GenericCalculator(1,0);
        ArithmeticException arithmeticException = assertThrows(ArithmeticException.class, doubleCalculator::divide);
        assertEquals("Division by zero", arithmeticException.getMessage());
    }

    @Test
    void calculateDivisionOfTwoNumbers_shouldReturnCorrectResult() {
        doubleCalculator = new GenericCalculator(10,2);
        assertEquals(5, doubleCalculator.divide());
    }

    @Test
    void calculateDivisionOfTwoNumbers_shouldReturnResultWithoutDicimalPlaces() {
        doubleCalculator = new GenericCalculator(10,3);
        assertEquals(3.3333333333333335, doubleCalculator.divide());
    }

    @Test
    void calculateDivisionOfTwoNumbers_shouldReturnResultWithoutRounding() {
        doubleCalculator = new GenericCalculator(20,3);
        assertEquals((20d/3), doubleCalculator.divide());
    }

    @Test
    void calculateSumOfTwoSmallNumbers_shouldReturnResultWithRoundingIssue() {
        doubleCalculator = new GenericCalculator(0.1,0.2);
        assertEquals(0.30000000000000004, doubleCalculator.sum());
    }

}