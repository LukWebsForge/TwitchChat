package de.lukweb.twitchchat.twitch;

import de.lukweb.twitchchat.TwitchChat;
import de.lukweb.twitchchat.events.irc.IrcReceiveMessageEvent;
import de.lukweb.twitchchat.irc.IrcInputHandler;
import de.lukweb.twitchchat.twitch.commands.ClearchatC;
import de.lukweb.twitchchat.twitch.commands.GlobaluserstateC;
import de.lukweb.twitchchat.twitch.commands.HosttargetC;
import de.lukweb.twitchchat.twitch.commands.JoinC;
import de.lukweb.twitchchat.twitch.commands.ModeC;
import de.lukweb.twitchchat.twitch.commands.NoticeC;
import de.lukweb.twitchchat.twitch.commands.PartC;
import de.lukweb.twitchchat.twitch.commands.PingC;
import de.lukweb.twitchchat.twitch.commands.PrivmsgC;
import de.lukweb.twitchchat.twitch.commands.ReconnectC;
import de.lukweb.twitchchat.twitch.commands.RoomstateC;
import de.lukweb.twitchchat.twitch.commands.UsernoticeC;
import de.lukweb.twitchchat.twitch.commands.UserstateC;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwitchInputHandler implements IrcInputHandler {

    private TwitchChat chat;
    private Map<String, Command> commands;

    public TwitchInputHandler(TwitchChat chat) {
        this.chat = chat;
        commands = new HashMap<>();
        registerCommands();
    }

    private void registerCommands() {
        Command[] commandsArr = new Command[]{
                new ClearchatC(),
                new GlobaluserstateC(),
                new HosttargetC(),
                new JoinC(),
                new ModeC(),
                new NoticeC(),
                new PartC(),
                new PingC(),
                new PrivmsgC(),
                new ReconnectC(),
                new RoomstateC(),
                new UsernoticeC(),
                new UserstateC()
        };
        for (Command command : commandsArr) commands.put(command.getName().toLowerCase(), command);
    }

    @Override
    public void handle(String input) {
        IrcReceiveMessageEvent receiveMessageEvent = new IrcReceiveMessageEvent(input);
        chat.getEventManager().callEvent(receiveMessageEvent);
        if (receiveMessageEvent.isCanceled()) return;

        String[] splitted = input.split(" ");
        Map<String, String> tags = new HashMap<>();
        String channel = "";

        if (splitted[0].startsWith("@")) {
            String tagsStr = splitted[0].substring(1);
            for (String s : tagsStr.split("[;]")) {
                String[] split = s.split("[=]");
                tags.put(split[0], split.length > 1 ? split[0] : "");
            }
            splitted = Arrays.copyOfRange(splitted, 1, splitted.length);
        }

        if (splitted[0].startsWith(":")) {
            channel = splitted[0];
            splitted = Arrays.copyOfRange(splitted, 1, splitted.length);
        }

        String command = splitted[0];
        String[] arguments = Arrays.copyOfRange(splitted, 1, splitted.length);

        if (isInt(command)) {
            // This is just a info message from twitch or message about channels
            return;
        }

        if (!commands.containsKey(command.toLowerCase())) {
            // something is putt (broken) ):
            System.out.println("unregistered command: " + command);
            return;
        }

        commands.get(command.toLowerCase()).handle(channel, tags, arguments, chat);
    }

    private boolean isInt(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException ignored) {
            return false;
        }
    }
}
