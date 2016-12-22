package de.lukweb.twitchchat;

import de.lukweb.twitchchat.events.EventManager;
import de.lukweb.twitchchat.irc.IrcClient;
import de.lukweb.twitchchat.irc.MessageDelayer;
import de.lukweb.twitchchat.twitch.TwitchInputHandler;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;

public class TwitchChat {

    private String username;
    private String oauthkey;

    private IrcClient irc;
    private EventManager eventManager;
    private MessageDelayer messageDelayer;
    private HashMap<String, TwitchChannel> channels = new HashMap<>();

    /**
     * Initalizes and starts the TwitchChatClient. To get an OAuth Password visit
     * <a href="https://twitchapps.com/tmi/">this</a> side. Call the method {@link TwitchChat#connect()} to open a
     * connection to the Twitch IRC.
     *
     * @param username The account's twitch username
     * @param oauthkey The OAuth Password of the account
     */
    public TwitchChat(String username, String oauthkey) {
        this.username = username.toLowerCase();
        this.oauthkey = oauthkey.startsWith("oauth:") ? oauthkey : "oauth:" + oauthkey;
        this.eventManager = new EventManager();
    }

    /**
     * (Re-)Connects the chat instance with the Twitch IRC.
     * WARNING: Messages in cache may be purged.
     */
    public void connect() {
        try {
            irc = new IrcClient("irc.chat.twitch.tv", 443, true);
        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
            return;
        }
        this.messageDelayer = new MessageDelayer(this, irc);
        irc.setInputHandler(new TwitchInputHandler(this));
        sendRawMessage("PASS " + oauthkey);
        sendRawMessage("NICK " + username);
        sendRawMessage("CAP REQ :twitch.tv/membership");
        sendRawMessage("CAP REQ :twitch.tv/commands");
        sendRawMessage("CAP REQ :twitch.tv/tags");
    }

    /**
     * Returns whether the client is connected to the Twitch server.
     *
     * @return whether connected to the Twitch server
     */
    public boolean isConnected() {
        return irc != null && irc.isConnected();
    }

    /**
     * Returns the chat of a Twitch channel. When the irc client is already connected to the channel, if not the irc
     * clients connects to the channel and returns it.
     *
     * @param channel a Twitch channel name (without the leading #)
     * @return a Twitch channel
     */
    public TwitchChannel getChannel(String channel) {
        if (!isConnected()) throw new IllegalStateException("The client isn't connected!");
        channel = channel.toLowerCase();
        if (channels.containsKey(channel)) return channels.get(channel);
        return joinChannel(channel);
    }

    private TwitchChannel joinChannel(String channel) {
        sendRawMessage("JOIN #" + channel);
        TwitchChannel twitchChannel = new TwitchChannel(channel, this);
        channels.put(channel, twitchChannel);
        return twitchChannel;
    }

    void leaveChannel(TwitchChannel channel) {
        sendRawMessage("PART #" + channel.getName());
        channels.remove(channel.getName());
    }

    /**
     * Gets the username of the connected user
     *
     * @return username of the connected user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the event manager for registering, unregistering and calling events.
     *
     * @return the event manager
     */
    public EventManager getEventManager() {
        return eventManager;
    }


    /**
     * Sends a raw message to the IRC. The message will be delayed if the
     * <a href="https://www.youtube.com/watch?v=0rNpHKSjIdQ">rate limit</a> was hit.
     *
     * @param message the message to be sent
     */
    public void sendRawMessage(String message) {
        messageDelayer.queue(message, false);
    }

    /**
     * Sends a raw message to the IRC. The message will be delayed if the
     * <a href="https://www.youtube.com/watch?v=0rNpHKSjIdQ">rate limit</a> was hit.
     *
     * @param message  the message to be sent
     * @param operator whether the twitch client is a operator in this channel
     */
    public void sendRawMessage(String message, boolean operator) {
        if (!isConnected()) throw new IllegalStateException("The client isn't connected!");
        messageDelayer.queue(message, operator);
    }

    /**
     * Closes the connection to all channels and the irc. Use the method {@link TwitchChat#connect()} to connect again.
     */
    public void close() {
        for (TwitchChannel channel : channels.values()) {
            channel.leave();
        }
        irc.close();
    }
}
