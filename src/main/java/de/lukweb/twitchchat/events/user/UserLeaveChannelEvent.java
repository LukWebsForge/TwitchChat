package de.lukweb.twitchchat.events.user;

import de.lukweb.twitchchat.TwitchUser;

public class UserLeaveChannelEvent extends UserEvent {

    public UserLeaveChannelEvent(TwitchUser user) {
        super(user);
    }
}
