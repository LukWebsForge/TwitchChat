package de.lukweb.twitchchat.events.user;

import de.lukweb.twitchchat.TwitchUser;

public class UserDonatesBitsEvent extends UserEvent {

    public UserDonatesBitsEvent(TwitchUser user) {
        super(user);
    }
}
