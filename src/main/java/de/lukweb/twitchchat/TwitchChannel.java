package de.lukweb.twitchchat;

import java.util.List;
import java.util.function.Consumer;

public interface TwitchChannel {

    /**
     * Gets the id of the room
     *
     * @return the id of the room
     */
    int getRoomId();

    /**
     * Gets the name of the channel.
     *
     * @return the name of the channel
     */
    String getName();

    /**
     * Sends a message to this channel.
     *
     * @param message the message will be sent
     */
    void sendMessage(String message);

    /**
     * This method loads all chatters in this channel using the Twitch Message Infrastructure.
     * It can be extremly useful for channels with more than 1000 chatters, because the names command recived during the
     * channel join doesn't contains always all users, in the case with more than 1000 chatters, there are only mods.
     * BUT: The Twitch Message Infrastructure endpoint isn't documented. This means it can be removed in the future.
     */
    void loadHTTPChatters();

    /**
     * This method loads all chatters in this channel using the Twitch Message Infrastructure.
     * It can be extremly useful for channels with more than 1000 chatters, because the names command recived during the
     * channel join doesn't contains always all users, in the case with more than 1000 chatters, there are only mods.
     * BUT: The Twitch Message Infrastructure endpoint isn't documented. This means it can be removed in the future.
     *
     * @param async whether the users should be fetched asynchronous
     */
    void loadHTTPChatters(boolean async);

    /**
     * This method loads all chatters in this channel using the Twitch Message Infrastructure.
     * It can be extremly useful for channels with more than 1000 chatters, because the names command recived during the
     * channel join doesn't contains always all users, in the case with more than 1000 chatters, there are only mods.
     * BUT: The Twitch Message Infrastructure endpoint isn't documented. This means it can be removed in the future.
     *
     * @param async   whether the users should be fetched asynchronous
     * @param success whether a error occured during the web request
     */
    void loadHTTPChatters(boolean async, Consumer<Boolean> success);

    /**
     * Gets the language definided by the broadcaster for the chat. The language may be an emtpy string if there the
     * broadcaster hasn't definided a language for the chat.
     *
     * @return language for the chat
     */
    String getLanguage();

    /**
     * Gets whether messages with more than 9 character must be unique.
     *
     * @return Gets whether messages with more than 9 character must be unique
     */
    boolean isUniqueMessages();

    /**
     * Gets whether only subscribers can send messages into the chat.
     *
     * @return whether only subscribers can send messages into the chat
     */
    boolean isSubsOnly();

    /**
     * Gets whether the slow mode is enabled. The slow mode definines time how long chatters without mod privileges
     * must wait between messages.
     *
     * @return whether the slow mode is enabled
     */
    boolean isSlowMode();

    /**
     * Gets whether chatters can only sends emotes.
     *
     * @return whether chatters can only sends emotes
     */
    boolean isEmoteOnly();

    /**
     * Gets the time users without mod privileges must wait between sending messages.
     *
     * @return the time users must wait between sending messages
     */
    int getSlowModeTime();

    /**
     * Gets a list of chatters in this channel. If there are more than 1000 users in this channel the list may
     * not be complete. For more information take a look at
     * <a href="https://github.com/justintv/Twitch-API/blob/master/IRC.md#mode">this</a>.
     *
     * @return list of chatters in this channel
     */
    List<TwitchUser> getChatters();

    /**
     * Gets a chatter by its name. If the chatter wasn't found in this channel this method will return null.
     *
     * @param name the name of the chatter
     * @return a chatter by its name
     */
    TwitchUser getChatter(String name);

    /**
     * Leaves this channel
     */
    void leave();
}
