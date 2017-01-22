package de.lukweb.twitchchat.twitch.commands;

import de.lukweb.twitchchat.events.chat.ChatReconnectEvent;
import de.lukweb.twitchchat.twitch.Command;
import de.lukweb.twitchchat.twitch.TurboChat;

import java.util.Map;

public class ReconnectC extends Command {

    public ReconnectC() {
        super("RECONNECT");
    }

    @Override
    public void handle(String sender, Map<String, String> tags, String[] arguments, TurboChat chat) {
        chat.getEventManager().callEvent(new ChatReconnectEvent());
        new Thread(chat::reconnect).start();
    }
}
