package de.lukweb.twitchchat.twitch.commands;

import de.lukweb.twitchchat.twitch.Command;
import de.lukweb.twitchchat.twitch.TurboChannel;
import de.lukweb.twitchchat.twitch.TurboChat;
import de.lukweb.twitchchat.twitch.messages.StateAttributes;

import java.util.Map;

public class UserstateC extends Command {

    public UserstateC() {
        super("USERSTATE");
    }

    @Override
    public void handle(String sender, Map<String, String> tags, String[] arguments, TurboChat chat) {
        if (arguments.length < 1) return;

        TurboChannel channel = chat.getChannel(arguments[0].substring(1));
        StateAttributes attributes = new StateAttributes(tags);

        channel.getOwnChatter().update(attributes);
    }
}
