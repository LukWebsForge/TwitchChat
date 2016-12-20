package de.lukweb.twitchchat.events.channel;

import de.lukweb.twitchchat.TwitchChannel;

public class EnableSlowModeEvent extends ChannelEvent {

    public EnableSlowModeEvent(TwitchChannel channel) {
        super(channel);
    }
}
