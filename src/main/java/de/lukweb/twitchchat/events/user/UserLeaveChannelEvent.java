package de.lukweb.twitchchat.events.user;

import de.lukweb.twitchchat.TwitchChannel;
import de.lukweb.twitchchat.TwitchUser;

public class UserLeaveChannelEvent extends UserEvent {

    private TwitchChannel channel;

    public UserLeaveChannelEvent(TwitchUser user, TwitchChannel channel) {
        super(user);
        this.channel = channel;
    }

    public TwitchChannel getChannel() {
        return channel;
    }
}
