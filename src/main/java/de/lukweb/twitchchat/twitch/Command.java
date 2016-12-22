package de.lukweb.twitchchat.twitch;

import de.lukweb.twitchchat.TwitchChat;

import java.util.Map;

public abstract class Command {

    private String name;

    public Command(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void handle(String channel, Map<String, String> tags, String[] arguments, TwitchChat chat);
}
