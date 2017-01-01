package de.lukweb.twitchchat.twitch;

import de.lukweb.twitchchat.TwitchChannel;
import de.lukweb.twitchchat.TwitchRank;
import de.lukweb.twitchchat.TwitchUser;

/**
 * Implementation of {@link TwitchUser}
 */
public class TurboUser implements TwitchUser {

    private String name;
    private int userid;
    private TwitchChannel channel;

    private TwitchRank rank;
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
    private int donatedBits;

    public TurboUser(String name) {
        this.name = name;
    }

    @Override
    public int getUserId() {
        return 0;
    }

    public void setUserid(int userid) {
        this.userid = userid;
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
    public TwitchRank getRank() {
        return rank;
    }

    public void setRank(TwitchRank rank) {
        this.rank = rank;
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
        return rank == TwitchRank.CHANNEL_MOD;
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
    public int getDonatedBitAmount() {
        return donatedBits;
    }

    public void setDonatedBits(int donatedBits) {
        this.donatedBits = donatedBits;
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
