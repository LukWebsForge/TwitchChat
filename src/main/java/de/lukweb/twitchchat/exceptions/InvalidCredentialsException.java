package de.lukweb.twitchchat.exceptions;

public class InvalidCredentialsException extends RuntimeException {

    public InvalidCredentialsException() {
        super("The credentials for your Twitch Account are wrong!");
    }
}
