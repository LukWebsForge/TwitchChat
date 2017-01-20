package de.lukweb.twitchchat.twitch;

import de.lukweb.twitchchat.TwitchChannel;
import de.lukweb.twitchchat.TwitchRank;
import de.lukweb.twitchchat.TwitchUser;
import de.lukweb.twitchchat.events.user.UserDataUpdateEvent;
import de.lukweb.twitchchat.twitch.messages.MessageAttributes;

/**
 * Implementation of {@link TwitchUser}
 */
public class TurboUser implements TwitchUser {

    private String name;
    private int userid;
    private TurboChannel channel;

    private TwitchRank rank;
    private boolean broadcaster;
    private boolean prime;
    private boolean turbo;
    private boolean mod;
    private boolean subscriber;
    private boolean operator;

    private boolean banned;
    private int timeouted;

    private String color;
    private String displayName;
    private int donatedBits;

    public TurboUser(String name, TurboChannel channel) {
        this.name = name;
        this.channel = channel;
    }

    @Override
    public int getUserId() {
        return userid;
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

    @Override
    public boolean isTurbo() {
        return turbo;
    }

    @Override
    public boolean isPrime() {
        return prime;
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
    public String getColor() {
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

    public void update(MessageAttributes attributes) {

        // We call the event if the attributes are different
        if (!attributes.compareTo(this))
            channel.getChat().getEventManager().callEvent(new UserDataUpdateEvent(this, attributes));

        if (attributes.getUserId() > 0) userid = attributes.getUserId();
        if (attributes.getDisplayName() != null) displayName = attributes.getDisplayName();

        rank = attributes.getRank();
        broadcaster = attributes.isBroadcaster();
        turbo = attributes.isTurbo();
        prime = attributes.isPrime();
        mod = attributes.isMod();
        subscriber = attributes.isSubscriber();

        color = attributes.getColor();
        donatedBits = attributes.getAllBits();
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
