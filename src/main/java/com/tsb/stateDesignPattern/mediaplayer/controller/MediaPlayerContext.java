
package com.tsb.stateDesignPattern.mediaplayer.controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class MediaPlayerContext {
    private State state;
    private MediaPlayer mediaPlayer;
    private boolean loop;
    private byte[] audioData;

    public MediaPlayerContext() {
        state = new StoppedState(); // Initial state is StoppedState
        loop = false; // Looping is initially set to false
    }

    // Sets the media file to be played
    public void setMediaFile(String mediaFilePath) {
        try {
            File file = new File(mediaFilePath);
            String uriString = file.toURI().toString().replace(" ", "%20");
            mediaPlayer = new MediaPlayer(new Media(uriString));
            mediaPlayer.setOnReady(() -> {
                state = new ReadyState(); // Transition to ReadyState when media is ready
                System.out.println("MediaPlayer is ready.");
            });
            mediaPlayer.setOnEndOfMedia(() -> {
                if (loop) {
                    mediaPlayer.seek(Duration.ZERO); // Loop the media if loop is true
                    mediaPlayer.play();
                    System.out.println("----> Media has looped.");
                } else {
                    mediaPlayer.seek(Duration.ZERO); // Stop the media when it ends
                    mediaPlayer.stop();
                    state = new FinishedState(); // Transition to FinishedState when media ends
                    System.out.println("----> Media has ended.");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Reads raw audio data (placeholder for future implementation)
    private void readAudioData(File file) {
        try {
            // Example using Apache Tika or a similar library
            // This is a placeholder for actual implementation
            // which reads the raw audio data for visualization

            // Placeholder code for reading raw audio data
            // audioData = ... ;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Returns the audio spectrum data
    public byte[] getSpectrumData() {
        return audioData;
    }

    // Returns the MediaPlayer instance
    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    // Sets the looping behavior for the media
    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    // Plays the media using the current state
    public void play() {
        state.play(this);
        state = new PlayingState(); // Transition to PlayingState
        if (mediaPlayer != null) {
            if (state instanceof FinishedState) {
                mediaPlayer.seek(Duration.ZERO);
                mediaPlayer.play();
            } else {
                mediaPlayer.play();
            }
        }
    }

    // Pauses the media using the current state
    public void pause() {
        state.pause(this);
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    // Stops the media using the current state
    public void stop() {
        state.stop(this);
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    // Sets the current state of the media player
    public void setState(State state) {
        this.state = state;
    }

    // Returns the current state of the media player
    public State getState() {
        return state;
    }
}