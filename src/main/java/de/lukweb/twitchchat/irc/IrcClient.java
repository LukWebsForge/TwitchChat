package de.lukweb.twitchchat.irc;

import java.util.function.Consumer;

public interface IrcClient {

    void setInputHandler(IrcInputHandler inputHandler);

    void setErrorHandler(Consumer<Throwable> errorHandler);

    void sendString(String message);

    boolean isConnected();

    boolean isClosed();

    void close();
}
