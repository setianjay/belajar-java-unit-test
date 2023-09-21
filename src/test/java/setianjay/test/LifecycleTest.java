package setianjay.test;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * @TestInstance is an annotation used to set the lifecycle of a test class. There are 2 values that can be used, namely
 * Lifecycle.PER_METHOD or Lifecycle.PER_CLASS. By default, the lifecycle for the testing class is Lifecycle.PER_METHOD,
 * the lifecycle will create a different test class object for every 1 test method. Whereas Lifecycle.PER_CLASS will
 * only create one test class object for the entire test method or other words use the same object fpr the entire test
 * method.
 * Example:
 *
 * - Lifecycle.PER_METHOD (default)
 * LifecycleTest lfTest1 = new LifecycleTest();
 * lfTest1.test1();
 *
 * LifecycleTest lfTest2 = new LifecycleTest();
 * lfTest2.test2();
 *
 * LifecycleTest lfTest3 = new LifecycleTest();
 * lfTest3.test3();
 *
 * - Lifecycle.PER_CLASS
 * LifecycleTest lfTest = new LifecycleTest();
 * lfTest.test1();
 * lfTest.test2();
 * lfTest.test3();
 *
 * ---------------------------------------------------------------------------------------------------------------------
 * @TestMethodOrder is an annotation used to provide the order in which the test methods are performed. You can create
 * a concrete class that implements MethodOrderer and override 2 its method, or you can use an existing concrete
 * class such as:
 *
 * - MethodOrderer.OrderAnnotation
 * - MethodOrderer.DisplayName
 * - MethodOrderer.MethodName
 * - MethodOrderer.Random (default)
 *
 * Note:
 * - if use MethodOrdered.OrderAnnotation, we must add @Order annotation to test method and give the order number.
 * */

//@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class LifecycleTest {
    List<Integer> numbers;

    /*
    * if @TestInstance is Lifecycle.PER_METHOD the output for counter is 1.
    * if @TestInstance is Lifecycle.PER_CLASS the output for counter is 2.
    * */
    private int counter = 0;

    /*
    *
    * @BeforeAll is an annotation to process tasks before all test run.
    * @AfterAll is an annotation to process tasks after all test run.
    * @BeforeEach is an annotation to process tasks before each test run.
    * @AfterEach is an annotation to process tasks after each test run.
    *
    * Note:
    * - if @TestInstance is Lifecycle.PER_METHOD, @BeforeAll and @AfterAll annotation must be static method.
    * - if @TestInstance is Lifecycle.PER_CLASS, @BeforeAll and @AfterAll annotation must not be a static method.
    *
    * */
    @BeforeAll
    public void setupAll(){
        numbers = new ArrayList<>();
    }

    @AfterAll
    public void shutdownAll(){
        numbers = null;
    }

    @BeforeEach
    public void setupEach(){
        System.out.println("Run before each test process");
    }

    @AfterEach
    public void shutdownEach(){
        System.out.println("Run after each test process finish");
    }

    @Test
    @Order(value = 1)
    void test1(){
        counter++;
        System.out.println(counter);
        assertEquals(1, counter);
    }

    @Test
    @Order(value = 2)
    void test2(){
        counter++;
        System.out.println(counter);
        // assertEquals(1, counter); // if @TestInstance is Lifecycle.PER_METHOD, the output is 1.
        assertEquals(2, counter);
    }
}
