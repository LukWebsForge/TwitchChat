package de.lukweb.twitchchat.events.user;

import de.lukweb.twitchchat.TwitchUser;

public class UserLostOperatorEvent extends UserEvent {

    public UserLostOperatorEvent(TwitchUser user) {
        super(user);
    }
}
