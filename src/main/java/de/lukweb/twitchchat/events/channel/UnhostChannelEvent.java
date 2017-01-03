package de.lukweb.twitchchat.events.channel;

import de.lukweb.twitchchat.TwitchChannel;

public class UnhostChannelEvent extends ChannelEvent {

    private String target;
    private int hostViewers;

    public UnhostChannelEvent(TwitchChannel channel, String target, int hostViewers) {
        super(channel);
        this.target = target;
        this.hostViewers = hostViewers;
    }

    public String getTarget() {
        return target;
    }

    public int getHostViewers() {
        return hostViewers;
    }
}
