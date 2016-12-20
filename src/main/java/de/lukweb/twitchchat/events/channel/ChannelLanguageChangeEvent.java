package de.lukweb.twitchchat.events.channel;

import de.lukweb.twitchchat.TwitchChannel;

public class ChannelLanguageChangeEvent extends ChannelEvent {

    public ChannelLanguageChangeEvent(TwitchChannel channel) {
        super(channel);
    }
}
