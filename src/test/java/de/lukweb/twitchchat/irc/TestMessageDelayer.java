package de.lukweb.twitchchat.irc;

import de.lukweb.twitchchat.TwitchChat;
import de.lukweb.twitchchat.events.irc.IrcQueueMessageEvent;
import de.lukweb.twitchchat.events.irc.IrcSendMessageEvent;

public class TestMessageDelayer implements MessageDelayer {

    private TwitchChat chat;
    private IrcClient irc;

    public TestMessageDelayer(TwitchChat chat, IrcClient irc) {
        this.chat = chat;
        this.irc = irc;
    }

    @Override
    public void queue(String message, boolean operator) {
        QueuedMessage queuedMessage = new QueuedMessage(message, operator);

        IrcQueueMessageEvent queueEvent = new IrcQueueMessageEvent(queuedMessage);
        chat.getEventManager().callEvent(queueEvent);
        if (queueEvent.isCanceled()) return;

        IrcSendMessageEvent sendEvent = new IrcSendMessageEvent(queuedMessage);
        chat.getEventManager().callEvent(sendEvent);
        if (sendEvent.isCanceled()) return;

        irc.sendString(message);
    }
}
