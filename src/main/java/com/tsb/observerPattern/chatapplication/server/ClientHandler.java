package com.tsb.observerPattern.chatapplication.server;
import java.io.*;
import java.net.*;


/**
 * Socket and Server References: 
 * Each ClientHandler holds a reference to its Socket and the Server. 
 * The Socket is used for communicating with the connected client, and the 
 * Server reference is used for interacting with server-wide operations like 
 * broadcasting messages.
 * 
 * Run Method: 
 * This method is where the main functionality of the ClientHandler lies. 
 * It reads messages from its client and uses the server's method to broadcast 
 * these messages to all connected clients. It continues to do this until it 
 * encounters an end-of-stream condition (i.e., the client disconnects).
 * 
 * Error and Connection Handling: 
 * Proper error handling and cleanup are crucial. The method includes a 
 * finally block that ensures the client's socket is closed when they disconnect 
 * or an error occurs. It also removes the ClientHandler from the server's 
 * client list to prevent memory leaks and ensure the server does not attempt to 
 * send messages to a disconnected client.
 * 
 * Broadcasting Joining and Leaving Messages: 
 * The handler automatically broadcasts messages to all clients when a 
 * user joins or leaves the chat, providing a simple notification system for 
 * user presence.
 */
public class ClientHandler implements Runnable {
    private  Socket socket; // Socket representing the client connection
    private  Server server; // Reference to the server that handles all clients
    private PrintWriter out; // Writer to send messages back to the client
    private String userName; // Store the username of the client

    /**
     * Constructor for the ClientHandler.
     * @param socket The socket representing the connection to the client.
     * @param server The server instance this handler is part of.
     */
    public ClientHandler(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    /**
     * The main method that handles client communication.
     * It reads messages from the client and broadcasts them to all other clients.
     */
    @Override
    public void run() {
        try {
            InputStream input = socket.getInputStream(); // Get the input stream from the socket
            BufferedReader reader = new BufferedReader(new InputStreamReader(input)); // Create a reader to read messages from the client
            OutputStream output = socket.getOutputStream(); // Get the output stream from the socket
            out = new PrintWriter(output, true); // Initialize the writer with autoFlush

            // Read the first message from the client, which is expected to be the username
            this.userName = reader.readLine();
            // Broadcast that the user has joined the chat to all connected clients
            server.broadcastMessage(userName + " has joined the chat!");

            String clientMessage;
            // Continuously read messages from the client and broadcast them
            while ((clientMessage = reader.readLine()) != null) {
                server.broadcastMessage(userName + ": " + clientMessage);
            }

        } catch (IOException e) {
            System.out.println("Error handling client: " + e.getMessage()); // Log any exception that occurs
        } finally {
            // Remove this client from the server's list when the client disconnects
            server.clients.remove(this);
            // Notify all clients that this user has left the chat
            server.broadcastMessage(userName + " has left the chat.");
            try {
                socket.close(); // Close the client socket
            } catch (IOException e) {
                System.out.println("Error closing a socket: " + e.getMessage());
            }
        }
    }

    /**
     * Sends a message to this client.
     * @param message The message to send.
     */
    public void sendMessage(String message) {
        out.println(message); // Send the message through the PrintWriter
    }
}
