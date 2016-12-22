package de.lukweb.twitchchat.twitch.commands;

import de.lukweb.twitchchat.TwitchChat;
import de.lukweb.twitchchat.twitch.Command;

import java.util.Map;

public class NoticeC extends Command {

    public NoticeC() {
        super("NOTICE");
    }

    @Override
    public void handle(String channel, Map<String, String> tags, String[] arguments, TwitchChat chat) {

    }
}
