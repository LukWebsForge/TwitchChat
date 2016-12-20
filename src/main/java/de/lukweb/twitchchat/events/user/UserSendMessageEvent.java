package de.lukweb.twitchchat.events.user;

import de.lukweb.twitchchat.TwitchUser;

public class UserSendMessageEvent extends UserEvent {

    public UserSendMessageEvent(TwitchUser user) {
        super(user);
    }
}
