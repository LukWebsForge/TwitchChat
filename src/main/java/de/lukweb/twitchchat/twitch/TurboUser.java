package de.lukweb.twitchchat.twitch;

import de.lukweb.twitchchat.TwitchChannel;
import de.lukweb.twitchchat.TwitchUser;

/**
 * Implementation of {@link TwitchUser}
 */
public class TurboUser implements TwitchUser {

    private String name;
    private int userid;
    private TwitchChannel channel;

    private boolean staff; // global twitch mod
    private boolean turbo;
    private boolean broadcaster;
    private boolean mod;
    private boolean subscriber;
    private boolean operator;

    private boolean banned;
    private int timeouted;

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

    public void setOperator(boolean operator) {
        this.operator = operator;
    }

    @Override
    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    @Override
    public boolean isTimeouted() {
        return timeouted > (System.currentTimeMillis() / 1000);
    }

    @Override
    public int getTimeoutUntil() {
        return timeouted;
    }

    public void setTimeouted(int duration) {
        timeouted = (int) ((System.currentTimeMillis() / 1000) + duration);
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
