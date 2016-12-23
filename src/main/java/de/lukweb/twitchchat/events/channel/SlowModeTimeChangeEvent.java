package de.lukweb.twitchchat.events.channel;

import de.lukweb.twitchchat.TwitchChannel;

public class SlowModeTimeChangeEvent extends ChannelEvent {

    private int oldSlowModeTime;

    public SlowModeTimeChangeEvent(TwitchChannel channel, int oldSlowModeTime) {
        super(channel);
        this.oldSlowModeTime = oldSlowModeTime;
    }

    public int getOldSlowModeTime() {
        return oldSlowModeTime;
    }
}
