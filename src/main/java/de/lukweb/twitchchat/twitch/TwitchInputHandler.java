package de.lukweb.twitchchat.twitch;

import de.lukweb.twitchchat.events.irc.IrcReceiveMessageEvent;
import de.lukweb.twitchchat.irc.IrcInputHandler;
import de.lukweb.twitchchat.twitch.commands.*;
import de.lukweb.twitchchat.twitch.utils.IntegerUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwitchInputHandler implements IrcInputHandler {

    private TurboChat chat;
    private Map<String, Command> commands;

    public TwitchInputHandler(TurboChat chat) {
        this.chat = chat;
        commands = new HashMap<>();
        registerCommands();
    }

    private void registerCommands() {
        Command[] commandsArr = new Command[]{
                new CapC(),
                new ClearchatC(),
                new GlobaluserstateC(),
                new HosttargetC(),
                new JoinC(),
                new ModeC(),
                new NamesC(),
                new NoticeC(),
                new PartC(),
                new PingC(),
                new PrivmsgC(),
                new ReconnectC(),
                new RoomstateC(),
                new UsernoticeC(),
                new UserstateC(),
                new WhisperC()
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
        String sender = "";

        if (splitted[0].startsWith("@")) {
            String tagsStr = splitted[0].substring(1);
            for (String s : tagsStr.split("[;]")) {
                String[] split = s.split("[=]");
                tags.put(split[0], split.length > 1 ? split[1].trim() : "");
            }
            splitted = Arrays.copyOfRange(splitted, 1, splitted.length);
        }

        if (splitted[0].startsWith(":")) {
            sender = splitted[0];
            splitted = Arrays.copyOfRange(splitted, 1, splitted.length);
        }

        String command = splitted[0];
        String[] arguments = Arrays.copyOfRange(splitted, 1, splitted.length);

        if (IntegerUtils.isInt(command)) {
            if (arguments.length >= 2 && arguments[1].equals("=")) {
                command = "names";
            } else {
                // This is just a info message from twitch
                return;
            }
        }

        if (!commands.containsKey(command.toLowerCase())) {
            // something is broken ):
            System.out.println("unregistered command: " + command);
            return;
        }

        commands.get(command.toLowerCase()).handle(sender, tags, arguments, chat);
    }
}
