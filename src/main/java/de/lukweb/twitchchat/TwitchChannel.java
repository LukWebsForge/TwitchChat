package de.lukweb.twitchchat;

import java.util.List;

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
     * Gets the time users without mod privileges must wait between sending messages.
     *
     * @return the time users must wait between sending messages
     */
    int getSlowModeTime();

    /**
     * Sends a whipser message to a user in this channel. The user must be in THIS channel. There should be a delay
     * of 350 ms between whispers, because the {@link de.lukweb.twitchchat.irc.MessageDelayer} currently not covers
     * this.
     *
     * @param to      user who will recive this message
     * @param message the message
     */
    void sendWhisper(String to, String message);

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
     * @return a chatter by its name
     */
    TwitchUser getChatter(String name);

    /**
     * Leaves this channel
     */
    void leave();
}
