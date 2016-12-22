package de.lukweb.twitchchat.twitch;

import de.lukweb.twitchchat.TwitchChannel;
import de.lukweb.twitchchat.TwitchUser;

public class TurboUser implements TwitchUser {

    private String name;
    private TwitchChannel channel;

    private boolean staff; // global twitch mods
    private boolean turbo;
    private boolean broadcaster;
    private boolean mod;
    private boolean subscriber;

    private int color;
    private String displayName;

    public TurboUser(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public TwitchChannel getChannel() {
        return channel;
    }

    @Override
    public boolean isStaff() {
        return staff;
    }

    @Override
    public boolean isTurbo() {
        return turbo;
    }

    @Override
    public boolean isBroadcaster() {
        return broadcaster;
    }

    @Override
    public boolean isMod() {
        return mod;
    }

    @Override
    public boolean isSubscriber() {
        return subscriber;
    }

    @Override
    public int getColor() {
        return color;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TurboUser that = (TurboUser) o;

        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
