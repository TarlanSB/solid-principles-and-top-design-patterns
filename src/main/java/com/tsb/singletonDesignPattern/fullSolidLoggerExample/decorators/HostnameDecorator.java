package com.tsb.singletonDesignPattern.fullSolidLoggerExample.decorators;

import com.tsb.singletonDesignPattern.fullSolidLoggerExample.core.Logger;

import java.net.InetAddress;

public class HostnameDecorator extends AbstractLogDecorator {

    public HostnameDecorator(Logger logger) {
        super(logger);
    }

    @Override
    protected String decorate(String message) {
        String hostname;
        try {
            hostname = InetAddress.getLocalHost().getHostName();
        } catch (Exception e) {
            hostname = "Unknown Host";
        }
        return String.format("[%s] %s", hostname, message);
    }
}

