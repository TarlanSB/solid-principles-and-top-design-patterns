package singletonDesignPattern.fullSolidLoggerExample.decorators;


import singletonDesignPattern.fullSolidLoggerExample.core.Logger;

public abstract class AbstractLogDecorator implements Logger {
    protected Logger wrappedLogger;

    public AbstractLogDecorator(Logger logger) {
        this.wrappedLogger = logger;
    }

    @Override
    public void log(String message) {
        wrappedLogger.log(decorate(message));
    }

    // Abstract method that subclasses will implement to provide specific decoration
    protected abstract String decorate(String message);
}

