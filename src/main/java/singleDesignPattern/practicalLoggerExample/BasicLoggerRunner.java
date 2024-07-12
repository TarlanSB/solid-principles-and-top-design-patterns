package singleDesignPattern.practicalLoggerExample;

import java.io.IOException;

public class BasicLoggerRunner {
    public static void main(String[] args) {
        try {
            // Initialize the logger with the file name "app.log"
            BasicLogger logger = new BasicLogger("app.log");

            // Log a few messages
            logger.log("Starting the application. 2");
            logger.log("Performing some operations... 2");
            logger.log("An example warning message. 2");
            logger.log("Shutting down the application. 2");

            System.out.println("Logging completed. Check the app.log file.");
        } catch (IOException e) {
            System.err.println("An error occurred while initializing the logger: " + e.getMessage());
            e.printStackTrace();
        }

        // Using the SingletonLogger
        SingletonLogger singletonLogger = SingletonLogger.getInstance("app.log");
        singletonLogger.log("This is a singleton logger message.");

        // Another call to SingletonLogger, no need to specify the file name
        SingletonLogger anotherInstance = SingletonLogger.getInstance("ignoredFileName");
        anotherInstance.log("Another message via singleton logger.");
    }
}
