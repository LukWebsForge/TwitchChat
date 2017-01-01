package de.lukweb.twitchchat.twitch.messages;

public class MessageBadge {

    private String name;
    private int value;

    public MessageBadge(String badgeString) {
        String[] split = badgeString.split("[/]");
        name = split[0];
        if (split.length < 2) {
            value = 1;
            return;
        }
        value = split.length < 2 ? 1 : Integer.parseInt(split[1]);
    }

    public MessageBadge(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageBadge that = (MessageBadge) o;

        if (value != that.value) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + value;
        return result;
    }
}
