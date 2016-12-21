package de.lukweb.twitchchat.events.irc;

import de.lukweb.twitchchat.events.Event;
import de.lukweb.twitchchat.irc.QueuedMessage;

public class QueuedMessageEvent extends Event {

    private QueuedMessage message;

    public QueuedMessageEvent(QueuedMessage message) {
        this.message = message;
    }

    public QueuedMessage getMessage() {
        return message;
    }
}
