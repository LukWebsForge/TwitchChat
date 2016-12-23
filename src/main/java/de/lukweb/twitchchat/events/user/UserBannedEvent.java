package de.lukweb.twitchchat.events.user;

import de.lukweb.twitchchat.TwitchUser;

public class UserBannedEvent extends UserEvent {

    private String reason;

    public UserBannedEvent(TwitchUser user, String reason) {
        super(user);
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
