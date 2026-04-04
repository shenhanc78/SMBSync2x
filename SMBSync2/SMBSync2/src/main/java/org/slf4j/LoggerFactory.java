package org.slf4j;

public class LoggerFactory {
    public LoggerFactory() {}
    
    public static Logger getLogger(Class<?> clazz) {
        return new Logger(clazz, new LoggerOption());
    }
}
