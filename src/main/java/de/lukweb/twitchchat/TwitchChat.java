package de.lukweb.twitchchat;

import de.lukweb.twitchchat.irc.IrcClient;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;

public class TwitchChat {

    private String username;
    private String oauthkey;

    private IrcClient irc;
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
        connect();
    }

    private void connect() {
        try {
            irc = new IrcClient("irc.chat.twitch.tv", 443, true);
        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
            return;
        }
        irc.sendString("PASS " + oauthkey);
        irc.sendString("NICK " + username);
        irc.sendString("CAP REQ :twitch.tv/membership");
        irc.sendString("CAP REQ :twitch.tv/commands");
        irc.sendString("CAP REQ :twitch.tv/tags");
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

    IrcClient getIrc() {
        return irc;
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
