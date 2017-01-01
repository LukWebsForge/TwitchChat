package de.lukweb.twitchchat;

public enum TwitchRank {

    NONE(""),
    CHANNEL_MOD("mod"),
    GLOBAL_MOD,
    ADMIN,
    STAFF;

    private String name;

    TwitchRank() {
        this.name = name().toLowerCase();
    }

    TwitchRank(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static TwitchRank get(String lowercaseName) {
        for (TwitchRank rank : values()) if (rank.getName().equals(lowercaseName)) return rank;
        throw new RuntimeException("A TwitchRank with the name \"" + lowercaseName + "\" couldn't be found!");
    }
}
