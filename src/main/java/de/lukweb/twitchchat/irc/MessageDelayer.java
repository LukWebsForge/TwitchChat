package de.lukweb.twitchchat.irc;

public interface MessageDelayer {

    void queue(String message, boolean operator);
}
