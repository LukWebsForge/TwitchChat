package de.lukweb.twitchchat;

import de.lukweb.twitchchat.events.EventManager;
import de.lukweb.twitchchat.twitch.TurboChat;

import java.util.function.Consumer;

public interface TwitchChat {

    /**
     * (Re-)Connects the chat instance with the Twitch IRC.
     * WARNING: Messages in cache may be purged.
     *
     * @return whether the connection attempt was successful
     */
    boolean connect();

    /**
     * Returns whether the client is connected to the Twitch server.
     *
     * @return whether connected to the Twitch server
     */
    boolean isConnected();

    /**
     * Returns the chat of a Twitch channel. When the irc client is already connected to the channel, if not the irc
     * clients connects to the channel and returns it.
     *
     * @param channel a Twitch channel name (without the leading #)
     * @return a Twitch channel
     */
    TwitchChannel getChannel(String channel);

    /**
     * Gets the username of the connected user
     *
     * @return username of the connected user
     */
    String getUsername();

    /**
     * Gets the event manager for registering, unregistering and calling events.
     *
     * @return the event manager
     */
    EventManager getEventManager();

    /**
     * Sends a raw message to the IRC. The message will be delayed if the
     * <a href="https://www.youtube.com/watch?v=0rNpHKSjIdQ">rate limit</a> was hit.
     *
     * @param message the message to be sent
     */
    void sendRawMessage(String message);

    /**
     * Sends a raw message to the IRC. The message will be delayed if the
     * <a href="https://www.youtube.com/watch?v=0rNpHKSjIdQ">rate limit</a> was hit.
     *
     * @param message  the message to be sent
     * @param operator whether the twitch client is a operator in this channel
     */
    void sendRawMessage(String message, boolean operator);

    /**
     * Sets a callback for handling warnings. Set it to null to set it to the default value.
     *
     * @param callback the callback for handling warnings
     */
    void setWarningOutput(Consumer<String> callback);

    /**
     * Closes the connection to all channels and the irc. Use the method {@link TwitchChat#connect()} to connect again.
     */
    void close();

    /**
     * Initalizes the TwitchChatClient. To get an OAuth Password visit <a href="https://twitchapps.com/tmi/">this</a>
     * site. Call the method {@link TurboChat#connect()} to open a connection to the Twitch IRC.
     *
     * @param username The account's twitch username
     * @param oauthkey The OAuth Password of the account
     * @return A Twitch chat client
     */
    static TwitchChat build(String username, String oauthkey) {
        return new TurboChat(username, oauthkey);
    }
}
