package com.tsb.singletonDesignPattern.practicalLoggerExample;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.time.LocalDateTime;

public class BasicLogger {
    private PrintWriter writer;

    public BasicLogger(String fileName) throws IOException {
        // Open the given file in append mode.
        writer = new PrintWriter(new FileWriter(fileName, true));
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
