package de.lukweb.twitchchat.twitch.commands;

import de.lukweb.twitchchat.TwitchChannel;
import de.lukweb.twitchchat.events.channel.NoticeEvent;
import de.lukweb.twitchchat.twitch.Command;
import de.lukweb.twitchchat.twitch.TurboChat;

import java.util.Arrays;
import java.util.Map;

public class NoticeC extends Command {

    public NoticeC() {
        super("NOTICE");
    }

    private final String[] LOGIN_FAILED = new String[]{"*", ":Login", "authentication", "failed"};

    @Override
    public void handle(String sender, Map<String, String> tags, String[] arguments, TurboChat chat) {
        if (Arrays.equals(arguments, LOGIN_FAILED)) {
            chat.closeWrongCredentials();
            return;
        }
        if (!tags.containsKey("msg-id")) return;
        if (arguments.length < 2) return;

        String msgId = tags.get("msg-id");
        TwitchChannel channel = chat.getChannel(arguments[0].substring(1));
        String message = arguments[1].substring(1);
        for (int i = 2; i < arguments.length; i++) message += " " + arguments[i];

        chat.getEventManager().callEvent(new NoticeEvent(channel, msgId, message));
    }
}
