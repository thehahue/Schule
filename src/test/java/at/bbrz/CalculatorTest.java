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

    @Test
    void calculateWithMaxValues_shouldReturnCorrectResult() {
        calculator = new Calculator(Integer.MAX_VALUE,1);
        assertEquals(Integer.MIN_VALUE, calculator.sum());
    }

    @Test
    void divisionByZero_shouldThrowException() {
        calculator = new Calculator(1,0);
        ArithmeticException arithmeticException = assertThrows(ArithmeticException.class, calculator::divide);
        assertEquals("Division by zero", arithmeticException.getMessage());
    }

    @Test
    void calculateDivisionOfTwoNumbers_shouldReturnCorrectResult() {
        calculator = new Calculator(10,2);
        assertEquals(5, calculator.divide());
    }

    @Test
    void calculateDivisionOfTwoNumbers_shouldReturnResultWithoutDicimalPlaces() {
        calculator = new Calculator(10,3);
        assertEquals(3, calculator.divide());
    }

    @Test
    void calculateDivisionOfTwoNumbers_shouldReturnResultWithoutRounding() {
        calculator = new Calculator(20,3); //6.6666666666
        assertEquals(6, calculator.divide());
    }
}