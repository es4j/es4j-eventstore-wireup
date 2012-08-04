package org.es4j.eventstore.wireup;

import org.es4j.eventstore.core.logging.ConsoleWindowLogger;
import org.es4j.eventstore.core.logging.OutputWindowLogger;
import org.es4j.util.logging.ILog;
import org.es4j.util.logging.LogFactory;
import org.es4j.util.logging.LoggerDelegate;


public class LoggingWireupExtensions {
    
    public static Wireup logToConsoleWindow(/*this*/ Wireup wireup) {
        //return wireup.logTo(type => new ConsoleWindowLogger(type));
        return wireup.logTo(new LoggerDelegate() {

            @Override
            public <T> ILog buildLogger(Class<T> typeToLog) {
                return new ConsoleWindowLogger(typeToLog);
            }
        });        
    }
                
    public static Wireup logToOutputWindow(/*this*/ Wireup wireup) {
        //return wireup.logTo(type => new OutputWindowLogger(type));
        return wireup.logTo(new LoggerDelegate() {

            @Override
            public <T> ILog buildLogger(Class<T> typeToLog) {
                return new OutputWindowLogger(typeToLog);
            }
        });        
    }
                
    public static Wireup logTo(/*this*/ Wireup wireup, LoggerDelegate logger) {
        LogFactory.setBuildLogger(logger);
        return wireup;
    }
}