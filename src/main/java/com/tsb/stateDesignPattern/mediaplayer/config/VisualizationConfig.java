package com.tsb.stateDesignPattern.mediaplayer.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class VisualizationConfig {
    @JsonProperty("backgroundColor")
    public String backgroundColor;

    @JsonProperty("waveformColor")
    public String waveformColor;

    @JsonProperty("axisColor")
    public String axisColor;

    @JsonProperty("progressLineColor")
    public String progressLineColor;

    @JsonProperty("fontColor")
    public String fontColor;

    @JsonProperty("barColor")
    public String barColor;

    @JsonProperty("exaggerationFactor")
    public double exaggerationFactor;

    @JsonProperty("numBars")
    public int numBars;

    public static VisualizationConfig load(InputStream inputStream) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(inputStream, VisualizationConfig.class);
    }
}
