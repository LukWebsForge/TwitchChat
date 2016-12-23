package de.lukweb.twitchchat.twitch.commands;

import de.lukweb.twitchchat.events.irc.IrcGainCapabilityEvent;
import de.lukweb.twitchchat.twitch.Command;
import de.lukweb.twitchchat.twitch.TurboChat;

import java.util.Map;

public class CapC extends Command {

    public CapC() {
        super("CAP");
    }

    @Override
    public void handle(String sender, Map<String, String> tags, String[] arguments, TurboChat chat) {
        if (arguments.length < 3) return;
        if (!arguments[0].equalsIgnoreCase("*") || !arguments[1].equalsIgnoreCase("ACK")) return;

        chat.addCapability(arguments[2]);

        chat.getEventManager().callEvent(new IrcGainCapabilityEvent(arguments[2]));
    }
}
