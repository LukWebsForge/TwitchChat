package de.lukweb.twitchchat.events.user;

import de.lukweb.twitchchat.TwitchUser;

public class UserGainOperatorEvent extends UserEvent {

    public UserGainOperatorEvent(TwitchUser user) {
        super(user);
    }
}
