package de.lukweb.twitchchat.twitch.messages;

import de.lukweb.twitchchat.TwitchRank;
import de.lukweb.twitchchat.twitch.messages.MessageEmote.EmoteLocation;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MessageAttributeTest {

    @Test
    public void testAttributes() {
        String tagString = "badges=staff/1,bits/1000;bits=100;color=;display-name=TWITCH_UserNaME;emotes=15:4-6,1-3/" +
                "21:8-15;id=b34ccfc7-4977-403a-8a94-33c6bac34fb8;mod=0;room-id=1337;subscriber=0;turbo=1;" +
                "user-id=1337;user-type=staff";

        Map<String, String> tagsMap = new HashMap<>();

        for (String tag : tagString.split("[;]")) {
            String[] split = tag.split("[=]");
            tagsMap.put(split[0], split.length < 2 ? "" : split[1]);
        }

        MessageAttributes attributes = new MessageAttributes(tagsMap);

        assertTrue(attributes.getBadges().contains(new MessageBadge("staff", 1)));
        assertTrue(attributes.getBadges().contains(new MessageBadge("bits", 1000)));
        assertEquals(attributes.getColor(), "");
        assertEquals(attributes.getDisplayName(), "TWITCH_UserNaME");
        assertTrue(attributes.getEmotes().contains(new MessageEmote(15, Arrays.asList(
                new EmoteLocation(4, 6), new EmoteLocation(1, 3)
        ))));
        assertTrue(attributes.getEmotes().contains(new MessageEmote(21, Collections.singletonList(
                new EmoteLocation(8, 15)
        ))));
        assertEquals(attributes.getMessageId(), "b34ccfc7-4977-403a-8a94-33c6bac34fb8");
        assertEquals(attributes.isMod(), false);
        assertEquals(attributes.getRoomId(), 1337);
        assertEquals(attributes.isSubscriber(), false);
        assertEquals(attributes.isTurbo(), true);
        assertEquals(attributes.getUserId(), 1337);
        assertEquals(attributes.getRank(), TwitchRank.STAFF);
    }

}
