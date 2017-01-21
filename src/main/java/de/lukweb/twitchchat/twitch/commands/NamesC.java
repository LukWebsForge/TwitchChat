package de.lukweb.twitchchat.twitch.commands;

import de.lukweb.twitchchat.events.user.UserJoinChannelEvent;
import de.lukweb.twitchchat.twitch.Command;
import de.lukweb.twitchchat.twitch.TurboChannel;
import de.lukweb.twitchchat.twitch.TurboChat;
import de.lukweb.twitchchat.twitch.TurboUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class NamesC extends Command {

    public NamesC() {
        super("NAMES");
    }

    @Override
    public void handle(String sender, Map<String, String> tags, String[] arguments, TurboChat chat) {
        if (arguments.length < 4) return;
        TurboChannel channel = chat.getChannel(arguments[2].substring(1));
        List<String> chatters = new ArrayList<>();

        chatters.add(arguments[3].substring(1));
        chatters.addAll(Arrays.asList(arguments).subList(4, arguments.length));

        chatters.forEach(name -> {
            if (channel.getChatter(name) != null) return;
            TurboUser chatter = channel.createTurboChatter(name);
            chat.getEventManager().callEvent(new UserJoinChannelEvent(chatter, channel));
        });
    }
}
