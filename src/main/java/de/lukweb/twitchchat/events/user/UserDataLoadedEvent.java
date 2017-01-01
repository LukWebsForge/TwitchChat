package de.lukweb.twitchchat.events.user;

import de.lukweb.twitchchat.TwitchUser;

public class UserDataLoadedEvent extends UserEvent {

    public UserDataLoadedEvent(TwitchUser user) {
        super(user);
    }
}
