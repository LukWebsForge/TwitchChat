package de.lukweb.twitchchat.events.user;

import de.lukweb.twitchchat.TwitchUser;
import de.lukweb.twitchchat.events.Event;

public class UserEvent extends Event {

    private TwitchUser user;

    public UserEvent(TwitchUser user) {
        this.user = user;
    }

    public TwitchUser getUser() {
        return user;
    }
}
