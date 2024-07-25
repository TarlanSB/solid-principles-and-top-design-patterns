// Define the package this class belongs to.
package singletonDesignPattern.fullSolidLoggerExample.server;

// Import necessary classes from other packages.

import singletonDesignPattern.fullSolidLoggerExample.core.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

// Declaration of the LoggingServer class.
public class LoggingServer {
    // Declare variables for the server: the port number, a Logger instance, and a flag to check if the server is running.
    private final int port;
    private final Logger logger;
    private boolean isRunning;

    // Constructor for the LoggingServer class. It initializes the server with a specific port and a logger instance.
    public LoggingServer(int port, Logger logger) {
        this.port = port; // Assign the port number for the server.
        this.logger = logger; // Assign the logger instance for logging messages.
        this.isRunning = true; // Initially set the server's running status to true.
    }

    // Method to start the server.
    public void startServer() {
        // Try to open a server socket on the specified port.
        try (ServerSocket serverSocket = new ServerSocket(this.port)) {
            // Print a message to the console indicating the server is running.
            System.out.println("Logging Server is running on port " + this.port);

            while (isRunning) { // While the server is set to be running,
                // Wait and accept incoming client connections.
                try (Socket clientSocket = serverSocket.accept();
                     // Create a reader to read messages from the client.
                     BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                    String messageLine;
                    // Read messages line by line until there are no more messages or the server stops.
                    while ((messageLine = reader.readLine()) != null && isRunning) {
                        logger.log(messageLine); // Log each line of message using the provided logger.
                    }
                } catch (Exception e) { // Catch any exceptions related to client connections.
                    // Print an error message if a client connection fails.
                    System.err.println("An error occurred with a client connection: " + e.getMessage());
                }
            }
        } catch (Exception e) { // Catch any exceptions when attempting to start the server.
            // Print an error message if the server fails to start.
            System.err.println("Logging Server failed to start: " + e.getMessage());
        }
    }

    // Method to stop the server.
    public void stopServer() {
        this.isRunning = false; // Set the running status to false, which will stop the server loop.
        // Print a message indicating the server is stopping.
        System.out.println("Logging Server is stopping...");
    }
}
