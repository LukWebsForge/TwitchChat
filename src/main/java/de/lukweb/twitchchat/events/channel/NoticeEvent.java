package de.lukweb.twitchchat.events.channel;

import de.lukweb.twitchchat.TwitchChannel;

public class NoticeEvent extends ChannelEvent {

    private String messageId;
    private String message;

    public NoticeEvent(TwitchChannel channel, String messageId, String message) {
        super(channel);
        this.messageId = messageId;
        this.message = message;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getMessage() {
        return message;
    }

}
