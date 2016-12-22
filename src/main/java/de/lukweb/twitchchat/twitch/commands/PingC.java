package de.lukweb.twitchchat.twitch.commands;

import de.lukweb.twitchchat.TwitchChat;
import de.lukweb.twitchchat.events.chat.ChatPingEvent;
import de.lukweb.twitchchat.twitch.Command;

import java.util.Map;

public class PingC extends Command {

    public PingC() {
        super("PING");
    }

    @Override
    public void handle(String channel, Map<String, String> tags, String[] arguments, TwitchChat chat) {
        ChatPingEvent event = new ChatPingEvent();
        chat.getEventManager().callEvent(event);

        chat.sendRawMessage("PONG " + arguments[0]);
    }
}
