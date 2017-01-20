package de.lukweb.twitchchat.twitch.commands;

import de.lukweb.twitchchat.events.user.UserDonateBitsEvent;
import de.lukweb.twitchchat.events.user.UserSendMessageEvent;
import de.lukweb.twitchchat.twitch.Command;
import de.lukweb.twitchchat.twitch.TurboChannel;
import de.lukweb.twitchchat.twitch.TurboChat;
import de.lukweb.twitchchat.twitch.TurboUser;
import de.lukweb.twitchchat.twitch.messages.MessageAttributes;
import de.lukweb.twitchchat.twitch.utils.IntegerUtils;

import java.util.Map;

public class PrivmsgC extends Command {

    public PrivmsgC() {
        super("PRIVMSG");
    }

    @Override
    public void handle(String sender, Map<String, String> tags, String[] arguments, TurboChat chat) {
        if (arguments.length < 2) return;

        MessageAttributes attributes = new MessageAttributes(tags);

        TurboChannel channel = chat.getChannel(arguments[0].substring(1));
        TurboUser user = channel.createTurboChatter(getUsernameBySender(sender));
        String message = arguments[1].substring(1);
        for (int i = 2; i < arguments.length; i++) message += " " + arguments[i];

        user.update(attributes);

        if (tags.containsKey("room-id") && IntegerUtils.isInt(tags.get("room-id"))) {
            channel.setRoomId(Integer.parseInt(tags.get("room-id")));
        }

        boolean emoteMessage = message.charAt(0) == 1;
        if (emoteMessage) message = message.substring(1, message.length() - 1);

        UserSendMessageEvent event;
        if (tags.containsKey("bits")) {
            event = new UserDonateBitsEvent(user, message, attributes, Integer.parseInt(tags.get("bits")));
        } else {
            event = new UserSendMessageEvent(user, message, attributes, emoteMessage);
        }

        chat.getEventManager().callEvent(event);
    }
}
