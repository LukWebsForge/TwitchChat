package de.lukweb.twitchchat.events.user;

import de.lukweb.twitchchat.TwitchUser;

public class UserResubscribeEvent extends UserEvent {

    private int months;
    private String systemMsg;
    private String userMsg;

    public UserResubscribeEvent(TwitchUser user, int months, String systemMsg, String userMsg) {
        super(user);
        this.months = months;
        this.systemMsg = systemMsg;
        this.userMsg = userMsg;
    }

    public int getMonths() {
        return months;
    }

    public String getSystemMsg() {
        return systemMsg;
    }

    public String getUserMsg() {
        return userMsg;
    }
}
