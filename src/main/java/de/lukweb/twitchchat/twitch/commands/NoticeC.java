package de.lukweb.twitchchat.twitch.commands;

import de.lukweb.twitchchat.twitch.Command;
import de.lukweb.twitchchat.twitch.TurboChat;

import java.util.Map;

public class NoticeC extends Command {

    public NoticeC() {
        super("NOTICE");
    }

    @Override
    public void handle(String sender, Map<String, String> tags, String[] arguments, TurboChat chat) {

    }
}
