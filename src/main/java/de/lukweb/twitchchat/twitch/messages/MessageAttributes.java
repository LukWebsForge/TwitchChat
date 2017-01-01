package de.lukweb.twitchchat.twitch.messages;

import de.lukweb.twitchchat.TwitchRank;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MessageAttributes {

    private List<MessageBadge> badges;
    private String color;
    private String displayName;
    private List<MessageEmote> emotes;
    private List<Integer> emoteSets;
    private String messageId;
    private boolean mod;
    private boolean subscriber;
    private boolean turbo;
    private int roomId;
    private int userId;
    private TwitchRank rank;
    private int bits;

    public MessageAttributes(Map<String, String> tags) {

        badges = new ArrayList<>();
        emotes = new ArrayList<>();
        emoteSets = new ArrayList<>();

        if (tags.containsKey("badges")) {
            for (String badgeString : tags.get("badges").split("[,]")) {
                badges.add(new MessageBadge(badgeString));
            }
        }

        color = tags.get("color");
        displayName = tags.get("display-name");

        if (tags.containsKey("emotes")) {
            for (String emoteString : tags.get("emotes").split("/")) {
                emotes.add(new MessageEmote(emoteString));
            }
        }

        if (tags.containsKey("emote-sets")) {
            for (String emoteSet : tags.get("emote-sets").split("[,]")) {
                emoteSets.add(Integer.parseInt(emoteSet));
            }
        }

        messageId = tags.get("id");

        if (tags.containsKey("mod")) {
            mod = tags.get("mod").equals("1");
        }

        if (tags.containsKey("subscriber")) {
            subscriber = tags.get("subscriber").equals("1");
        }

        if (tags.containsKey("turbo")) {
            turbo = tags.get("turbo").equals("1");
        }

        if (tags.containsKey("room-id")) {
            roomId = Integer.parseInt(tags.get("room-id"));
        }

        if (tags.containsKey("user-id")) {
            userId = Integer.parseInt(tags.get("user-id"));
        }

        rank = TwitchRank.get(tags.getOrDefault("user-type", ""));

        if (tags.containsKey("bits")) {
            bits = Integer.parseInt(tags.get("bits"));
        }
    }

    public List<MessageBadge> getBadges() {
        return badges;
    }

    public String getColor() {
        return color;
    }

    public String getDisplayName() {
        return displayName;
    }

    public List<MessageEmote> getEmotes() {
        return emotes;
    }

    public List<Integer> getEmoteSets() {
        return emoteSets;
    }

    public String getMessageId() {
        return messageId;
    }

    public boolean isMod() {
        return mod;
    }

    public boolean isSubscriber() {
        return subscriber;
    }

    public boolean isTurbo() {
        return turbo;
    }

    public int getRoomId() {
        return roomId;
    }

    public int getUserId() {
        return userId;
    }

    public TwitchRank getRank() {
        return rank;
    }

    public int getBits() {
        return bits;
    }
}

