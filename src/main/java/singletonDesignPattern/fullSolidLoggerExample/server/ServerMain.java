package singletonDesignPattern.fullSolidLoggerExample.server;

import singletonDesignPattern.fullSolidLoggerExample.impl.FileLogger;
import singletonDesignPattern.fullSolidLoggerExample.singleton.SingletonLogger;

public class ServerMain {
    public static void main(String[] args) {
        // Specify the port on which the server should listen for incoming log messages
        int port = 12345;
        // Specify the file to which logs should be written
        String logFilePath = "server_logs.log";

        try {
            // Initialize the FileLogger (or another Logger implementation as needed)
            FileLogger fileLogger = new FileLogger(logFilePath);
            // Wrap the FileLogger with the SingletonLogger to ensure a single logging instance
            SingletonLogger singletonLogger = SingletonLogger.getInstance(fileLogger);

            // Create and start the logging server with the singletonLogger
            LoggingServer server = new LoggingServer(port, singletonLogger);
            System.out.println("Logging Server started on port " + port + ". Listening for log messages...");

            // Start the server; this method should block, listening for incoming connections
            server.startServer();

            // After the server is stopped (e.g., via a shutdown command), clean up resources
            System.out.println("Logging Server has been stopped.");
        } catch (Exception e) {
            System.err.println("An error occurred while starting the logging server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

