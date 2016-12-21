package de.lukweb.twitchchat.events.irc;

import de.lukweb.twitchchat.events.Cancellable;
import de.lukweb.twitchchat.irc.QueuedMessage;

/**
 * Called when the irc client queues a message for sending to the server
 */
public class IrcQueueMessageEvent extends QueuedMessageEvent implements Cancellable {

    private boolean canceled;

    public IrcQueueMessageEvent(QueuedMessage message) {
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
