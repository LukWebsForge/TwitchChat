package de.lukweb.twitchchat.irc;

import de.lukweb.twitchchat.TwitchChat;
import de.lukweb.twitchchat.events.irc.IrcQueueMessageEvent;
import de.lukweb.twitchchat.events.irc.IrcSendMessageEvent;

import java.util.LinkedList;

public class TurboMessageDelayer implements MessageDelayer {

    private TwitchChat chat;
    private IrcClient irc;
    private LinkedList<QueuedMessage> queuedMessages;

    // todo use queue

    public TurboMessageDelayer(TwitchChat chat, IrcClient irc) {
        this.chat = chat;
        this.irc = irc;
        this.queuedMessages = new LinkedList<>();
        new Thread(() -> {
            while (!irc.isClosed()) {
                long startTime = System.currentTimeMillis();

                int limit = getRemainingLimit() / 20;
                limit = limit < 1 ? 1 : limit;

                if (!isLimitReached()) {
                    for (int i = 0; i < limit; i++) {
                        QueuedMessage poll = queuedMessages.poll();
                        if (poll == null) break;
                        send(poll);
                    }
                }

                try {
                    Thread.sleep(50 - (System.currentTimeMillis() - startTime));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "TwitchChat-MessageDelayer").start();
    }

    @Override
    public void queue(String message, boolean operator) {
        QueuedMessage queuedMessage = new QueuedMessage(message, operator);

        IrcQueueMessageEvent event = new IrcQueueMessageEvent(queuedMessage);
        chat.getEventManager().callEvent(event);
        if (event.isCanceled()) return;

        queuedMessages.add(queuedMessage);
    }

    private void send(QueuedMessage message) {

        IrcSendMessageEvent event = new IrcSendMessageEvent(message);
        chat.getEventManager().callEvent(event);
        if (event.isCanceled()) return;

        irc.sendString(message.getMessage());
        message.setSend();
    }

    private boolean isLimitReached() {
        return getRemainingLimit() <= 0;
    }

    private int getRemainingLimit() {
        boolean nonOperatorMsg = false;
        int messageCount = 0;

        for (QueuedMessage message : queuedMessages) {
            if (!message.isSent()) continue;
            if ((message.getSentTimestamp() + 30) < (System.currentTimeMillis() / 1000)) continue;
            if (!message.isOperator()) nonOperatorMsg = true;
            messageCount++;
        }

        if (nonOperatorMsg) {
            return 20 - messageCount;
        } else {
            return 200 - messageCount;
        }
    }
}
