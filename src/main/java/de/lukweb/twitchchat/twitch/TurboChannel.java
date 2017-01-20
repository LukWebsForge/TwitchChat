package de.lukweb.twitchchat.twitch;

import de.lukweb.twitchchat.TwitchChannel;
import de.lukweb.twitchchat.TwitchUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of the {@link TwitchChannel}
 */
public class TurboChannel implements TwitchChannel {

    private int roomid;
    private String name;
    private TurboChat chat;
    private List<TurboUser> chatters;

    // Attributes: https://github.com/justintv/Twitch-API/blob/master/IRC.md#roomstate-1
    private String language;
    private boolean uniqueMessages; // Messages with more than 9 characters must be unique
    private boolean subsOnly;
    private int slowMode;
    private boolean emoteOnly;

    private String hosting;

    private String messagePrefix;

    TurboChannel(String name, TurboChat chat) {
        this.name = name;
        this.chat = chat;
        this.chatters = new ArrayList<>();
        this.language = "";
        String username = chat.getUsername();
        messagePrefix = ":" + username + "!" + username + "@" + username + ".tmi.twitch.tv ";
    }

    public TurboChat getChat() {
        return chat;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getRoomId() {
        return roomid;
    }

    public void setRoomId(int roomid) {
        this.roomid = roomid;
    }

    @Override
    public void sendMessage(String message) {
        chat.sendRawMessage(messagePrefix + "PRIVMSG #" + name + " :" + message, false); // todo change 2. parameter
    }

    public TurboUser getOwnChatter() {
        return createTurboChatter(chat.getUsername());
    }

    @Override
    public List<TwitchUser> getChatters() {
        return Collections.unmodifiableList(chatters);
    }

    public List<TurboUser> getTurboChatters() {
        return Collections.unmodifiableList(chatters);
    }

    @Override
    public TurboUser getChatter(String name) {
        for (TurboUser chatter : chatters) if (chatter.getName().equalsIgnoreCase(name)) return chatter;
        return null;
    }

    public TurboUser createTurboChatter(String name) {
        name = name.toLowerCase();
        TurboUser user = getChatter(name);
        if (user != null) return user;
        user = new TurboUser(name, this);
        chatters.add(user);
        return user;
    }

    public void removeTurboChatter(TurboUser user) {
        chatters.remove(user);
    }

    @Override
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public boolean isUniqueMessages() {
        return uniqueMessages;
    }

    public void setUniqueMessages(boolean uniqueMessages) {
        this.uniqueMessages = uniqueMessages;
    }

    @Override
    public boolean isSubsOnly() {
        return subsOnly;
    }

    public void setSubsOnly(boolean subsOnly) {
        this.subsOnly = subsOnly;
    }

    @Override
    public boolean isSlowMode() {
        return slowMode > 0;
    }

    @Override
    public int getSlowModeTime() {
        return slowMode;
    }

    public void setSlowMode(int slowMode) {
        this.slowMode = slowMode;
    }

    @Override
    public boolean isEmoteOnly() {
        return emoteOnly;
    }

    public void setEmoteOnly(boolean emoteOnly) {
        this.emoteOnly = emoteOnly;
    }

    @Override
    public void sendWhisper(String to, String message) {
        sendMessage(".w " + to + " " + message);
    }

    public String getHosting() {
        return hosting;
    }

    public void setHosting(String hosting) {
        this.hosting = hosting;
    }

    @Override
    public void leave() {
        chat.leaveChannel(this);
    }
}
