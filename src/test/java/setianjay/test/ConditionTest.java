package setianjay.test;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;
import org.opentest4j.TestAbortedException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

/*
* @Tags is an annotation used for integrating some test class or test method to run together (not parallel). We mark the
* class or method with @Tags annotation and give the same name to integrate the test class or method. In this case, if
* we run the test based on the tags name like 'integration-test' ConditionTest class will run together with
* Condition2Test class because they have same name for Tags.
* */
@Tags(value = {@Tag(value = "integration-test")})
class ConditionTest {

    /*
     * Throw TestAbortedException manually can stop or abort the test process.
     *
     * */
    @Test
    void testSystemEnvironmentVariableWithThrowingTestAbortedException() {
        String profile = System.getenv("PROFILE");

        if (!"DEV".equals(profile)) {
            // stop or abort the test process because the PROFILE is not 'DEV'.
            throw new TestAbortedException("Only [DEV] PROFILE can run test!");
        }

        // test for DEV PROFILE
        assertEquals("DEV", profile);
    }

    /*
     * Instead of throwing TestAbortedException manually to stop or abort the testing process, you can use Assumption to
     * throw TestAbortedException automatically. With Assumption, if condition is not met, it will automatically throw
     * TestAbortedException, it will stop or abort test process.
     * */
    @Test
    void testSystemEnvironmentVariableWithAssumption() {
        String profile = System.getenv("PROFILE");
        // if assumeTrue return true the test process will continue, otherwise it will stop or abort
        assumeTrue("DEV".equals(profile));

        // test for DEV PROFILE
        assertEquals("DEV", profile);
    }

    /*
     * Other than Assumption, we can use an annotation to check specific condition. If the conditions are met, test will
     * run. Otherwise, test will stop or abort.
     * */
    @Test
    @EnabledIfEnvironmentVariables(
            value = {@EnabledIfEnvironmentVariable(named = "PROFILE", matches = "DEV")}
    )
    void testSystemEnvironmentVariableWithAnnotation(){
        // if Environment Variable is match, test will run. Otherwise, test will stop or abort.

        // test for DEV PROFILE
        String profile = System.getenv("PROFILE");
        assertEquals("DEV", profile);
    }

    @Test
    @EnabledIfSystemProperties(
            value = {
                    @EnabledIfSystemProperty(named = "java.vendor", matches = "Oracle Corporation"),
                    @EnabledIfSystemProperty(named = "user.country", matches = "ID")
            }
    )
    void testForSystemProperty() {
        // if System Property is match, test will run. Otherwise, test will stop or abort.

        // to check all system property
        System.getProperties().forEach((key, value) -> System.out.println("Key: " + key + ", value: " + value));
        assertEquals("Oracle Corporation", System.getProperty("java.vendor"));
        assertEquals("ID", System.getProperty("user.country"));

    }

    @Test
    @DisabledIfSystemProperties(
            value = {
                    @DisabledIfSystemProperty(named = "java.vendor", matches = "Oracle Corporation"),
                    @DisabledIfSystemProperty(named = "user.country", matches = "ID")
            }
    )
    void testForDisabledSystemProperty() {
        // if system property is match, test will stop or abort. Otherwise, test will run.

        // to check all system property to pass as argument on annotation @EnabledIfSystemProperty
        System.getProperties().forEach((key, value) -> System.out.println("Key: " + key + ", value: " + value));
        assertNotEquals("Sun Microsystems", System.getProperty("java.vendor"));
        assertNotEquals("IN", System.getProperty("user.country"));
    }


    @Test
    @EnabledOnOs(value = {OS.WINDOWS})
    void testForWindowsOS() {
        // if operating system is Windows, test will run. Otherwise, test will stop or abort.

        assertTrue(System.getProperty("os.name").contains("Windows"));
    }

    @Test
    @DisabledOnOs(value = {OS.WINDOWS})
    void testForDisabledWindowsOS() {
        // if operating system is not Windows, test will run. Otherwise, test will stop or abort.

        assertFalse(System.getProperty("os.name").contains("Windows"));
    }

    @Test
    @EnabledOnJre(value = {JRE.JAVA_16})
    void testForJRE16() {
        // if JRE is JRE 16, test will run. Otherwise, test will stop or abort.

        assertEquals(16, Integer.parseInt(System.getProperty("java.version")));
    }

    @Test
    @DisabledOnJre(value = {JRE.JAVA_16})
    void testForDisabledJRE16() {
        // if JRE is not JRE 16, test will run. Otherwise, test will stop or abort.

        assertNotEquals(17, Integer.parseInt(System.getProperty("java.version")));
    }

    @Test
    @EnabledForJreRange(min = JRE.JAVA_8, max = JRE.JAVA_16)
    void testForJRE8UntilJRE16() {
        // if JRE is JRE 8 until JRE 16, test will run. Otherwise, test will stop or abort.

        assertEquals(16, Integer.parseInt(System.getProperty("java.version")));
    }

    @Test
    @DisabledForJreRange(min = JRE.JAVA_8, max = JRE.JAVA_16)
    void testForDisabledJRE8UntilJRE16() {
        // if JRE is not JRE 8 until JRE 16, test will run. Otherwise, test will stop or abort.

        assertNotEquals(17, Integer.parseInt(System.getProperty("java.version")));
    }
}
