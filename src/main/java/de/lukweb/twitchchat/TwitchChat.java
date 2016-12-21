package de.lukweb.twitchchat;

import de.lukweb.twitchchat.events.EventManager;
import de.lukweb.twitchchat.events.irc.IrcReceiveMessageEvent;
import de.lukweb.twitchchat.irc.IrcClient;
import de.lukweb.twitchchat.irc.MessageDelayer;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.function.Consumer;

public class TwitchChat {

    private String username;
    private String oauthkey;

    private IrcClient irc;
    private EventManager eventManager;
    private MessageDelayer messageDelayer;
    private HashMap<String, TwitchChannel> channels = new HashMap<>();

    /**
     * Initalizes and starts the TwitchChatClient. To get an OAuth Password visit
     * <a href="https://twitchapps.com/tmi/">this</a> side.
     *
     * @param username The account's twitch username
     * @param oauthkey The OAuth Password of the account
     */
    public TwitchChat(String username, String oauthkey) {
        this.username = username.toLowerCase();
        this.oauthkey = oauthkey.startsWith("oauth:") ? oauthkey : "oauth:" + oauthkey;
        this.eventManager = new EventManager();
        connect();
    }

    private void connect() {
        try {
            irc = new IrcClient("irc.chat.twitch.tv", 443, true);
        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
            return;
        }
        this.messageDelayer = new MessageDelayer(this, irc);
        irc.setInputCallback(getInputHandler());
        irc.sendString("PASS " + oauthkey);
        irc.sendString("NICK " + username);
        irc.sendString("CAP REQ :twitch.tv/membership");
        irc.sendString("CAP REQ :twitch.tv/commands");
        irc.sendString("CAP REQ :twitch.tv/tags");
    }

    private Consumer<String> getInputHandler() {
        return msg -> {
            IrcReceiveMessageEvent event = new IrcReceiveMessageEvent(msg);
            eventManager.callEvent(event);
            if (event.isCanceled()) return;

            // todo add command handlers
        };
    }

    /**
     * Returns the chat of a Twitch channel. When the irc client is already connected to the channel, if not the irc
     * clients connects to the channel and returns it.
     *
     * @param channel a Twitch channel name (without the leading #)
     * @return a Twitch channel
     */
    public TwitchChannel getChannel(String channel) {
        channel = channel.toLowerCase();
        if (channels.containsKey(channel)) return channels.get(channel);
        return joinChannel(channel);
    }

    private TwitchChannel joinChannel(String channel) {
        irc.sendString("JOIN #" + channel);
        TwitchChannel twitchChannel = new TwitchChannel(channel, this);
        channels.put(channel, twitchChannel);
        return twitchChannel;
    }

    void leaveChannel(TwitchChannel channel) {
        irc.sendString("PART #" + channel.getName());
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
     * @param message  the message to be sent
     * @param operator whether the twitch client is a operator in this channel
     */
    public void sendRawMessage(String message, boolean operator) {
        messageDelayer.queue(message, operator);
    }

    /**
     * Closes the connection to all channels and the irc
     */
    public void close() {
        for (TwitchChannel channel : channels.values()) {
            channel.leave();
        }
        irc.close();
    }
}
