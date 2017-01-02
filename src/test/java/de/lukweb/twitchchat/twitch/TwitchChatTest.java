package de.lukweb.twitchchat.twitch;

import de.lukweb.twitchchat.TwitchChat;
import de.lukweb.twitchchat.irc.TestIrcClient;
import de.lukweb.twitchchat.irc.TestMessageDelayer;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class TwitchChatTest {

    public static TwitchTestEnvironment getEnvironment() {
        TurboChat chat = new TurboChat("test", "oauth:0000");
        TestIrcClient irc = new TestIrcClient();
        chat.connect(irc, new TestMessageDelayer(chat, irc));
        return new TwitchTestEnvironment(chat, irc);
    }

    public static class TwitchTestEnvironment {

        private TwitchChat chat;
        private TestIrcClient irc;

        TwitchTestEnvironment(TwitchChat chat, TestIrcClient irc) {
            this.chat = chat;
            this.irc = irc;
        }

        public TwitchChat getChat() {
            return chat;
        }

        public TestIrcClient getIrc() {
            return irc;
        }
    }

    @Test
    public void testFirstMessages() {
        TwitchTestEnvironment env = getEnvironment();

        List<String> out = env.getIrc().getOutMessageLog();
        assertTrue(out.contains("CAP REQ :twitch.tv/membership"));
        assertTrue(out.contains("CAP REQ :twitch.tv/commands"));
        assertTrue(out.contains("CAP REQ :twitch.tv/tags"));
        assertTrue(out.contains("PASS oauth:0000"));
        assertTrue(out.contains("NICK test"));
    }

}
