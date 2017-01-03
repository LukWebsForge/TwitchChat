package de.lukweb.twitchchat.twitch.commands;

import de.lukweb.twitchchat.events.chat.ChatClientDataLoadedEvent;
import de.lukweb.twitchchat.twitch.Command;
import de.lukweb.twitchchat.twitch.TurboChat;
import de.lukweb.twitchchat.twitch.messages.StateAttributes;

import java.util.Map;

public class GlobaluserstateC extends Command {

    public GlobaluserstateC() {
        super("GLOBALUSERSTATE");
    }

    @Override
    public void handle(String sender, Map<String, String> tags, String[] arguments, TurboChat chat) {
        StateAttributes attributes = new StateAttributes(tags);
        chat.getEventManager().callEvent(new ChatClientDataLoadedEvent(attributes));
    }
}
