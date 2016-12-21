package de.lukweb.twitchchat.events.irc;

import de.lukweb.twitchchat.events.Cancellable;
import de.lukweb.twitchchat.irc.QueuedMessage;

/**
 * Called when the irc client sends a message to the server
 */
public class IrcSendMessageEvent extends QueuedMessageEvent implements Cancellable {

    private boolean canceled;

    public IrcSendMessageEvent(QueuedMessage message) {
        super(message);
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
