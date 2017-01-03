package de.lukweb.twitchchat.events.user;

import de.lukweb.twitchchat.TwitchUser;
import de.lukweb.twitchchat.twitch.messages.MessageAttributes;

public class UserSendMessageEvent extends UserEvent {

    private String message;
    private MessageAttributes attributes;
    private boolean emoteMessage;

    public UserSendMessageEvent(TwitchUser user, String message, MessageAttributes attributes, boolean emoteMessage) {
        super(user);
        this.message = message;
        this.attributes = attributes;
        this.emoteMessage = emoteMessage;
    }

    public String getMessage() {
        return message;
    }

    public MessageAttributes getAttributes() {
        return attributes;
    }

    /**
     * Gets whether this message was sent using the /me command
     *
     * @return whether this message was sent using the /me command
     */
    public boolean isEmoteMessage() {
        return emoteMessage;
    }
}
