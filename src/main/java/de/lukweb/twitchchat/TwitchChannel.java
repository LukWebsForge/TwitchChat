package de.lukweb.twitchchat;

public interface TwitchChannel {

    /**
     * Gets the name of the channel
     *
     * @return the name of the channel
     */
    String getName();

    /**
     * Sends a message to this channel
     *
     * @param message the message will be sent
     */
    void sendMessage(String message);

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
     * Leaves this channel
     */
    void leave();
}
