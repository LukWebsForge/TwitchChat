package de.lukweb.twitchchat.events.user;

import de.lukweb.twitchchat.TwitchUser;

public class UserLoseOperatorEvent extends UserEvent {

    public UserLoseOperatorEvent(TwitchUser user) {
        super(user);
    }
}
