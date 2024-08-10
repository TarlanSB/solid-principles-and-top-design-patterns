package com.tsb.stateDesignPattern.mediaplayer.controller;

// State representing the ready state of the media player
public class ReadyState implements State {
    @Override
    public void play(MediaPlayerContext player) {
        System.out.println("Playing media from ready state.");
        player.getMediaPlayer().play();
        player.setState(new PlayingState()); // Transition to PlayingState
    }

    @Override
    public void pause(MediaPlayerContext player) {
        // Ready state cannot be paused directly
    }

    @Override
    public void stop(MediaPlayerContext player) {
        System.out.println("Stopping media from ready state.");
        player.getMediaPlayer().stop();
        player.setState(new StoppedState()); // Transition to StoppedState
    }
}
