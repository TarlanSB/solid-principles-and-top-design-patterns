package com.tsb.stateDesignPattern.mediaplayer.controller;

// State representing the paused state of the media player
public class PausedState implements State {
    @Override
    public void play(MediaPlayerContext player) {
        System.out.println("Resuming playback.");
        player.getMediaPlayer().play();
        player.setState(new PlayingState()); // Transition to PlayingState
    }

    @Override
    public void pause(MediaPlayerContext player) {
        System.out.println("The player is already paused.");
    }

    @Override
    public void stop(MediaPlayerContext player) {
        System.out.println("Stopping playback.");
        player.getMediaPlayer().stop();
        player.setState(new StoppedState()); // Transition to StoppedState
    }
}
