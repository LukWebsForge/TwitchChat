# TwitchChat
A simple IRC client for Twitch

:warning: This project hasn't been updated for six or more years. Maybe it does still work, but I can't guarantee anything.

## Getting Started

### Download

I recommend you to use a maven project for your application. 
You can access this library via [JitPack](https://jitpack.io/#LukWebsForge/TwitchChat):
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.LukWebsForge</groupId>
        <artifactId>TwitchChat</artifactId>
        <version>v1.0.rc3</version>
    </dependency>
</dependencies>
```

### Usage

1. Create an instance of the TwitchChat using `TwitchChat#build(String username, String oauth)`
2. Register your event listeners using `EventManager#register(Listener listener)`
3. Connect the instance to the Twitch IRC using `TwitchChat#connect()`
4. Join channels using `TwitchChat#getChannel(String channel)`

### Example

```java
TwitchChat chat = TwitchChat.build("twitchusername", "oauth:123456789abcdefghijklmn");
// You can get an oauth key at https://twitchapps.com/tmi/
chat.getEventManager().register(new Listener() {
    @Handler
    public void onJoin(UserJoinChannelEvent ev) {
        System.out.println(ev.getUser().getName() + " joined channel " + ev.getChannel().getName());
    }
});
chat.connect();
TwitchChannel channel = chat.getChannel("twitchchannel");
channel.sendMessage("Hello I'am here!");
Thread.sleep(Integer.MAX_VALUE);
```

## Contribution

If you want to contribute you can find helpful information at 
[Twitch's developer documentation](https://dev.twitch.tv/docs/irc/).

Your ideas and bugs are welcome.
