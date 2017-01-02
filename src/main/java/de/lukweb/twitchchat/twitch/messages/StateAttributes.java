package de.lukweb.twitchchat.twitch.messages;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StateAttributes extends MessageAttributes {

    private List<Integer> emoteSets;

    public StateAttributes(Map<String, String> tags) {
        super(tags);

        emoteSets = new ArrayList<>();

        if (tags.containsKey("emote-sets")) {
            for (String emoteSet : tags.get("emote-sets").split("[,]")) {
                emoteSets.add(Integer.parseInt(emoteSet));
            }
        }
    }

    public List<Integer> getEmoteSets() {
        return emoteSets;
    }
}
