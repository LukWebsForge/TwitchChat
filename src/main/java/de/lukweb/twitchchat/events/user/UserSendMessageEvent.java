package de.lukweb.twitchchat.events.user;

import de.lukweb.twitchchat.TwitchUser;
import de.lukweb.twitchchat.twitch.messages.MessageAttributes;

public class UserSendMessageEvent extends UserEvent {

    private String message;
    private MessageAttributes attributes;

    public UserSendMessageEvent(TwitchUser user, String message, MessageAttributes attributes) {
        super(user);
        this.message = message;
        this.attributes = attributes;
    }

    public String getMessage() {
        return message;
    }

    public MessageAttributes getAttributes() {
        return attributes;
    }
}
