package de.lukweb.twitchchat.twitch;

import de.lukweb.twitchchat.TwitchChannel;

import java.util.ArrayList;
import java.util.List;

public class TurboChannel implements TwitchChannel {

    private String name;
    private TurboChat chat;
    private List<TurboUser> chatters = new ArrayList<>();
    // Attributes: https://github.com/justintv/Twitch-API/blob/master/IRC.md#roomstate-1
    private String language;
    private boolean uniqueMessages; // Messages with more than 9 characters must be unique
    private boolean subsOnly;
    private boolean slowMode;

    private String messagePrefix;

    TurboChannel(String name, TurboChat chat) {
        this.name = name;
        this.chat = chat;
        String username = chat.getUsername();
        messagePrefix = ":" + username + "!" + username + "@" + username + ".tmi.twitch.tv ";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void sendMessage(String message) {
        chat.sendRawMessage(messagePrefix + "PRIVMSG #" + name + " :" + message, false); // todo change 2. parameter
    }

    @Override
    public void sendWhisper(String to, String message) {
        sendMessage(".w " + to + " " + message);
    }

    @Override
    public void leave() {
        chat.leaveChannel(this);
    }
}
