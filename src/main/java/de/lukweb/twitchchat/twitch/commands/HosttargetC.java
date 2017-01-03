package de.lukweb.twitchchat.twitch.commands;

import de.lukweb.twitchchat.events.channel.HostChannelEvent;
import de.lukweb.twitchchat.events.channel.UnhostChannelEvent;
import de.lukweb.twitchchat.twitch.Command;
import de.lukweb.twitchchat.twitch.TurboChannel;
import de.lukweb.twitchchat.twitch.TurboChat;

import java.util.Map;

public class HosttargetC extends Command {

    public HosttargetC() {
        super("HOSTTARGET");
    }

    @Override
    public void handle(String sender, Map<String, String> tags, String[] arguments, TurboChat chat) {
        if (arguments.length > 3) return;

        TurboChannel host = chat.getChannel(arguments[0].substring(1));
        int viewers = arguments[2].equals("-") ? 0 : Integer.parseInt(arguments[2]);

        if (arguments[1].equals(":-")) {
            String target = host.getHosting();
            host.setHosting(null);

            chat.getEventManager().callEvent(new UnhostChannelEvent(host, target, viewers));
        } else {
            String target = arguments[1].substring(1);
            host.setHosting(target);

            chat.getEventManager().callEvent(new HostChannelEvent(host, target, viewers));
        }

    }
}
