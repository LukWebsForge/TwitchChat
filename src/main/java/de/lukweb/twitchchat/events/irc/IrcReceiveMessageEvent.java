package de.lukweb.twitchchat.events.irc;

import de.lukweb.twitchchat.events.Cancellable;
import de.lukweb.twitchchat.events.Event;

/**
 * Called when the irc client receives a message from the server
 */
public class IrcReceiveMessageEvent extends Event implements Cancellable {

    private String message;
    private boolean canceled;

    public IrcReceiveMessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    @Override
    public boolean isCanceled() {
        return canceled;
    }
}
