package de.lukweb.twitchchat.events.user;

import de.lukweb.twitchchat.TwitchChannel;
import de.lukweb.twitchchat.TwitchUser;

public class UserJoinChannelEvent extends UserEvent {

    private TwitchChannel channel;

    public UserJoinChannelEvent(TwitchUser user, TwitchChannel channel) {
        super(user);
        this.channel = channel;
    }

    public TwitchChannel getChannel() {
        return channel;
    }
}
