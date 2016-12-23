package de.lukweb.twitchchat.twitch.commands;

import de.lukweb.twitchchat.events.user.UserGainOperatorEvent;
import de.lukweb.twitchchat.events.user.UserLoseOperatorEvent;
import de.lukweb.twitchchat.twitch.Command;
import de.lukweb.twitchchat.twitch.TurboChat;
import de.lukweb.twitchchat.twitch.TurboUser;

import java.util.Map;

public class ModeC extends Command {

    public ModeC() {
        super("MODE");
    }

    @Override
    public void handle(String sender, Map<String, String> tags, String[] arguments, TurboChat chat) {
        if (arguments.length < 3) return;

        String channel = arguments[0].startsWith("#") ? arguments[0].substring(1) : arguments[0];
        boolean gained = arguments[1].startsWith("+");
        String user = arguments[2];

        TurboUser turboUser = chat.getChannel(channel).createTurboChatter(user);
        turboUser.setOperator(gained);

        if (gained) {
            chat.getEventManager().callEvent(new UserGainOperatorEvent(turboUser));
        } else {
            chat.getEventManager().callEvent(new UserLoseOperatorEvent(turboUser));
        }

    }
}
