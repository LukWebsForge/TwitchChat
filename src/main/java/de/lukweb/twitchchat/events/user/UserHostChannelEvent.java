package de.lukweb.twitchchat.events.user;

import de.lukweb.twitchchat.TwitchUser;

public class UserHostChannelEvent extends UserEvent {

    public UserHostChannelEvent(TwitchUser user) {
        super(user);
    }
}
