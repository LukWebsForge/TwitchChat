package de.lukweb.twitchchat.events;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Handler {

    /**
     * The priority definies when a event should be called. Events with a low priority are called before events with
     * a high priority.
     *
     * @return the priority defining when the event should be called
     */
    byte priority() default 0;
}
