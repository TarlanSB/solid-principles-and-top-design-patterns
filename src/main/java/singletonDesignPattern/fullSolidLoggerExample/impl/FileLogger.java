package singletonDesignPattern.fullSolidLoggerExample.impl;

import singletonDesignPattern.fullSolidLoggerExample.core.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileLogger implements Logger {
    private PrintWriter writer;

    public FileLogger(String fileName) throws IOException {
        writer = new PrintWriter(new FileWriter(fileName, true));
    }

    @Override
    public void log(String message) {
        writer.println(message);
        writer.flush();
    }
}
