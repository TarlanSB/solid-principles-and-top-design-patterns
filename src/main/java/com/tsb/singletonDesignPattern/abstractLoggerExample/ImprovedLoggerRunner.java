package com.tsb.singletonDesignPattern.abstractLoggerExample;

public class ImprovedLoggerRunner {
    public static void main(String[] args) {
        // Using the SingletonLogger
        SingletonFileLogger logger = SingletonFileLogger.getInstance("app.log");
        logger.log("Improved Singleton logger message with decoration.");

        // Reusing the same instance
        SingletonFileLogger anotherLoggerInstance = SingletonFileLogger.getInstance("ignoredFileName.log");
        anotherLoggerInstance.log("Another message, same improved singleton logger instance.");
    }
}
