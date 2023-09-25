package setianjay.test;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tags(value = {@Tag(value = "integration-test")})
class Condition2Test {

    @Test
    @DisplayName(value = "testIntegrationTest")
    void testIntegrationTest(TestInfo testInfo){
        assertEquals("testIntegrationTest", testInfo.getDisplayName());
    }
}
