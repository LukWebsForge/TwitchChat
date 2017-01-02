package de.lukweb.twitchchat.irc;

import de.lukweb.twitchchat.TwitchChat;
import de.lukweb.twitchchat.events.irc.IrcQueueMessageEvent;
import de.lukweb.twitchchat.events.irc.IrcSendMessageEvent;

import java.util.ArrayList;
import java.util.List;

public class TurboMessageDelayer implements MessageDelayer {

    private TwitchChat chat;
    private IrcClient irc;
    private List<QueuedMessage> messages;

    public TurboMessageDelayer(TwitchChat chat, IrcClient irc) {
        this.chat = chat;
        this.irc = irc;
        this.messages = new ArrayList<>();
        new Thread(() -> {
            while (!irc.isClosed()) {
                long startTime = System.currentTimeMillis();

                int limit = getRemainingLimit() / 20;
                limit = limit < 1 ? 1 : limit;

                if (!isLimitReached()) messages.stream()
                        .filter(msg -> !msg.isSent())
                        .sorted()
                        .limit(limit)
                        .forEachOrdered(this::send);

                cleanUp();

                try {
                    Thread.sleep(50 - (System.currentTimeMillis() - startTime));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void queue(String message, boolean operator) {
        QueuedMessage queuedMessage = new QueuedMessage(message, operator);

        IrcQueueMessageEvent event = new IrcQueueMessageEvent(queuedMessage);
        chat.getEventManager().callEvent(event);
        if (event.isCanceled()) return;

        messages.add(queuedMessage);
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

        for (QueuedMessage message : messages) {
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

    private void cleanUp() {
        int time = (int) (System.currentTimeMillis() / 1000);
        messages.removeIf(msg -> msg.isSent() && (msg.getSentTimestamp() + 2 * 60) < time);
    }
}
