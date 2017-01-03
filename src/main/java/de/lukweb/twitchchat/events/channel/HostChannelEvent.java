package de.lukweb.twitchchat.events.channel;

import de.lukweb.twitchchat.TwitchChannel;

public class HostChannelEvent extends ChannelEvent {

    private String target;
    private int hostViewers;

    public HostChannelEvent(TwitchChannel channel, String target, int hostViewers) {
        super(channel);
        this.target = target;
        this.hostViewers = hostViewers;
    }

    /**
     * Gets the channel started hosting a target channel with the name returned by {getTarget}
     *
     * @return the channel starts to host another channel
     */
    @Override
    public TwitchChannel getChannel() {
        return super.getChannel();
    }

    public String getTarget() {
        return target;
    }

    public int getHostViewers() {
        return hostViewers;
    }
}
