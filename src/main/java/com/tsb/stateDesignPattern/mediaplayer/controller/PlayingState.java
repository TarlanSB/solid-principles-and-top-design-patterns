package com.tsb.stateDesignPattern.mediaplayer.controller;

// State representing the playing state of the media player
public class PlayingState implements State {
    @Override
    public void play(MediaPlayerContext player) {
        System.out.println("The player is already playing.");
    }

    @Override
    public void pause(MediaPlayerContext player) {
        System.out.println("Pausing playback.");
        player.getMediaPlayer().pause();
        player.setState(new PausedState()); // Transition to PausedState
    }

    @Override
    public void stop(MediaPlayerContext player) {
        System.out.println("Stopping playback.");
        player.getMediaPlayer().stop();
        player.setState(new StoppedState()); // Transition to StoppedState
    }
}
