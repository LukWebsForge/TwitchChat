package de.lukweb.twitchchat.irc;

public interface IrcClient {

    void setInputHandler(IrcInputHandler inputHandler);

    void sendString(String message);

    boolean isConnected();

    boolean isClosed();

    void close();
}
