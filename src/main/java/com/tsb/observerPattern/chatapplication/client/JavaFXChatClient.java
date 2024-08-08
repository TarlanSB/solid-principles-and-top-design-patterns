package com.tsb.observerPattern.chatapplication.client;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.*;
import java.net.Socket;


/**
 * JavaFX UI Elements: 
 * The UI is split into two parts: the login interface and the chat interface. 
 * The user must log in before accessing the chat.
 * 
 * Network Communication: 
 * The client establishes a connection to the server via sockets and 
 * communicates using input and output streams.
 * 
 * Concurrency: 
 * The application handles network communication on a separate 
 * thread to avoid blocking the UI, ensuring the application remains responsive.
 * 
 * Event Handling: 
 * User actions like sending messages or closing the window are handled 
 * through event handlers, making the interface interactive.
 */
public class JavaFXChatClient extends Application {
    private TextArea messageArea;  // Text area for displaying received messages
    private TextField inputField;  // Text field for typing new messages
    private TextField userNameField;  // Text field to enter the username
    private Button sendButton;  // Button to send messages
    private PrintWriter writer;  // To write messages to the server
    private Socket socket;  // Socket for connecting to the server
    private String userName;  // Stores the user's chosen username

    /**
     * Starts the primary stage of the application, setting up the login and chat UI.
     * @param primaryStage The primary stage for this application, onto which the scene is set.
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chat Client");

        // Login layout configuration
        VBox loginLayout = new VBox(10);
        userNameField = new TextField();
        userNameField.setPromptText("Enter your username and press Enter");
        Button loginButton = new Button("Login");
        loginLayout.getChildren().addAll(userNameField, loginButton);

        // Chat layout configuration
        BorderPane chatLayout = new BorderPane();
        messageArea = new TextArea();
        messageArea.setEditable(false);
        inputField = new TextField();
        inputField.setDisable(true); // Disabled until logged in
        sendButton = new Button("Send");
        sendButton.setDisable(true); // Disabled until logged in
        BorderPane bottomLayout = new BorderPane();
        bottomLayout.setCenter(inputField);
        bottomLayout.setRight(sendButton);
        chatLayout.setCenter(messageArea);
        chatLayout.setBottom(bottomLayout);

        Scene loginScene = new Scene(loginLayout, 400, 300);
        Scene chatScene = new Scene(chatLayout, 400, 300);

        // Event handler for login button
        loginButton.setOnAction(e -> {
            userName = userNameField.getText();
            if (!userName.isEmpty()) {
                startClient();  // Start the client connection
                primaryStage.setScene(chatScene);  // Switch to chat scene after login
            }
        });

        // Event handlers for sending messages
        sendButton.setOnAction(e -> sendMessage());
        inputField.setOnAction(e -> sendMessage());

        // Handle window close request
        primaryStage.setOnCloseRequest(event -> {
            sendMessage(userName + " has left the chat.");
            try {
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        primaryStage.setScene(loginScene);  // Set the initial scene as the login scene
        primaryStage.show();
    }

    /**
     * Establishes a connection to the server and initializes communication streams.
     */
    private void startClient() {
        String hostname = "localhost";
        int port = 12345;
        try {
            socket = new Socket(hostname, port);  // Connect to the server
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
            writer.println(userName);  // Send username immediately after connecting
            inputField.setDisable(false);
            sendButton.setDisable(false);

            // Start a new thread to read messages from the server
            Thread readerThread = new Thread(this::readMessages);
            readerThread.start();
        } catch (IOException ex) {
            messageArea.appendText("Could not connect to the server.\n");
        }
    }

    /**
     * Sends a message typed by the user to the server.
     */
    private void sendMessage() {
        String message = inputField.getText();
        if (!message.isEmpty()) {
            writer.println(message);  // Send the message to the server
            inputField.clear();  // Clear the input field after sending
        }
    }
    
    /**
     * Overloaded method to send a specific message (e.g., system messages like exiting).
     * @param message The message to be sent.
     */
    private void sendMessage(String message) {
        if (writer != null) {
            writer.println(message);  // Send the specific message text to the server
        } else {
            messageArea.appendText("Not connected to server.\n");
        }
    }

    /**
     * Continuously reads messages from the server and updates the chat display.
     */
    private void readMessages() {
        try {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            String line;
            while ((line = reader.readLine()) != null) {
                final String finalLine = line;
                Platform.runLater(() -> messageArea.appendText(finalLine + "\n"));
            }
        } catch (IOException ex) {
            Platform.runLater(() -> messageArea.appendText("Error reading from server.\n"));
        }
    }

    /**
     * The main method that launches the JavaFX application.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        launch(args);
    }
}
