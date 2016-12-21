package de.lukweb.twitchchat.events;

public interface Cancellable {

    void setCanceled(boolean canceled);

    boolean isCanceled();

}
