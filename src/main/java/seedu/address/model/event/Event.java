package seedu.address.model.event;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents an Event in ForYourInterest.
 * Guarantees: event name is not null.
 */
public class Event {

    // Identity fields
    private final EventName eventName;

    // Data fields
    // TODO: Add a list of Person involved in event

    /**
     * Event name must not be null.
     */
    public Event(EventName eventName) {
        requireAllNonNull(eventName);
        this.eventName = eventName;
    }

    public EventName getEventName() {
        requireAllNonNull(eventName);
        return eventName.copy();
    }

    /**
     * Returns true if both events have the same identity and data fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Event)) {
            return false;
        }

        Event otherEvent = (Event) other;
        return otherEvent.getEventName().equals(getEventName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventName);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getEventName());
        return builder.toString();
    }
}
