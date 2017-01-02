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

    boolean isBanned();

    boolean isTimeouted();

    int getTimeoutUntil();

    int getDonatedBitAmount();
}
