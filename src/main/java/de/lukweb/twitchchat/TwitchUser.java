package de.lukweb.twitchchat;

/**
 * A TwitchUser is specific for every {@link TwitchChannel}.
 */
public class TwitchUser {

    private String name;
    private TwitchChannel channel;

    private boolean staff; // global twitch mods
    private boolean turbo;
    private boolean broadcaster;
    private boolean mod;
    private boolean subscriber;

    private int color;
    private String displayName;

    public TwitchUser(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TwitchUser that = (TwitchUser) o;

        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
