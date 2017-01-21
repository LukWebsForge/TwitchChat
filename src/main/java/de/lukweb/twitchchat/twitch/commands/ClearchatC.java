package de.lukweb.twitchchat.twitch.commands;

import de.lukweb.twitchchat.events.channel.ClearChannelChatEvent;
import de.lukweb.twitchchat.events.user.UserBannedEvent;
import de.lukweb.twitchchat.events.user.UserTimeoutedEvent;
import de.lukweb.twitchchat.twitch.Command;
import de.lukweb.twitchchat.twitch.TurboChannel;
import de.lukweb.twitchchat.twitch.TurboChat;
import de.lukweb.twitchchat.twitch.TurboUser;

import java.util.Map;

public class ClearchatC extends Command {

    public ClearchatC() {
        super("CLEARCHAT");
    }

    @Override
    public void handle(String sender, Map<String, String> tags, String[] arguments, TurboChat chat) {
        if (arguments.length < 1) return;
        TurboChannel channel = chat.getChannel(arguments[0].substring(1));

        if (arguments.length == 1) {

            chat.getEventManager().callEvent(new ClearChannelChatEvent(channel));

        } else if (arguments.length >= 2) {

            TurboUser user = channel.createTurboChatter(arguments[1].substring(1));
            String reason = tags.get("ban-reason");

            if (tags.containsKey("ban-duration")) {
                long duration = Long.parseLong(tags.get("ban-duration"));
                user.setTimeouted(duration);
                chat.getEventManager().callEvent(new UserTimeoutedEvent(user, reason, duration));
            } else {
                user.setBanned(true);
                chat.getEventManager().callEvent(new UserBannedEvent(user, reason));
            }

        }
    }
}
