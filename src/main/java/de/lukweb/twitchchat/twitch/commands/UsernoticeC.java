package de.lukweb.twitchchat.twitch.commands;

import de.lukweb.twitchchat.events.user.UserResubscribeEvent;
import de.lukweb.twitchchat.twitch.Command;
import de.lukweb.twitchchat.twitch.TurboChannel;
import de.lukweb.twitchchat.twitch.TurboChat;
import de.lukweb.twitchchat.twitch.TurboUser;

import java.util.Map;

public class UsernoticeC extends Command {

    public UsernoticeC() {
        super("USERNOTICE");
    }

    @Override
    public void handle(String sender, Map<String, String> tags, String[] arguments, TurboChat chat) {
        if (arguments.length < 1) return;
        if (!tags.containsKey("msg-id") || !tags.get("msg-id").equalsIgnoreCase("resub")) return;
        if (!tags.containsKey("msg-param-months") || !tags.containsKey("login")) return;

        TurboChannel channel = chat.getChannel(arguments[0].substring(1));
        TurboUser user = channel.createTurboChatter(tags.get("login"));
        int months = Integer.parseInt(tags.get("msg-param-months"));
        String systemMsg = tags.getOrDefault("system-msg", "");
        String userMsg = arguments.length > 1 ? arguments[1].substring(1) : "";

        UserResubscribeEvent event = new UserResubscribeEvent(user, months, systemMsg, userMsg);
        chat.getEventManager().callEvent(event);
    }
}
