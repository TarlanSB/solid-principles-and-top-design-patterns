package com.tsb.observerPattern.chatapplication.server;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * ServerSocket: 
 * This is a special type of socket used for listening for incoming network requests. 
 * In our server application, it's set up to listen on a specified port.
 * 
 * ExecutorService: 
 * This manages a pool of threads. Each client is handled in a 
 * separate thread, allowing multiple clients to be serviced simultaneously.
 * 
 * Synchronized List: 
 * This is used to handle the list of client connections. 
 * It ensures that the list is thread-safe, meaning it can be modified by multiple 
 * threads without causing data corruption.
 * 
 * Infinite Loop: 
 * The server runs an infinite loop that continuously listens  for new client connections. 
 * This is typical for servers that need to handle clients at any time without a predefined limit.
 */
public class Server {
    private int port; // Port number on which the server will listen for incoming connections
    private ServerSocket serverSocket; // Server socket that listens for incoming client connections
    private ExecutorService pool = Executors.newCachedThreadPool(); // A thread pool for handling client connections concurrently
    List<ClientHandler> clients = Collections.synchronizedList(new ArrayList<>()); // Thread-safe list of client handlers

    /**
     * Constructor that sets the port number for the server.
     * @param port The port number on which the server will operate.
     */
    public Server(int port) {
        this.port = port;
    }

    /**
     * Starts the server and listens for incoming connections.
     * Accepts each connection, creates a handler for it, and processes it in a separate thread.
     */
    public void start() {
        try {
            serverSocket = new ServerSocket(port); // Create a server socket bound to the specified port
            System.out.println("Server started on port: " + port);

            //
            // Infinite loop to accept new clients continuously
            //
            while (true) { 
                Socket clientSocket = serverSocket.accept(); // Accept an incoming client connection
                ClientHandler clientHandler = new ClientHandler(clientSocket, this); // Create a new ClientHandler for the connected client
                clients.add(clientHandler); // Add the new client handler to the list of managed clients
                pool.execute(clientHandler); // Execute the client handler in a separate thread
            }
        } catch (IOException e) {
            System.out.println("Error starting server: " + e.getMessage()); // Handle exceptions related to I/O errors
        }
    }

    /**
     * Broadcasts a message to all connected clients.
     * @param message The message to be broadcasted.
     */
    public void broadcastMessage(String message) {
        for (ClientHandler client : clients) { // Iterate over all clients
            client.sendMessage(message); // Send the message to each client
        }
    }

    /**
     * Main method to start the server.
     * @param args Command line arguments (not used here).
     */
    public static void main(String[] args) {
        int port = 12345; // Example port number
        Server server = new Server(port); // Create a server instance on the specified port
        server.start(); // Start the server
    }
}
