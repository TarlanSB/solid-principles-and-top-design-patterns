package singletonDesignPattern.abstractLoggerExample;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class SingletonFileLogger extends AbstractLogger {
    private static SingletonFileLogger instance;

    // Private constructor to prevent external instantiation
    private SingletonFileLogger(String fileName) throws IOException {
        writer = new PrintWriter(new FileWriter(fileName, true));
    }

    // Public method to get the singleton instance. It synchronizes on the class object
    // to ensure thread safety during instance creation.
    public static synchronized SingletonFileLogger getInstance(String fileName) {
        if (instance == null) {
            try {
                instance = new SingletonFileLogger(fileName);
            } catch (IOException e) {
                throw new RuntimeException("Failed to initialize logger", e);
            }
        }
        return instance;
    }
}
