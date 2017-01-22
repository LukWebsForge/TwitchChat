# TwitchChat
A simple IRC client for Twitch

:tada: **The first release candidate is out!** Happy testing!

[![Build Status](https://travis-ci.org/LukWebsForge/TwitchChat.svg?branch=master)](https://travis-ci.org/LukWebsForg/TwitchChat)
[![JitPack](https://jitpack.io/v/LukWebsForge/TwitchChat.svg)](https://jitpack.io/#LukWebsForge/TwitchChat)


## Getting Started

### Download

I recommend you to use a maven project for your application. You can use 
[JitPack](https://jitpack.io/#lukweb-de/TwitchChat) to embed this library. A release on maven central is also 
planned for the future. 

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

If you want to contribute you can find helpful information 
[here](https://github.com/justintv/Twitch-API/blob/master/IRC.md).

Your ideas and bugs are welcome.
