package de.lukweb.twitchchat.events.irc;

import de.lukweb.twitchchat.events.Event;

public class IrcGainCapabilityEvent extends Event {

    private String capability;

    public IrcGainCapabilityEvent(String capability) {
        this.capability = capability;
    }

    public String getCapability() {
        return capability;
    }
}
