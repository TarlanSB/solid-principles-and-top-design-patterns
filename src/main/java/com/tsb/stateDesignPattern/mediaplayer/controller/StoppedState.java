package com.tsb.stateDesignPattern.mediaplayer.controller;

// State representing the stopped state of the media player
public class StoppedState implements State {
    @Override
    public void play(MediaPlayerContext player) {
        if (player.getMediaPlayer() != null) {
            System.out.println("Playing media from the start.");
            player.getMediaPlayer().play();
            player.setState(new PlayingState()); // Transition to PlayingState
        } else {
            System.out.println("No media loaded.");
        }
    }

    @Override
    public void pause(MediaPlayerContext player) {
        System.out.println("Cannot pause. The player is already stopped.");
    }

    @Override
    public void stop(MediaPlayerContext player) {
        System.out.println("The player is already stopped.");
    }
}
