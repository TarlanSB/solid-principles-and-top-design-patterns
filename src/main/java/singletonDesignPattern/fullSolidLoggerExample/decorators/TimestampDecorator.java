package singletonDesignPattern.fullSolidLoggerExample.decorators;

import singletonDesignPattern.fullSolidLoggerExample.core.Logger;

import java.time.LocalDateTime;

public class TimestampDecorator extends AbstractLogDecorator {

    public TimestampDecorator(Logger logger) {
        super(logger);
    }

    @Override
    protected String decorate(String message) {
        LocalDateTime now = LocalDateTime.now();
        return String.format("[%s] %s", now, message);
    }
}

