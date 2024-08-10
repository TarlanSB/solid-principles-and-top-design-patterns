package com.tsb.stateDesignPattern.mediaplayer.visualization;

import java.io.IOException;
import java.io.InputStream;

import com.tsb.stateDesignPattern.mediaplayer.config.VisualizationConfig;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class WaveformVisualization implements VisualizationStrategy {
    private VisualizationConfig config;

    public WaveformVisualization() {
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
        gc.setFill(Color.web(config.backgroundColor));
        gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        gc.setStroke(Color.web(config.waveformColor));

        double width = gc.getCanvas().getWidth();
        double height = gc.getCanvas().getHeight();
        double centerY = height / 2;

        // Convert bytes to samples
        double[] samples = new double[audioData.length / 2];
        for (int i = 0; i < samples.length; i++) {
            samples[i] = (audioData[2 * i] << 8 | audioData[2 * i + 1] & 0xFF) / 32768.0;
        }

        // Draw the waveform
        for (int i = 0; i < samples.length - 1; i++) {
            double x1 = i * (width / (samples.length - 1));
            double y1 = centerY - (samples[i] * config.exaggerationFactor * height / 2);
            double x2 = (i + 1) * (width / (samples.length - 1));
            double y2 = centerY - (samples[i + 1] * config.exaggerationFactor * height / 2);
            gc.strokeLine(x1, y1, x2, y2);
        }

        // Draw progress line
        gc.setStroke(Color.web(config.progressLineColor));
        double progressX = width * progress;
        gc.strokeLine(progressX, 0, progressX, height);
    }
}
