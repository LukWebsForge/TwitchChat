package de.lukweb.twitchchat.irc;

import java.util.ArrayList;
import java.util.List;

public class TestIrcClient implements IrcClient {

    private List<String> inMessageLog;
    private List<String> outMessageLog;
    private IrcInputHandler inputHandler;
    private boolean closed;

    public TestIrcClient() {
        inMessageLog = new ArrayList<>();
        outMessageLog = new ArrayList<>();
    }

    @Override
    public void setInputHandler(IrcInputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    public void sendStringAsTwitch(String message) {
        inMessageLog.add(message);
        if (inputHandler == null) return;
        inputHandler.handle(message);
    }

    @Override
    public void sendString(String message) {
        outMessageLog.add(message);
    }

    public List<String> getInMessageLog() {
        return inMessageLog;
    }

    public List<String> getOutMessageLog() {
        return outMessageLog;
    }

    @Override
    public boolean isConnected() {
        return !closed;
    }

    @Override
    public boolean isClosed() {
        return closed;
    }

    @Override
    public void close() {
        closed = true;
    }
}
