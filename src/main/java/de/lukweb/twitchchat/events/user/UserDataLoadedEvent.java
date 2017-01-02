package de.lukweb.twitchchat.events.user;

import de.lukweb.twitchchat.TwitchUser;
import de.lukweb.twitchchat.twitch.messages.StateAttributes;

public class UserDataLoadedEvent extends UserEvent {

    private StateAttributes attributes;

    public UserDataLoadedEvent(TwitchUser user, StateAttributes attributes) {
        super(user);
        this.attributes = attributes;
    }

    public StateAttributes getAttributes() {
        return attributes;
    }
}
