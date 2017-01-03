package de.lukweb.twitchchat.twitch.commands;

import de.lukweb.twitchchat.events.channel.ChannelDataLoadedEvent;
import de.lukweb.twitchchat.events.channel.ChannelLanguageChangeEvent;
import de.lukweb.twitchchat.events.channel.DisableEmoteOnlyChatEvent;
import de.lukweb.twitchchat.events.channel.DisableR9KEvent;
import de.lukweb.twitchchat.events.channel.DisableSubsOnlyChatEvent;
import de.lukweb.twitchchat.events.channel.EnableEmoteOnlyChatEvent;
import de.lukweb.twitchchat.events.channel.EnableR9KEvent;
import de.lukweb.twitchchat.events.channel.EnableSubsOnlyChatEvent;
import de.lukweb.twitchchat.events.channel.SlowModeTimeChangeEvent;
import de.lukweb.twitchchat.twitch.Command;
import de.lukweb.twitchchat.twitch.TurboChannel;
import de.lukweb.twitchchat.twitch.TurboChat;

import java.util.Map;

public class RoomstateC extends Command {

    public RoomstateC() {
        super("ROOMSTATE");
    }

    @Override
    public void handle(String sender, Map<String, String> tags, String[] arguments, TurboChat chat) {
        if (arguments.length < 1) return;

        TurboChannel channel = chat.getChannel(arguments[0].substring(1));
        boolean events = tags.size() < 5;

        if (tags.containsKey("broadcaster-lang")) {
            String oldLanguage = channel.getLanguage();
            channel.setLanguage(tags.get("broadcaster-lang"));
            if (events) chat.getEventManager().callEvent(new ChannelLanguageChangeEvent(channel, oldLanguage));
        }

        if (tags.containsKey("r9k")) {
            boolean r9k = tags.get("r9k").equalsIgnoreCase("1");
            channel.setUniqueMessages(r9k);

            if (events && r9k) {
                chat.getEventManager().callEvent(new EnableR9KEvent(channel));
            } else if (events && !r9k) {
                chat.getEventManager().callEvent(new DisableR9KEvent(channel));
            }
        }

        if (tags.containsKey("slow")) {
            try {
                int slowMode = Integer.parseInt(tags.get("slow"));
                int oldSlowMode = channel.getSlowModeTime();
                channel.setSlowMode(slowMode);

                if (events) chat.getEventManager().callEvent(new SlowModeTimeChangeEvent(channel, oldSlowMode));
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
        }

        if (tags.containsKey("subs-only")) {
            boolean subsOnly = tags.get("subs-only").equalsIgnoreCase("1");
            channel.setSubsOnly(subsOnly);

            if (events && subsOnly) {
                chat.getEventManager().callEvent(new EnableSubsOnlyChatEvent(channel));
            } else if (events && !subsOnly) {
                chat.getEventManager().callEvent(new DisableSubsOnlyChatEvent(channel));
            }
        }

        if (tags.containsKey("emote-only")) {
            boolean emoteOnly = tags.get("emote-only").equalsIgnoreCase("1");
            channel.setEmoteOnly(emoteOnly);

            if (events && emoteOnly) {
                chat.getEventManager().callEvent(new EnableEmoteOnlyChatEvent(channel));
            } else if (events && !emoteOnly) {
                chat.getEventManager().callEvent(new DisableEmoteOnlyChatEvent(channel));
            }
        }

        if (!events) {
            chat.getEventManager().callEvent(new ChannelDataLoadedEvent(channel));
        }

    }
}
