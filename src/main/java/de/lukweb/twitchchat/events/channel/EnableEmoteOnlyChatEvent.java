package de.lukweb.twitchchat.events.channel;

import de.lukweb.twitchchat.TwitchChannel;

public class EnableEmoteOnlyChatEvent extends ChannelEvent {

    public EnableEmoteOnlyChatEvent(TwitchChannel channel) {
        super(channel);
    }
}
