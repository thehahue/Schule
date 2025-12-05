package at.bbrz.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoubleCalculatorTest {
    DoubleCalculator doubleCalculator;

    @BeforeEach
    void setUp() {
        doubleCalculator = new DoubleCalculator(5,7);
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
        doubleCalculator = new DoubleCalculator(-5,-7);
        assertEquals(-12, doubleCalculator.sum());
    }

    @Test
    void calculateSubstractOfTwoNegativeNumbers_shouldReturnCorrectResult() {
        doubleCalculator = new DoubleCalculator(-5,-7);
        assertEquals(2, doubleCalculator.substract());
    }

    @Test
    void calculateMultiplyOfTwoNegativeNumbers_shouldReturnCorrectResult() {
        doubleCalculator = new DoubleCalculator(-5,-7);
        assertEquals(35, doubleCalculator.multiply());
    }

    @Test
    void calculateWithMaxValues_shouldReturnCorrectResult() {
        doubleCalculator = new DoubleCalculator(Double.MAX_VALUE,1000);
        assertEquals(Double.MAX_VALUE, doubleCalculator.sum());
    }

    @Test
    void divisionByZero_shouldThrowException() {
        doubleCalculator = new DoubleCalculator(1,0);
        ArithmeticException arithmeticException = assertThrows(ArithmeticException.class, doubleCalculator::divide);
        assertEquals("Division by zero", arithmeticException.getMessage());
    }

    @Test
    void calculateDivisionOfTwoNumbers_shouldReturnCorrectResult() {
        doubleCalculator = new DoubleCalculator(10,2);
        assertEquals(5, doubleCalculator.divide());
    }

    @Test
    void calculateDivisionOfTwoNumbers_shouldReturnResultWithoutDicimalPlaces() {
        doubleCalculator = new DoubleCalculator(10,3);
        assertEquals(3.3333333333333335, doubleCalculator.divide());
    }

    @Test
    void calculateDivisionOfTwoNumbers_shouldReturnResultWithoutRounding() {
        doubleCalculator = new DoubleCalculator(20,3);
        assertEquals((20d/3), doubleCalculator.divide());
    }

}