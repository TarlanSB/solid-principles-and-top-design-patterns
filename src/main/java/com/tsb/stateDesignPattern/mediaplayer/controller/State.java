package com.tsb.stateDesignPattern.mediaplayer.controller;

// Interface defining the state transitions for a media player
public interface State {
    void play(MediaPlayerContext player);
    void pause(MediaPlayerContext player);
    void stop(MediaPlayerContext player);
}
