package de.lukweb.twitchchat.events.user;

import de.lukweb.twitchchat.TwitchUser;

public class UserResubscribeEvent extends UserEvent {

    public UserResubscribeEvent(TwitchUser user) {
        super(user);
    }
}
