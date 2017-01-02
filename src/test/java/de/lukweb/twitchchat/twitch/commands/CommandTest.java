package de.lukweb.twitchchat.twitch.commands;

import de.lukweb.twitchchat.events.Handler;
import de.lukweb.twitchchat.events.Listener;
import de.lukweb.twitchchat.events.irc.IrcGainCapabilityEvent;
import de.lukweb.twitchchat.events.user.UserBannedEvent;
import de.lukweb.twitchchat.events.user.UserTimeoutedEvent;
import de.lukweb.twitchchat.irc.TestIrcClient;
import de.lukweb.twitchchat.twitch.TwitchChatTest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CommandTest {

    private CountDownLatch lock;

    @Test
    public void testPingAnswer() {
        TwitchChatTest.TwitchTestEnvironment env = TwitchChatTest.getEnvironment();
        TestIrcClient irc = env.getIrc();

        irc.sendStringAsTwitch("PING :tmi.twitch.tv");
        assertEquals(irc.getOutMessageLog().get(irc.getOutMessageLog().size() - 1), "PONG :tmi.twitch.tv");

        irc.sendStringAsTwitch("PING :test.tv");
        assertEquals(irc.getOutMessageLog().get(irc.getOutMessageLog().size() - 1), "PONG :test.tv");
    }

    @Test
    public void testCapabilities() throws InterruptedException {
        TwitchChatTest.TwitchTestEnvironment env = TwitchChatTest.getEnvironment();
        TestIrcClient irc = env.getIrc();

        List<String> capabilites = new ArrayList<>();
        lock = new CountDownLatch(2);

        env.getChat().getEventManager().register(new Listener() {
            @Handler
            public void onGainCapability(IrcGainCapabilityEvent event) {
                capabilites.add(event.getCapability());
                lock.countDown();
            }
        });

        irc.sendStringAsTwitch(":tmi.twitch.tv CAP * ACK :twitch.tv/membership");
        irc.sendStringAsTwitch(":tmi.twitch.tv CAP * ACK :twitch.tv/commands");
        irc.sendStringAsTwitch(":tmi.twitch.tv CAP dgklrd kldfgl");

        lock.await(100, TimeUnit.MILLISECONDS);

        assertTrue(capabilites.size() == 2);
        assertEquals(capabilites.get(0), "twitch.tv/membership");
        assertEquals(capabilites.get(1), "twitch.tv/commands");
    }

    @Test
    public void testClearchat() throws InterruptedException {
        TwitchChatTest.TwitchTestEnvironment env = TwitchChatTest.getEnvironment();

        List<String> usernames = new ArrayList<>();
        List<String> reasons = new ArrayList<>();
        List<Integer> duration = new ArrayList<>();
        lock = new CountDownLatch(3);

        env.getChat().getEventManager().register(new Listener() {
            @Handler
            public void onTimeout(UserTimeoutedEvent event) {
                usernames.add(event.getUser().getName());
                reasons.add(event.getReason());
                duration.add(event.getDuration());
                lock.countDown();
            }

            @Handler
            public void onBan(UserBannedEvent event) {
                usernames.add(event.getUser().getName());
                reasons.add(event.getReason());
                lock.countDown();
            }
        });

        TestIrcClient irc = env.getIrc();
        irc.sendStringAsTwitch("@ban-duration=15;ban-reason=Evil :tmi.twitch.tv CLEARCHAT #achannel :evil_user");
        irc.sendStringAsTwitch("@ban-duration=4;ban-reason=Ham :tmi.twitch.tv CLEARCHAT #achannel :other_user");
        irc.sendStringAsTwitch("@ban-reason=Nope :tmi.twitch.tv CLEARCHAT #thechannel :very_evil_user");
        irc.sendStringAsTwitch("@ban-reason=Nope :tmi.twitch.tv CLEARCHAT hi");

        lock.await(100, TimeUnit.MILLISECONDS);

        assertTrue(usernames.size() == 3);
        assertTrue(reasons.size() == 3);
        assertTrue(duration.size() == 2);

        assertEquals(usernames, Arrays.asList("evil_user", "other_user", "very_evil_user"));
        assertEquals(reasons, Arrays.asList("Evil", "Ham", "Nope"));
        assertEquals(duration, Arrays.asList(15, 4));
    }

}
