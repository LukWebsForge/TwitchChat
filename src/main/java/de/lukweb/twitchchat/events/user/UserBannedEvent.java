package de.lukweb.twitchchat.events.user;

import de.lukweb.twitchchat.TwitchUser;

public class UserBannedEvent extends UserEvent {

    public UserBannedEvent(TwitchUser user) {
        super(user);
    }
}
