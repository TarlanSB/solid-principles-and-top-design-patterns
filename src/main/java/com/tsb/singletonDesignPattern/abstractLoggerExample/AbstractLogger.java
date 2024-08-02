package com.tsb.singletonDesignPattern.abstractLoggerExample;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.time.LocalDateTime;

abstract class AbstractLogger {
    protected PrintWriter writer;

    // Method to log a message. This is final to ensure the logging process consistency,
    // but it delegates to `decorate`, which subclasses can customize.
    public final void log(String message) {
        String decoratedMessage = decorate(message);
        writer.println(decoratedMessage);
        writer.flush();
    }


    // Method to decorate a message. Subclasses should override this to provide custom decoration.
    protected String decorate(String message) {
        try {
            String computerName = InetAddress.getLocalHost().getHostName();
            LocalDateTime now = LocalDateTime.now();
            return String.format("[%s] [%s] %s", computerName, now, message);
        } catch (IOException e) {
            LocalDateTime now = LocalDateTime.now();
            return String.format("[Unknown Host] [%s] %s", now, message);
        }
    }
}
