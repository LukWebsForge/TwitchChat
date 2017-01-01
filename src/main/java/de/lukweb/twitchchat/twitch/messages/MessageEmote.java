package de.lukweb.twitchchat.twitch.messages;

import java.util.ArrayList;
import java.util.List;

public class MessageEmote {

    private int id;
    private List<EmoteLocation> locations;

    public MessageEmote(String emoteString) {
        String[] split = emoteString.split("[:]");
        id = Integer.parseInt(split[0]);

        locations = new ArrayList<>();

        if (split.length < 2) return;

        for (String loc : split[1].split("[,]")) {
            String[] range = loc.split("[-]");
            if (range.length < 2) continue;
            locations.add(new EmoteLocation(Integer.parseInt(range[0]), Integer.parseInt(range[1])));
        }
    }

    public MessageEmote(int id, List<EmoteLocation> locations) {
        this.id = id;
        this.locations = locations;
    }

    public int getId() {
        return id;
    }

    public List<EmoteLocation> getLocations() {
        return locations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageEmote that = (MessageEmote) o;

        if (id != that.id) return false;
        return locations != null ? locations.equals(that.locations) : that.locations == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (locations != null ? locations.hashCode() : 0);
        return result;
    }

    public static class EmoteLocation {

        private int start;
        private int end;

        public EmoteLocation(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            EmoteLocation that = (EmoteLocation) o;

            if (start != that.start) return false;
            return end == that.end;
        }

        @Override
        public int hashCode() {
            int result = start;
            result = 31 * result + end;
            return result;
        }
    }
}
