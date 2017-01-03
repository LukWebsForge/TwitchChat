package de.lukweb.twitchchat.events.chat;

import de.lukweb.twitchchat.TwitchRank;
import de.lukweb.twitchchat.events.Event;
import de.lukweb.twitchchat.twitch.messages.StateAttributes;

import java.util.Collections;
import java.util.List;

public class ChatClientDataLoadedEvent extends Event {

    private StateAttributes attributes;

    public ChatClientDataLoadedEvent(StateAttributes attributes) {
        this.attributes = attributes;
    }

    public String getColor() {
        return attributes.getColor();
    }

    public String getDisplayName() {
        return attributes.getDisplayName();
    }

    public List<Integer> getEmoteSets() {
        return Collections.unmodifiableList(attributes.getEmoteSets());
    }

    public boolean isTurbo() {
        return attributes.isTurbo();
    }

    public boolean isPrime() {
        return attributes.isPrime();
    }

    public int getUserId() {
        return attributes.getUserId();
    }

    public TwitchRank getRank() {
        return attributes.getRank();
    }
}
