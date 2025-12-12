package at.bbrz.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntCalculatorTest {
    GenericCalculator<Integer> intCalculator;

    @BeforeEach
    void setUp() {
        intCalculator = new GenericCalculator(5,7);
    }

    @Test
    void calculateSumOfTwoNumbers_shouldReturnCorrectResult() {
        assertEquals(12, intCalculator.sum());
    }

    @Test
    void calculateSubstractOfTwoNumbers_shouldReturnCorrectResult() {
        assertEquals(-2, intCalculator.substract());
    }

    @Test
    void calculateMultiplyOfTwoNumbers_shouldReturnCorrectResult() {
        assertEquals(35, intCalculator.multiply());
    }

    @Test
    void calculateSumOfTwoNegativeNumbers_shouldReturnCorrectResult() {
        intCalculator = new GenericCalculator(-5,-7);
        assertEquals(-12, intCalculator.sum());
    }

    @Test
    void calculateSubstractOfTwoNegativeNumbers_shouldReturnCorrectResult() {
        intCalculator = new GenericCalculator(-5,-7);
        assertEquals(2, intCalculator.substract());
    }

    @Test
    void calculateMultiplyOfTwoNegativeNumbers_shouldReturnCorrectResult() {
        intCalculator = new GenericCalculator(-5,-7);
        assertEquals(35, intCalculator.multiply());
    }

    @Test
    void calculateWithMaxValues_shouldReturnCorrectResult() {
        intCalculator = new GenericCalculator(Integer.MAX_VALUE,1);
        assertEquals(Integer.MIN_VALUE, intCalculator.sum());
    }

    @Test
    void divisionByZero_shouldThrowException() {
        intCalculator = new GenericCalculator(1,0);
        ArithmeticException arithmeticException = assertThrows(ArithmeticException.class, intCalculator::divide);
        assertEquals("Division by zero", arithmeticException.getMessage());
    }

    @Test
    void calculateDivisionOfTwoNumbers_shouldReturnCorrectResult() {
        intCalculator = new GenericCalculator(10,2);
        assertEquals(5, intCalculator.divide());
    }

    @Test
    void calculateDivisionOfTwoNumbers_shouldReturnResultWithoutDicimalPlaces() {
        intCalculator = new GenericCalculator(10,3);
        assertEquals(3, intCalculator.divide());
    }

    @Test
    void calculateDivisionOfTwoNumbers_shouldReturnResultWithoutRounding() {
        intCalculator = new GenericCalculator(20,3); //6.6666666666
        assertEquals(6, intCalculator.divide());
    }
}