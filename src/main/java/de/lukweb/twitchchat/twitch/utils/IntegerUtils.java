package de.lukweb.twitchchat.twitch.utils;

public class IntegerUtils {

    public static boolean isInt(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException ignored) {
            return false;
        }
    }

}
