package de.lukweb.twitchchat.events.channel;

import de.lukweb.twitchchat.TwitchChannel;

public class DisableSubsOnlyChatEvent extends ChannelEvent {

    public DisableSubsOnlyChatEvent(TwitchChannel channel) {
        super(channel);
    }
}
