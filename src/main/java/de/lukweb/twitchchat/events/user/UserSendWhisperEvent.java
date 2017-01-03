package de.lukweb.twitchchat.events.user;

import de.lukweb.twitchchat.events.Event;
import de.lukweb.twitchchat.twitch.messages.WhisperAttributes;

public class UserSendWhisperEvent extends Event {

    private String user;
    private String message;
    private WhisperAttributes attributes;

    public UserSendWhisperEvent(String user, String message, WhisperAttributes attributes) {
        this.user = user;
        this.message = message;
        this.attributes = attributes;
    }

    public String getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }

    public WhisperAttributes getAttributes() {
        return attributes;
    }
}
