package de.lukweb.twitchchat.events.user;

import de.lukweb.twitchchat.TwitchUser;

public class UserTimeoutedEvent extends UserEvent {

    private String reason;
    private long duration;

    public UserTimeoutedEvent(TwitchUser user, String reason, long duration) {
        super(user);
        this.reason = reason;
        this.duration = duration;
    }

    public String getReason() {
        return reason;
    }

    public long getDuration() {
        return duration;
    }
}
