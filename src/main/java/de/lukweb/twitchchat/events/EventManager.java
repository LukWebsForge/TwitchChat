package de.lukweb.twitchchat.events;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class EventManager {

    private List<EventMethod> eventMethods = new ArrayList<>();

    public void register(Listener listener) {
        eventMethods.addAll(getEventMethods(listener));
    }

    public void unregister(Listener listener) {
        List<EventMethod> methods = getEventMethods(listener);
        eventMethods.removeIf(methods::contains);
    }

    private List<EventMethod> getEventMethods(Listener listener) {
        List<EventMethod> methods = new ArrayList<>();

        for (Method method : listener.getClass().getMethods())
            if (EventMethod.is(method)) methods.add(new EventMethod(method, listener));

        return methods;
    }

    public void callEvent(Event event) {
        eventMethods.stream()
                .filter(m -> event.getClass().isAssignableFrom(m.getEvent()))
                .sorted()
                .forEachOrdered(m -> m.call(event));
    }

}
