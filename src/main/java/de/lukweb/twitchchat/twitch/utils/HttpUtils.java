package de.lukweb.twitchchat.twitch.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class HttpUtils {

    public static String get(String urlStr) {
        try {
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setInstanceFollowRedirects(true);

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            List<String> lines = in.lines().collect(Collectors.toList());

            in.close();

            return String.join("\n", lines);
        } catch (IOException e) {
            return null;
        }
    }

}
