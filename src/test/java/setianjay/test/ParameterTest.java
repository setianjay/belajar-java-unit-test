package setianjay.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import setianjay.test.di.RandomParameterResolver;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName(value = "A Parameter")
class ParameterTest {

    /*
     * @DisplayName is an annotation used to give the display name of a test according to the name we want.
     *
     * -----------------------------------------------------------------------------------------------------------------
     * @Extensions is an annotation used to register our own ParameterResolver class for a class that can be injected
     * automatically into parameters. In this test, the Random class will be automatically provided by JUnit because we
     * have created the ParameterResolver class namely RandomParameterResolver.
     *
     * -----------------------------------------------------------------------------------------------------------------
     * @Nested is an annotation used to notify that within a test class there is another test class.
     * */
    @DisplayName(value = "when auto injection")
    @Extensions(value = {@ExtendWith(value = RandomParameterResolver.class)})
    @Nested
    class DependencyInjectionTest{

        @Test
        @DisplayName(value = "TestInfo class")
        void testDependencyInjectionBuildInJUnit(TestInfo testInfo){
            System.out.println(testInfo.getDisplayName());
            System.out.println(testInfo.getTestClass().orElse(null));
            System.out.println(testInfo.getTestMethod().orElse(null));
            assertEquals("TestInfo class", testInfo.getDisplayName());
        }

        @Test
        @DisplayName(value = "Random class")
        void testDependencyInjectionUserBuildIn(Random random){
            int a = random.nextInt();
            int b = random.nextInt();
            int result = a + b;
            assertEquals(a + b, result);
        }
    }

    @DisplayName(value = "when use source parameter")
    @Nested
    class SourceParameterTest{
        private static int counter = 0;

        /*
        * @ParameterizedTest is an annotation used to notify that a test will be run with a parameter. This parameter
        * can be obtained from a source that is provided, here are some sources that you can use:
        *
        * - @ValueSource    = source from type data like Number, String, bool, Class.
        * - @MethodSource   = source from method
        * - @CSVSource      = source from Row of CSV
        * - @CsvFileSource  = source from CSV file
        * - @EnumSource     = source from Enum Class
        * - @NullSource     = source from Null
        *
        * Note:
        * - with @ParameterizedTest, the test will be run as many times as the number of parameters (like forEach).
        * */
        @ParameterizedTest(name = "int, {0}")
        @DisplayName(value = "with value source")
        @ValueSource(ints = {1, 2, 3})
        void testWithSourceParameter(int value){
            counter++;
            assertEquals(counter, value);
        }
    }


}
