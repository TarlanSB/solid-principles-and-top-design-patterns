package singletonDesignPattern.fullSolidLoggerExample.impl;

import singletonDesignPattern.fullSolidLoggerExample.core.Logger;

public class ConsoleLogger implements Logger {
    @Override
    public void log(String message) {
        System.out.println(message);
    }
}


