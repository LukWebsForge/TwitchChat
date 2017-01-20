package de.lukweb.twitchchat.events.user;

import de.lukweb.twitchchat.TwitchUser;
import de.lukweb.twitchchat.twitch.messages.MessageAttributes;

public class UserDataUpdateEvent extends UserEvent {

    private MessageAttributes attributes;

    public UserDataUpdateEvent(TwitchUser user, MessageAttributes attributes) {
        super(user);
        this.attributes = attributes;
    }

    /**
     * Gets the new data which will be set
     *
     * @return the new data which will be set
     */
    public MessageAttributes getAttributes() {
        return attributes;
    }
}
