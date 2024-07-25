package singletonDesignPattern.fullSolidLoggerExample.demo;

import singletonDesignPattern.fullSolidLoggerExample.core.Logger;
import singletonDesignPattern.fullSolidLoggerExample.decorators.HostnameDecorator;
import singletonDesignPattern.fullSolidLoggerExample.decorators.TimestampDecorator;
import singletonDesignPattern.fullSolidLoggerExample.impl.NetworkLogger;
import singletonDesignPattern.fullSolidLoggerExample.singleton.SingletonLogger;

public class NetworkLoggerDemo {
    public static void main(String[] args) {
        // Assume the server is listening on localhost port 12345
        String hostname = "localhost";
        int port = 12345;

        // Initialize the NetworkLogger (or retrieve it if it's already initialized)
        NetworkLogger networkLogger = new NetworkLogger(hostname, port);
        
        // Optionally, if using SingletonLogger to ensure a single instance
        SingletonLogger singletonNetworkLogger = SingletonLogger.getInstance(networkLogger);
        Logger decoratedNetworkLogger = new TimestampDecorator(new HostnameDecorator(singletonNetworkLogger));
        
        // Send 5 messages, each 0.5 seconds apart
        int messageCount = 5;
        for (int i = 1; i <= messageCount; i++) {
            // Construct the message with sequential numbering
            String message = String.format("Message %03d from NetworkLogger", i);
            decoratedNetworkLogger.log(message);

            // Wait for 0.5 seconds before sending the next message
            try {
                Thread.sleep(500); // 500 milliseconds = 0.5 seconds
            } catch (InterruptedException e) {
                System.err.println("Interrupted while waiting to send the next message");
                Thread.currentThread().interrupt(); // Set the interrupt flag
                break; // Exit the loop if the thread is interrupted
            }
        }
    }
}
