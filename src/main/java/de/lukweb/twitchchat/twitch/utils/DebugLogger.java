package de.lukweb.twitchchat.twitch.utils;

import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;

public class DebugLogger {

    public static Logger get(String name) {
        Logger logger = Logger.getLogger(name);
        Handler handler = new StdOutHandler();
        handler.setLevel(Level.ALL);
        logger.addHandler(handler);
        logger.setLevel(Level.OFF);
        logger.setUseParentHandlers(false);
        return logger;
    }

    private static class StdOutHandler extends StreamHandler {

        StdOutHandler() {
            setOutputStream(System.out);
            setFormatter(new Formatter() {
                @Override
                public String format(LogRecord record) {
                    return "[" + record.getLoggerName() + "] " + record.getMessage() + "\n";
                }
            });
        }

        @Override
        public void publish(LogRecord record) {
            super.publish(record);
            flush();
        }

        @Override
        public void close() {
            flush();
        }
    }

}
