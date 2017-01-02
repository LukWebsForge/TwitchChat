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

        String capability = arguments[2].substring(1);
        chat.addCapability(capability);

        chat.getEventManager().callEvent(new IrcGainCapabilityEvent(capability));
    }
}
