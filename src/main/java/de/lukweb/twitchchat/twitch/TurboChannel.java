package de.lukweb.twitchchat.twitch;

import de.lukweb.twitchchat.TwitchChannel;
import de.lukweb.twitchchat.TwitchRank;
import de.lukweb.twitchchat.TwitchUser;
import de.lukweb.twitchchat.events.user.UserJoinChannelEvent;
import de.lukweb.twitchchat.twitch.utils.EmptyConsumer;
import de.lukweb.twitchchat.twitch.utils.HttpUtils;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

/**
 * Implementation of the {@link TwitchChannel}
 */
public class TurboChannel implements TwitchChannel {

    private int roomid;
    private String name;
    private TurboChat chat;
    private List<TurboUser> chatters;
    private HashMap<String, Consumer<TwitchUser>> availableCallbacks;

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
        this.availableCallbacks = new HashMap<>();
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

    @Override
    public void getChatterWhenAvailable(String name, Consumer<TwitchUser> userConsumer) {
        if (getChatter(name) != null) {
            userConsumer.accept(getChatter(name));
            return;
        }
        availableCallbacks.put(name.toLowerCase(), userConsumer);
    }

    public TurboUser createTurboChatter(String name) {
        name = name.toLowerCase();
        TurboUser user = getChatter(name);
        if (user != null) return user;
        user = new TurboUser(name, this);
        chatters.add(user);
        availableCallbacks.getOrDefault(name.toLowerCase(), new EmptyConsumer<>()).accept(user);
        return user;
    }

    public void removeTurboChatter(TurboUser user) {
        chatters.remove(user);
    }

    @Override
    public void loadHTTPChatters() {
        loadHTTPChatters(true);
    }

    @Override
    public void loadHTTPChatters(boolean async) {
        loadHTTPChatters(async, success -> {
        });
    }

    @Override
    public void loadHTTPChatters(boolean async, Consumer<Boolean> success) {
        if (async) {
            new Thread(() -> success.accept(readHTTPChatters())).start();
        } else {
            success.accept(readHTTPChatters());
        }
    }

    private boolean readHTTPChatters() {
        String answer = HttpUtils.get("https://tmi.twitch.tv/group/user/" + name + "/chatters");
        if (answer == null) return false;
        JSONObject json = new JSONObject(answer);
        JSONObject chatters = json.getJSONObject("chatters");

        chatters.getJSONArray("moderators").forEach(name -> addHTTPChatter((String) name, TwitchRank.CHANNEL_MOD));
        chatters.getJSONArray("staff").forEach(name -> addHTTPChatter((String) name, TwitchRank.STAFF));
        chatters.getJSONArray("admins").forEach(name -> addHTTPChatter((String) name, TwitchRank.ADMIN));
        chatters.getJSONArray("global_mods").forEach(name -> addHTTPChatter((String) name, TwitchRank.GLOBAL_MOD));
        chatters.getJSONArray("viewers").forEach(name -> addHTTPChatter((String) name, TwitchRank.NONE));

        return true;
    }

    private void addHTTPChatter(String name, TwitchRank rank) {
        TurboUser chatter = createTurboChatter(name);
        chatter.setRank(rank);
        chat.getEventManager().callEvent(new UserJoinChannelEvent(chatter, this));
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
