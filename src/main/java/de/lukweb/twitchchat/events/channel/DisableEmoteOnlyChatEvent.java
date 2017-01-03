package de.lukweb.twitchchat.events.channel;

import de.lukweb.twitchchat.TwitchChannel;

public class DisableEmoteOnlyChatEvent extends ChannelEvent {

    public DisableEmoteOnlyChatEvent(TwitchChannel channel) {
        super(channel);
    }
}
