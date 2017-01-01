package de.lukweb.twitchchat.twitch.commands;

import de.lukweb.twitchchat.twitch.Command;
import de.lukweb.twitchchat.twitch.TurboChat;
import de.lukweb.twitchchat.twitch.messages.MessageAttributes;

import java.util.Map;

public class GlobaluserstateC extends Command {

    public GlobaluserstateC() {
        super("GLOBALUSERSTATE");
    }

    @Override
    public void handle(String sender, Map<String, String> tags, String[] arguments, TurboChat chat) {
        MessageAttributes attributes = new MessageAttributes(tags);
    }
}
