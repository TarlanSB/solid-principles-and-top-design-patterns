package com.tsb.singletonDesignPattern.fullSolidLoggerExample.impl;

// Necessary imports for networking and IO operations
import com.tsb.singletonDesignPattern.fullSolidLoggerExample.core.Logger;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;

// The NetworkLogger class implements the Logger interface, 
// indicating it must provide an implementation of the log method.
public class NetworkLogger implements Logger {
    // hostname and port: The network address and port of the logging server.
    private String hostname;
    private int port;

    // Constructor: Initializes a new instance of NetworkLogger with a specific hostname and port.
    public NetworkLogger(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    // Implementation of the log method from the Logger interface. 
    // This method is responsible for sending the log message to the logging server.
    @Override
    public void log(String message) {
        // Try-with-resources statement to ensure the socket and writer are closed 
        // automatically after the operation.
        try (Socket socket = new Socket(this.hostname, this.port);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            writer.write(message); // Writes the message to the output stream of the socket.
            writer.newLine(); // Adds a newline character to ensure the message is on its own line.
            writer.flush(); // Flushes the writer to ensure the message is sent immediately over the network.

        // Catch block to handle any exceptions that might occur during the logging process.
        } catch (Exception e) {
            // Prints an error message to standard error stream if the logging fails.
            System.err.println("Failed to send log message: " + e.getMessage());
            // Optionally, you could add a fallback mechanism here, such as logging to a 
            // local file or attempting to resend the message.
        }
    }
}




