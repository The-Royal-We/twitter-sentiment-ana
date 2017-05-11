import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

public class LoggerSmoke {

    @Test
    public void givenLoggerWithDefaultConfig_whenLogToConsole_thenOK() {
        Logger logger = LogManager.getLogger(getClass());
        Exception e = new RuntimeException("This is only a test");
        logger.info("This will be shown on the info level." +
                "This will be hidden");
        logger.error("This will be shown on the error level" , e);
    }
}
