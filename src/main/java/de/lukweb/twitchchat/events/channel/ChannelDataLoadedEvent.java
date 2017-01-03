package de.lukweb.twitchchat.events.channel;

import de.lukweb.twitchchat.TwitchChannel;

public class ChannelDataLoadedEvent extends ChannelEvent {

    public ChannelDataLoadedEvent(TwitchChannel channel) {
        super(channel);
    }
}
