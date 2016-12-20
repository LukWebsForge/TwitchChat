package de.lukweb.twitchchat.events.user;

import de.lukweb.twitchchat.TwitchUser;

public class UserJoinChannelEvent extends UserEvent {

    public UserJoinChannelEvent(TwitchUser user) {
        super(user);
    }
}
