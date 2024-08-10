package com.tsb.stateDesignPattern.mediaplayer.visualization;

import javafx.scene.canvas.GraphicsContext;

public interface VisualizationStrategy {
    void visualize(GraphicsContext gc, byte[] audioData, double progress);
}
