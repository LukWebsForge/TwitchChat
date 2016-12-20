package de.lukweb.twitchchat.events.channel;

import de.lukweb.twitchchat.TwitchChannel;

public class DisableSlowModeEvent extends ChannelEvent {

    public DisableSlowModeEvent(TwitchChannel channel) {
        super(channel);
    }
}
