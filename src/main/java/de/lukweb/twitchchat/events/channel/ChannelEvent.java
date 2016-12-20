package de.lukweb.twitchchat.events.channel;

import de.lukweb.twitchchat.TwitchChannel;
import de.lukweb.twitchchat.events.Event;

public class ChannelEvent extends Event {

    private TwitchChannel channel;

    public ChannelEvent(TwitchChannel channel) {
        this.channel = channel;
    }

    public TwitchChannel getChannel() {
        return channel;
    }
}
