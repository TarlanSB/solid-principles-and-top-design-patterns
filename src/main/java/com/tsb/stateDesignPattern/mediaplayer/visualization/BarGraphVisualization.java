package com.tsb.stateDesignPattern.mediaplayer.visualization;

import com.tsb.stateDesignPattern.mediaplayer.config.VisualizationConfig;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.io.InputStream;



public class BarGraphVisualization implements VisualizationStrategy {
    private VisualizationConfig config;

    public BarGraphVisualization() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("visualization_config.json")) {
            if (input == null) {
                throw new IOException("Configuration file not found");
            }
            config = VisualizationConfig.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            // Default values in case of error
            config = new VisualizationConfig();
            config.backgroundColor = "#000000";
            config.waveformColor = "#00FF00";
            config.axisColor = "#AAAAAA";
            config.progressLineColor = "#FF0000";
            config.fontColor = "#FFFFFF";
            config.barColor = "#00FF00";
            config.exaggerationFactor = 2.0;
            config.numBars = 512;
        }
    }

    @Override
    public void visualize(GraphicsContext gc, byte[] audioData, double progress) {
        // Set the background color of the canvas
        gc.setFill(Color.web(config.backgroundColor));
        gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        
        // Set the fill color for the bars
        gc.setFill(Color.web(config.barColor));

        double width = gc.getCanvas().getWidth();
        double height = gc.getCanvas().getHeight();
        double centerY = height / 2;
        double barWidth = width / config.numBars;

        // Convert the magnitude data to decibels
        int dataSize = Math.min(config.numBars, audioData.length);
        double[] magnitudes = new double[dataSize];
        for (int i = 0; i < dataSize; i++) {
            magnitudes[i] = 20 * Math.log10(Math.abs(audioData[i]) + 1);  // Add 1 to avoid log(0)
        }

        // Draw the bar graph
        for (int i = 0; i < dataSize; i++) {
            double x = i * barWidth;
            double barHeight = magnitudes[i] * config.exaggerationFactor;
            double yTop = centerY - barHeight / 2;
            gc.fillRect(x, yTop, barWidth - 1, barHeight);  // Subtract 1 for spacing between bars
        }

        // Draw progress line
        gc.setStroke(Color.web(config.progressLineColor));
        double progressX = width * progress;
        gc.strokeLine(progressX, 0, progressX, height);
    }
}
