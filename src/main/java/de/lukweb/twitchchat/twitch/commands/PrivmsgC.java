package de.lukweb.twitchchat.twitch.commands;

import de.lukweb.twitchchat.twitch.Command;
import de.lukweb.twitchchat.twitch.TurboChat;

import java.util.Map;

public class PrivmsgC extends Command {

    public PrivmsgC() {
        super("PRIVMSG");
    }

    @Override
    public void handle(String sender, Map<String, String> tags, String[] arguments, TurboChat chat) {

    }
}
