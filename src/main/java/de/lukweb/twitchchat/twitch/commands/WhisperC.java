package de.lukweb.twitchchat.twitch.commands;

import de.lukweb.twitchchat.events.user.UserSendWhisperEvent;
import de.lukweb.twitchchat.twitch.Command;
import de.lukweb.twitchchat.twitch.TurboChat;
import de.lukweb.twitchchat.twitch.messages.WhisperAttributes;

import java.util.Map;

public class WhisperC extends Command {

    public WhisperC() {
        super("WHISPER");
    }

    @Override
    public void handle(String sender, Map<String, String> tags, String[] arguments, TurboChat chat) {
        if (arguments.length < 2) return;
        String user = getUsernameBySender(sender);
        String message = arguments[1].substring(1);
        for (int i = 2; i < arguments.length; i++) message += " " + arguments[i];

        chat.getEventManager().callEvent(new UserSendWhisperEvent(user, message, new WhisperAttributes(tags)));
    }
}
