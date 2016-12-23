package de.lukweb.twitchchat.twitch.commands;

import de.lukweb.twitchchat.events.user.UserJoinChannelEvent;
import de.lukweb.twitchchat.twitch.Command;
import de.lukweb.twitchchat.twitch.TurboChannel;
import de.lukweb.twitchchat.twitch.TurboChat;
import de.lukweb.twitchchat.twitch.TurboUser;

import java.util.Map;

public class JoinC extends Command {

    public JoinC() {
        super("JOIN");
    }

    @Override
    public void handle(String sender, Map<String, String> tags, String[] arguments, TurboChat chat) {
        if (arguments.length < 1) return;

        String username = getUsernameBySender(sender);
        String channel = arguments[0].substring(1);

        TurboChannel turboChannel = chat.getChannel(channel);
        TurboUser turboUser = turboChannel.createTurboChatter(username);

        chat.getEventManager().callEvent(new UserJoinChannelEvent(turboUser, turboChannel));
    }
}
