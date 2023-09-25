package setianjay.test;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/*
* @DisplayNameGeneration is an annotation to giving display name for testing class and method automatically. You can create
* a concrete class that implements DisplayNameGenerator and override 3 its method, or you can use an existing concrete
* class such as:
*
* - DisplayNameGenerator.IndicativeSentences
* - DisplayNameGenerator.Standard
* - DisplayNameGenerator.Simple
* - DisplayNameGenerator.ReplaceUnderscores
* */
@DisplayNameGeneration(value = DisplayNameGenerator.IndicativeSentences.class)
class CalculatorTest {
    private static Calculator calculator;

    @BeforeAll
    public static void setup() {
        calculator = new Calculator();
    }

    @AfterAll
    public static void shutdown(){
        calculator = null;
    }

    /**
     * Test {@link Calculator} add function with <b>positive(+)</b> number arguments.
     * */
    @Test
    void testAddWithPositiveNumber(){
        int expected = 20;
        int actual = calculator.add(10, 10);
        assertEquals(expected, actual);
    }

    /**
     * Test {@link Calculator} add function with <b>negative(-)</b> number arguments.
     * */
    @Test
    void testAddWithNegativeNumber(){
        int expected = -20;
        int actual = calculator.add(-10, -10);
        assertEquals(expected, actual);
    }

    /**
     * Test {@link Calculator} add function with <b>positive(+) and negative(-) or vice versa</b> number arguments.
     * */
    @Test
    void testAddWithPositiveAndNegativeNumberOrViceVersa(){
        int expected = 0;
        int actual = calculator.add(10, -10);
        int actual2 = calculator.add(-10, 10);
        assertEquals(expected, actual);
        assertEquals(expected, actual2);
    }

    /**
     * Test {@link Calculator} divide function with <b>positive(+)</b> number arguments.
     * */
    @Test
    void testDivideWithPositiveNumber(){
        double expected = 3.5;
        double actual = calculator.divide(7, 2);
        assertEquals(expected, actual);
    }

    /**
     * Test {@link Calculator} divide function with <b>negative(-)</b> number arguments.
     * */
    @Test
    void testDivideWithNegativeNumber(){
        double expected = 10;
        double actual = calculator.divide(-100, -10);
        assertEquals(expected, actual);
    }

    /**
     * Test {@link Calculator} divide function with <b>positive(+) and negative(-) or vice versa</b> number arguments.
     * */
    @Test
    void testDivideWithPositiveAndNegativeNumberOrViceVersa(){
        double expected = -10;
        double actual = calculator.divide(100, -10);
        double actual2 = calculator.divide(-100, 10);
        assertEquals(expected, actual);
        assertEquals(expected, actual2);
    }

    /**
     * Test {@link Calculator} divide function with zero.
     * */
    @Test
    void testDivideWithZeroNumber(){
        assertThrows(IllegalArgumentException.class, () -> calculator.divide(10, 0));
    }

    /*
    * @Disabled is an annotation to ignore the test from the testing process, because the test has not finished
    * implementation or something else.
    * */
    @Test
    @Disabled(value = "Test has not completed, because I am stuck:v")
    void testFunctionCalculatorComingSoon(){

    }
}
