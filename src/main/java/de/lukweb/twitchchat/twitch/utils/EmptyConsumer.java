package de.lukweb.twitchchat.twitch.utils;

import java.util.function.Consumer;

public class EmptyConsumer<T> implements Consumer<T> {

    @Override
    public void accept(T t) {

    }
}
