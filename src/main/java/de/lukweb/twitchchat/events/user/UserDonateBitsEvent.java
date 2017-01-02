package de.lukweb.twitchchat.events.user;

import de.lukweb.twitchchat.TwitchUser;
import de.lukweb.twitchchat.twitch.messages.MessageAttributes;

public class UserDonateBitsEvent extends UserSendMessageEvent {

    private int bits;

    public UserDonateBitsEvent(TwitchUser user, String message, MessageAttributes attributes, int bits) {
        super(user, message, attributes);
        this.bits = bits;
    }

    public int getBits() {
        return bits;
    }
}
