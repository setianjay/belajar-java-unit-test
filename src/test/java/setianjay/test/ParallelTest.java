package setianjay.test;


import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
* @Execution is an annotation used to set execution mode to test class or method class. There are 2 values that can be
* used, namely ExecutionMode.SAME_THREAD or ExecutionMode.CONCURRENT. By default, the execution mode for the testing
* class or method is ExecutionMode.SAME_THREAD, That will make a test run in the same thread and also run sequentially,
* or in other words, the next test will only be run if the previous test has been completed.
*
* Whereas ExecutionMode.CONCURRENT That will make a test run in a different thread and also run in parallel or together.
* or in other words, the next test will still be run even though the previous test has not been completed.
* */
@Execution(value = ExecutionMode.CONCURRENT)
class ParallelTest {

    /*
    * @Timeout is an annotation used to set timeout for the testing method. If the given time limit has expired, then
    * the test method will throw an exception, namely TimeoutException.
    * */
    @ParameterizedTest
    @Timeout(value = 6000, unit = TimeUnit.MILLISECONDS)
    @ValueSource(longs = {5000, 6000, 4000})
    void waitingParallelTest(long waitingProcess) {
        long startTime = System.currentTimeMillis();

        // uses the Awaitility library to replace the use of Thread.sleep()
        await().pollDelay(waitingProcess, TimeUnit.MILLISECONDS).until(() -> true);

        long endTime = System.currentTimeMillis();
        long result = endTime - startTime;

        System.out.println("How long we waiting: " + result);

        assertTrue(result < 6000);
    }
}
