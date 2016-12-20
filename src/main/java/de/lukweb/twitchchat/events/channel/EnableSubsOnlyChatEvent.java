package de.lukweb.twitchchat.events.channel;

import de.lukweb.twitchchat.TwitchChannel;

public class EnableSubsOnlyChatEvent extends ChannelEvent {

    public EnableSubsOnlyChatEvent(TwitchChannel channel) {
        super(channel);
    }
}
