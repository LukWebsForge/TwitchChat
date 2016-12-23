package de.lukweb.twitchchat.events.user;

import de.lukweb.twitchchat.TwitchUser;

public class UserDonateBitsEvent extends UserEvent {

    public UserDonateBitsEvent(TwitchUser user) {
        super(user);
    }
}
