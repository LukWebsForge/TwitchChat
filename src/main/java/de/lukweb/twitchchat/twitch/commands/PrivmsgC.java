package de.lukweb.twitchchat.twitch.commands;

import de.lukweb.twitchchat.events.user.UserDonateBitsEvent;
import de.lukweb.twitchchat.events.user.UserSendMessageEvent;
import de.lukweb.twitchchat.twitch.Command;
import de.lukweb.twitchchat.twitch.TurboChat;
import de.lukweb.twitchchat.twitch.TurboUser;
import de.lukweb.twitchchat.twitch.messages.MessageAttributes;

import java.util.Map;

public class PrivmsgC extends Command {

    public PrivmsgC() {
        super("PRIVMSG");
    }

    @Override
    public void handle(String sender, Map<String, String> tags, String[] arguments, TurboChat chat) {
        if (arguments.length < 2) return;

        MessageAttributes attributes = new MessageAttributes(tags);

        TurboUser user = chat.getChannel(arguments[0].substring(1)).createTurboChatter(getUsernameBySender(sender));
        String message = arguments[1].substring(1);
        for (int i = 2; i < arguments.length; i++) message += " " + arguments[i];

        user.update(attributes);

        UserSendMessageEvent event;
        if (tags.containsKey("bits")) {
            try {
                event = new UserDonateBitsEvent(user, message, attributes, Integer.parseInt(tags.get("bits")));
            } catch (NumberFormatException ignored) {
                return;
            }
        } else {
            event = new UserSendMessageEvent(user, message, attributes);
        }

        chat.getEventManager().callEvent(event);
    }
}
