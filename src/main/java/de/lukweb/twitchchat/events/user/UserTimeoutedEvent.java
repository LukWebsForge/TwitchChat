package de.lukweb.twitchchat.events.user;

import de.lukweb.twitchchat.TwitchUser;

public class UserTimeoutedEvent extends UserEvent {

    public UserTimeoutedEvent(TwitchUser user) {
        super(user);
    }
}
