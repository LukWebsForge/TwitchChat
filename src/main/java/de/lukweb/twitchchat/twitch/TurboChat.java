package de.lukweb.twitchchat.twitch;

import de.lukweb.twitchchat.TwitchChannel;
import de.lukweb.twitchchat.TwitchChat;
import de.lukweb.twitchchat.events.EventManager;
import de.lukweb.twitchchat.irc.IrcClient;
import de.lukweb.twitchchat.irc.MessageDelayer;
import de.lukweb.twitchchat.irc.TurboIrcClient;
import de.lukweb.twitchchat.irc.TurboMessageDelayer;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Implementation of {@link TwitchChat}
 */
public class TurboChat implements TwitchChat {

    private String username;
    private String oauthkey;

    private IrcClient irc;
    private EventManager eventManager;
    private MessageDelayer messageDelayer;
    private List<String> capabilities = new ArrayList<>();
    private HashMap<String, TurboChannel> channels = new HashMap<>();

    public TurboChat(String username, String oauthkey) {
        this.username = username.toLowerCase();
        this.oauthkey = oauthkey.startsWith("oauth:") ? oauthkey : "oauth:" + oauthkey;
        this.eventManager = new EventManager();
    }

    @Override
    public void connect() {
        try {
            TurboIrcClient irc = new TurboIrcClient("irc.chat.twitch.tv", 443, true);
            connect(irc, new TurboMessageDelayer(this, irc));
        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

    void connect(IrcClient client, MessageDelayer messageDelayer) {
        this.irc = client;
        this.messageDelayer = messageDelayer;
        irc.setInputHandler(new TwitchInputHandler(this));
        sendRawMessage("CAP REQ :twitch.tv/membership");
        sendRawMessage("CAP REQ :twitch.tv/commands");
        sendRawMessage("CAP REQ :twitch.tv/tags");
        sendRawMessage("PASS " + oauthkey);
        sendRawMessage("NICK " + username);
    }

    @Override
    public boolean isConnected() {
        return irc != null && irc.isConnected();
    }

    @Override
    public TurboChannel getChannel(String channel) {
        if (!isConnected()) throw new IllegalStateException("The client isn't connected!");
        channel = channel.toLowerCase();
        if (channel.startsWith("#")) channel = channel.substring(1);
        if (channels.containsKey(channel)) return channels.get(channel);
        return joinChannel(channel);
    }

    private TurboChannel joinChannel(String channel) {
        sendRawMessage("JOIN #" + channel);
        TurboChannel twitchChannel = new TurboChannel(channel, this);
        channels.put(channel, twitchChannel);
        return twitchChannel;
    }

    void leaveChannel(TurboChannel channel) {
        sendRawMessage("PART #" + channel.getName());
        channels.remove(channel.getName());
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public EventManager getEventManager() {
        return eventManager;
    }


    @Override
    public void sendRawMessage(String message) {
        messageDelayer.queue(message, false);
    }

    @Override
    public void sendRawMessage(String message, boolean operator) {
        if (!isConnected()) throw new IllegalStateException("The client isn't connected!");
        messageDelayer.queue(message, operator);
    }

    public void addCapability(String capability) {
        capabilities.add(capability);
    }

    @Override
    public void close() {
        for (TwitchChannel channel : channels.values()) {
            channel.leave();
        }
        irc.close();
    }
}
