package org.slf4j;

public class Logger {
    public Logger(Class<?> clazz, LoggerOption option) {}
    
    public void setWriter(LoggerWriter writer) {}
    
    public void info(String msg, Exception e) {}
    public void debug(String msg, Exception e) {}
    public void warn(String msg, Exception e) {}
    public void trace(String msg, Exception e) {}
    public void error(String msg, Exception e) {}
    
    public void info(String msg) {}
    public void debug(String msg) {}
    public void warn(String msg) {}
    public void trace(String msg) {}
    public void error(String msg) {}
    
    public boolean isDebugEnabled() { return LoggerOption.debugEnabled; }
    public boolean isInfoEnabled() { return LoggerOption.infoEnabled; }
    public boolean isErrorEnabled() { return LoggerOption.errorEnabled; }
    public boolean isTraceEnabled() { return LoggerOption.traceEnabled; }
    public boolean isWarnEnabled() { return LoggerOption.warnEnabled; }
    
    public void setAppendTime(boolean append) {}
    public void setLogOption(boolean d, boolean i, boolean w, boolean e, boolean t) {}
}
