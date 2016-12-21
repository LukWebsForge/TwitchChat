package de.lukweb.twitchchat.events;

import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EventMethod implements Comparable {

    private Method method;
    private Listener classInstance;
    private Class<?> event;
    private byte priority;

    public EventMethod(Method method, Listener listener) {
        if (!is(method)) return;
        this.method = method;
        this.classInstance = listener;
        this.event = method.getParameters()[0].getType();
        this.priority = method.getAnnotation(annotation).priority();
    }

    public Class<?> getEvent() {
        return event;
    }

    public byte getPriority() {
        return priority;
    }

    public void call(Event event) {
        try {
            method.setAccessible(true);
            method.invoke(classInstance, event);
        } catch (Exception e) {
            // We catch all exceptions, because we want to prevent exceptions from breaking out of the listener
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error while calling event: " +
                    event.getClass().getSimpleName(), e);
        }
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof EventMethod)) return -1;
        EventMethod other = (EventMethod) o;

        if (other.getPriority() > getPriority()) return 1;
        else if (other.getPriority() < getPriority()) return -1;

        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventMethod that = (EventMethod) o;

        return method.equals(that.method);
    }

    @Override
    public int hashCode() {
        return method.hashCode();
    }

    ////

    private static Class<? extends Handler> annotation = Handler.class;

    public static boolean is(Method method) {
        if (!method.isAnnotationPresent(annotation)) return false;
        if (method.getParameters().length != 1) return false;
        if (!Event.class.isAssignableFrom(method.getParameters()[0].getType())) return false;
        return true;
    }
}
