package de.lukweb.twitchchat.twitch.messages;

import java.util.Map;

public class WhisperAttributes extends MessageAttributes {

    private String threadId;

    public WhisperAttributes(Map<String, String> tags) {
        super(tags);

        if (tags.containsKey("thread-id")) {
            threadId = tags.get("thread-id");
        }
    }

    public String getThreadId() {
        return threadId;
    }
}
