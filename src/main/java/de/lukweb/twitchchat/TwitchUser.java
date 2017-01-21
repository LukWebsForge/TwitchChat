package de.lukweb.twitchchat;

/**
 * A TwitchUser is specific for every {@link TwitchChannel}.
 */
public interface TwitchUser {

    /**
     * Gets the id of the user
     *
     * @return thie id of the user
     */
    int getUserId();

    /**
     * Gets the name (lowercase) of the twitch user.
     *
     * @return name of the twitch user
     */
    String getName();

    /**
     * Gets the display name shown to other twitch users of the twitch user.
     *
     * @return name shown to other twitch users
     */
    String getDisplayName();

    /**
     * Gets the channel of the user.
     *
     * @return channel of the user
     */
    TwitchChannel getChannel();

    /**
     * Gets the rank of a user
     *
     * @return the rank of a user
     */
    TwitchRank getRank();

    /**
     * Gets whether this user owns a twitch turbo subscription.
     *
     * @return whether this user owns a twitch turbo subscription
     */
    boolean isTurbo();

    /**
     * Gets whether this this user owns a twitch prime subscription.
     *
     * @return whether this this user owns a twitch prime subscription
     */
    boolean isPrime();

    /**
     * Gets whether this user is the brodcaster.
     *
     * @return whether this user is the brodcaster
     */
    boolean isBroadcaster();

    /**
     * Gets whether this user has mod rights in this channel.
     *
     * @return whether this user has mod rights in this channel
     */
    boolean isMod();

    /**
     * Gets whether this user has subscribed this channel.
     *
     * @return whether this user has subscribed this channel
     */
    boolean isSubscriber();

    /**
     * Gets the color used for displaying the name in the chat.
     *
     * @return color used for displaying the name
     */
    String getColor();

    /**
     * Gets whether the user is banned in this channel. Bans on Twitch are usually infinite.
     *
     * @return whether the user is banned
     */
    boolean isBanned();

    /**
     * Gets whether the user is timeouted in this channel. You can get the date until the end of the timeout using
     * the {@link TwitchUser#getTimeoutUntil()} method.
     *
     * @return whether the user is timeouted
     */
    boolean isTimeouted();

    /**
     * Gets the date until the user is timeouted / banned from this channel. The date is returned as unix timestamp.
     *
     * @return the date until the user is banned
     */
    long getTimeoutUntil();

    /**
     * Gets the amount of bits the user donated for this channel
     *
     * @return the amount of bits the user donated for this channel
     */
    int getDonatedBitAmount();

    /**
     * Sends a whipser message to this user. There should be a delay of 350 ms between whispers, because the
     * {@link de.lukweb.twitchchat.irc.MessageDelayer} currently not covers this.
     *
     * @param message the message to be sent
     */
    void sendWhisper(String message);
}
