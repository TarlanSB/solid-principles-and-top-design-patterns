package singleDesignPattern.practicalLoggerExample;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.time.LocalDateTime;

public class SingletonLogger {
    private static SingletonLogger instance;
    private PrintWriter writer;

    private SingletonLogger(String fileName) throws IOException {
        // Private constructor with file initialization
        writer = new PrintWriter(new FileWriter(fileName, true));
    }

    public static synchronized SingletonLogger getInstance(String fileName) {
        if (instance == null) {
            try {
                instance = new SingletonLogger(fileName);
            } catch (IOException e) {
                throw new RuntimeException("Failed to initialize logger", e);
            }
        }
        return instance;
    }

    public void log(String message) {
        // Decorate and write the message
        String decoratedMessage = decorate(message);
        writer.println(decoratedMessage);
        writer.flush(); // Ensure the message is written immediately
    }

    private String decorate(String message) {
        try {
            // Decorate the message with the computer name, current time, and the original message
            String computerName = InetAddress.getLocalHost().getHostName();
            LocalDateTime now = LocalDateTime.now();
            return String.format("[%s] [%s] %s", computerName, now, message);
        } catch (IOException e) {
            // Fallback if hostname can't be resolved
            LocalDateTime now = LocalDateTime.now();
            return String.format("[Unknown Host] [%s] %s", now, message);
        }
    }
}
