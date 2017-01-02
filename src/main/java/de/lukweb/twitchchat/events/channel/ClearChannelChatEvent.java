package de.lukweb.twitchchat.events.channel;

import de.lukweb.twitchchat.TwitchChannel;

public class ClearChannelChatEvent extends ChannelEvent {

    public ClearChannelChatEvent(TwitchChannel channel) {
        super(channel);
    }
}
