package de.lukweb.twitchchat.events.channel;

import de.lukweb.twitchchat.TwitchChannel;

public class ChannelLanguageChangeEvent extends ChannelEvent {

    private String oldLanguage;

    public ChannelLanguageChangeEvent(TwitchChannel channel, String oldLanguage) {
        super(channel);
        this.oldLanguage = oldLanguage;
    }

    public String getOldLanguage() {
        return oldLanguage;
    }
}
