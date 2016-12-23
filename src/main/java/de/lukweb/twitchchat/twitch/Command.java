package de.lukweb.twitchchat.twitch;

import java.util.Map;

public abstract class Command {

    private String name;

    public Command(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void handle(String sender, Map<String, String> tags, String[] arguments, TurboChat chat);

    protected String getUsernameBySender(String username) {
        return username.split("[!]")[0].substring(1);
    }
}
