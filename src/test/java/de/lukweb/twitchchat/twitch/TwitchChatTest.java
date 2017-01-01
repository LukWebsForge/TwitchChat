package de.lukweb.twitchchat.twitch;

import de.lukweb.twitchchat.TwitchChat;
import de.lukweb.twitchchat.irc.IrcClientTest;

public class TwitchChatTest {

    public static TwitchChat getChat() {
        TurboChat chat = new TurboChat("test", "");
        chat.connect(new IrcClientTest());
        return chat;
    }

}
