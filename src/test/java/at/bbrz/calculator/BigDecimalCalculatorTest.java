package at.bbrz.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BigDecimalCalculatorTest {
    BigDecimalCalculator bigDecimalCalculator;

    @BeforeEach
    void setUp() {
        bigDecimalCalculator = new BigDecimalCalculator(BigDecimal.valueOf(5),BigDecimal.valueOf(7));
    }

    @Test
    void calculateSumOfTwoNumbers_shouldReturnCorrectResult() {
        assertEquals(BigDecimal.valueOf(12), bigDecimalCalculator.sum());
    }

    @Test
    void calculateSubstractOfTwoNumbers_shouldReturnCorrectResult() {
        assertEquals(BigDecimal.valueOf(-2), bigDecimalCalculator.substract());
    }

    @Test
    void calculateMultiplyOfTwoNumbers_shouldReturnCorrectResult() {
        assertEquals(BigDecimal.valueOf(35), bigDecimalCalculator.multiply());
    }

    @Test
    void calculateSumOfTwoNegativeNumbers_shouldReturnCorrectResult() {
        bigDecimalCalculator = new BigDecimalCalculator(BigDecimal.valueOf(-5),BigDecimal.valueOf(-7));
        assertEquals(BigDecimal.valueOf(-12), bigDecimalCalculator.sum());
    }

    @Test
    void calculateSubstractOfTwoNegativeNumbers_shouldReturnCorrectResult() {
        bigDecimalCalculator = new BigDecimalCalculator(BigDecimal.valueOf(-5),BigDecimal.valueOf(-7));
        assertEquals(BigDecimal.valueOf(2), bigDecimalCalculator.substract());
    }

    @Test
    void calculateMultiplyOfTwoNegativeNumbers_shouldReturnCorrectResult() {
        bigDecimalCalculator = new BigDecimalCalculator(BigDecimal.valueOf(-5),BigDecimal.valueOf(-7));
        assertEquals(BigDecimal.valueOf(35), bigDecimalCalculator.multiply());
    }

    @Test
    void calculateWithMaxValues_shouldReturnCorrectResult() {
        bigDecimalCalculator = new BigDecimalCalculator(BigDecimal.valueOf(Double.MAX_VALUE),BigDecimal.valueOf(1000));
        assertEquals(BigDecimal.valueOf(Double.MAX_VALUE).add(BigDecimal.valueOf(1000)), bigDecimalCalculator.sum());
    }

    @Test
    void divisionByZero_shouldThrowException() {
        bigDecimalCalculator = new BigDecimalCalculator(BigDecimal.valueOf(1),BigDecimal.valueOf(0));
        ArithmeticException arithmeticException = assertThrows(ArithmeticException.class, bigDecimalCalculator::divide);
        assertEquals("Division by zero", arithmeticException.getMessage());
    }

    @Test
    void calculateDivisionOfTwoNumbers_shouldReturnCorrectResult() {
        bigDecimalCalculator = new BigDecimalCalculator(BigDecimal.valueOf(10),BigDecimal.valueOf(2));
        assertEquals(BigDecimal.valueOf(5), bigDecimalCalculator.divide());
    }

    @Test
    void calculateDivisionOfTwoNumbers_shouldReturnResultWithoutDicimalPlaces() {
        bigDecimalCalculator = new BigDecimalCalculator(BigDecimal.valueOf(10),BigDecimal.valueOf(3));
        BigDecimal result = bigDecimalCalculator.divide();
        BigDecimal expected = BigDecimal.valueOf(3.33);
        assertEquals(0, expected.compareTo(result));
    }

    @Test
    void calculateDivisionOfTwoNumbers_shouldReturnResultWithoutRounding() {
        bigDecimalCalculator = new BigDecimalCalculator(BigDecimal.valueOf(20),BigDecimal.valueOf(3));
        assertEquals(BigDecimal.valueOf(6.67), bigDecimalCalculator.divide());
    }

    @Test
    void calculateSumOfTwoSmallNumbers_shouldReturnResultWithRoundingIssue() {
        bigDecimalCalculator = new BigDecimalCalculator(BigDecimal.valueOf(0.1),BigDecimal.valueOf(0.2));
        assertEquals(BigDecimal.valueOf(0.3), bigDecimalCalculator.sum());
    }

}