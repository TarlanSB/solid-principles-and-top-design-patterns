package com.tsb.stateDesignPattern.mediaplayer.controller;

// State representing the finished state of the media player
public class FinishedState implements State {
    @Override
    public void play(MediaPlayerContext player) {
        System.out.println("Replaying media from the start.");
        player.getMediaPlayer().seek(javafx.util.Duration.ZERO);
        player.getMediaPlayer().play();
        player.setState(new PlayingState()); // Transition to PlayingState
    }

    @Override
    public void pause(MediaPlayerContext player) {
        // Cannot pause, media is finished
    }

    @Override
    public void stop(MediaPlayerContext player) {
        System.out.println("Stopping media from finished state.");
        player.getMediaPlayer().stop();
        player.setState(new StoppedState()); // Transition to StoppedState
    }
}
