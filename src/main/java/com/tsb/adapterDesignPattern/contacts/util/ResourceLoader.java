package com.tsb.adapterDesignPattern.contacts.util;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ResourceLoader {
    public String loadResource(String fileName) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream == null) {
                return null; // resource not found
            }
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

