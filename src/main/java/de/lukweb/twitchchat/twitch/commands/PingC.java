package de.lukweb.twitchchat.twitch.commands;

import de.lukweb.twitchchat.events.chat.ChatPingEvent;
import de.lukweb.twitchchat.twitch.Command;
import de.lukweb.twitchchat.twitch.TurboChat;

import java.util.Map;

public class PingC extends Command {

    public PingC() {
        super("PING");
    }

    @Override
    public void handle(String sender, Map<String, String> tags, String[] arguments, TurboChat chat) {
        chat.sendRawMessage("PONG " + arguments[0]);
        chat.getEventManager().callEvent(new ChatPingEvent());
    }
}
