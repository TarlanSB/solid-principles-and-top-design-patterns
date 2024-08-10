package com.tsb.stateDesignPattern.mediaplayer.ui;

import com.tsb.stateDesignPattern.mediaplayer.controller.*;
import com.tsb.stateDesignPattern.mediaplayer.visualization.VisualizationStrategy;
import com.tsb.stateDesignPattern.mediaplayer.visualization.WaveformVisualization;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class MediaPlayerUI extends Application {
    private final MediaPlayerContext player = new MediaPlayerContext();
    private Canvas canvas;
    private final VisualizationStrategy visualizationStrategy = new WaveformVisualization();
    private Label trackInfoLabel;
    private Label trackNameLabel;
    private Label stateLabel;
    private ProgressBar progressBar;
    private CheckBox loopSwitch;
    private Button playButton;
    private Button pauseButton;
    private Button stopButton;
    private Button loadButton;

    @Override
    public void start(Stage primaryStage) {
        // Initialize canvas for visualization
        canvas = new Canvas(600, 300); // Increased size for better visualization

        // Initialize load button and its action
        loadButton = new Button("Load File");
        loadButton.setOnAction(e -> loadFile(primaryStage));

        // Initialize play button and its action
        playButton = new Button("Play");
        playButton.setOnAction(e -> {
            player.play();
            if (player.getMediaPlayer().getCurrentTime().equals(Duration.ZERO)) {
                progressBar.setProgress(0); // Reset progress bar if starting from the beginning
            }
            updateStateLabel(); // Update state label to reflect current state
            updateButtonStates(); // Update button states based on current state
        });

        // Initialize pause button and its action
        pauseButton = new Button("Pause");
        pauseButton.setOnAction(e -> {
            player.pause();
            updateStateLabel(); // Update state label to reflect current state
            updateButtonStates(); // Update button states based on current state
        });

        // Initialize stop button and its action
        stopButton = new Button("Stop");
        stopButton.setOnAction(e -> {
            player.stop();
            updateStateLabel(); // Update state label to reflect current state
            updateButtonStates(); // Update button states based on current state
        });

        // Initialize loop switch and its action
        loopSwitch = new CheckBox("Loop");
        loopSwitch.setSelected(false);
        loopSwitch.setStyle("-fx-switch-width: 20px; -fx-switch-height: 10px;");
        loopSwitch.selectedProperty().addListener((obs, wasSelected, isSelected) -> player.setLoop(isSelected));

        // Create control buttons container
        HBox controls = new HBox(10, loadButton, playButton, pauseButton, stopButton, loopSwitch);
        controls.setAlignment(Pos.CENTER);

        // Initialize labels and progress bar for track info
        trackInfoLabel = new Label("Time: 0.00 / 0.00 seconds");
        trackNameLabel = new Label("Track: None");
        stateLabel = new Label("State: Stopped"); // Initial state
        progressBar = new ProgressBar();
        progressBar.setPrefHeight(5);
        progressBar.setProgress(0.0f);
        progressBar.setPrefWidth(Double.MAX_VALUE);
        progressBar.setStyle("-fx-accent: green;");

        // Create container for track info labels
        HBox trackInfoContainer = new HBox(10, trackNameLabel, trackInfoLabel, stateLabel);
        trackInfoContainer.setAlignment(Pos.CENTER);

        // Create container for top section
        VBox topContainer = new VBox(10, trackInfoContainer, progressBar);
        topContainer.setAlignment(Pos.CENTER);

        // Create the root layout and set the components
        BorderPane root = new BorderPane();
        root.setCenter(canvas);
        root.setBottom(controls);
        root.setTop(topContainer);

        // Create the scene and set it to the primary stage
        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        primaryStage.setTitle("Simple Media Player");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Add a periodic task to update the visualization and track info
        new javafx.animation.AnimationTimer() {
            @Override
            public void handle(long now) {
                updateVisualization(); // Update the visualization on canvas
                updateTrackInfo(); // Update the track info label
                updateStateLabel(); // Update the state label
                updateButtonStates(); // Update the button states
            }
        }.start();
    }

    // Method to load a media file
    private void loadFile(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Media Files", "*.mp4", "*.mp3", "*.wav")
        );
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            System.out.println("Chosen file path: " + file.getAbsolutePath());
            player.stop(); // Stop any currently playing file
            player.setMediaFile(file.getAbsolutePath()); // Set the new media file
            trackNameLabel.setText("Track: " + file.getName()); // Update track name label
            progressBar.setProgress(0); // Reset the progress bar
            updateTrackInfo(); // Update track info label
            updateStateLabel(); // Update state label
        }
    }

    // Method to update the visualization on canvas
    private void updateVisualization() {
        byte[] spectrumData = player.getSpectrumData();
        if (spectrumData != null) {
            GraphicsContext gc = canvas.getGraphicsContext2D();
            double progress = player.getMediaPlayer().getCurrentTime().toSeconds() / player.getMediaPlayer().getTotalDuration().toSeconds();
            visualizationStrategy.visualize(gc, spectrumData, progress); // Use the visualization strategy to draw on canvas
        }
    }

    // Method to update the track info label
    private void updateTrackInfo() {
        if (player.getMediaPlayer() != null) {
            MediaPlayer mediaPlayer = player.getMediaPlayer();
            MediaPlayer.Status status = mediaPlayer.getStatus();
            System.out.println("MediaPlayer Status: " + status);

            if (status == MediaPlayer.Status.PLAYING || status == MediaPlayer.Status.PAUSED || status == MediaPlayer.Status.READY || status == MediaPlayer.Status.STOPPED) {
                double currentTime = mediaPlayer.getCurrentTime().toSeconds();
                double totalDuration = mediaPlayer.getTotalDuration() != null ? mediaPlayer.getTotalDuration().toSeconds() : 0;
                System.out.println("Current Time: " + currentTime);
                System.out.println("Total Duration: " + totalDuration);

                trackInfoLabel.setText(String.format("Time: %.2f / %.2f seconds", currentTime, totalDuration));
                if (totalDuration > 0) {
                    progressBar.setProgress(currentTime / totalDuration);
                    System.out.println("Progress Bar Data: " + (currentTime / totalDuration));
                }
            }
        }
    }

    // Method to update the state label
    private void updateStateLabel() {
        if (player.getMediaPlayer() != null) {
            State state = player.getState();
            String stateText;

            if (state instanceof PlayingState) {
                stateText = "Playing";
            } else if (state instanceof PausedState) {
                stateText = "Paused";
            } else if (state instanceof StoppedState) {
                stateText = "Stopped";
            } else if (state instanceof ReadyState) {
                stateText = "Ready";
            } else if (state instanceof FinishedState) {
                stateText = "Finished";
            } else {
                stateText = "Unknown";
            }
            stateLabel.setText("State: " + stateText); // Set the state label text
        }
    }

    // Method to update the button states based on the current state
    private void updateButtonStates() {
        State state = player.getState();

        if (state instanceof PlayingState) {
            playButton.setDisable(true);
            pauseButton.setDisable(false);
            stopButton.setDisable(false);
            loadButton.setDisable(true);
        } else if (state instanceof PausedState) {
            playButton.setDisable(false);
            pauseButton.setDisable(true);
            stopButton.setDisable(false);
            loadButton.setDisable(true);
        } else if (state instanceof StoppedState) {
            playButton.setDisable(player.getMediaPlayer() == null);
            pauseButton.setDisable(true);
            stopButton.setDisable(true);
            loadButton.setDisable(false);
        } else if (state instanceof ReadyState) {
            playButton.setDisable(false);
            pauseButton.setDisable(true);
            stopButton.setDisable(true);
            loadButton.setDisable(false);
        } else if (state instanceof FinishedState) {
            playButton.setDisable(false);
            pauseButton.setDisable(true);
            stopButton.setDisable(false);
            loadButton.setDisable(false);
        } else {
            playButton.setDisable(true);
            pauseButton.setDisable(true);
            stopButton.setDisable(true);
            loadButton.setDisable(true);
        }
    }

    // Main method to launch the application
    public static void main(String[] args) {
        launch(args);
    }
}