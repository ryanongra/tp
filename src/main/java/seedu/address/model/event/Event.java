package seedu.address.model.event;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Represents an Event in ForYourInterest.
 * Guarantees: event name is not null.
 */
public class Event {

    // Identity fields
    private final EventName eventName;

    // Data fields
    private final UniquePersonList attendees;

    /**
     * Event name must not be null.
     */
    public Event(EventName eventName) {
        requireAllNonNull(eventName);
        this.eventName = eventName;
        this.attendees = new UniquePersonList();
    }

    /**
     * Event name and attendees must not be null.
     */
    public Event(EventName eventName, UniquePersonList attendees) {
        requireAllNonNull(eventName, attendees);
        this.eventName = eventName;
        this.attendees = attendees;
    }

    public EventName getEventName() {
        requireAllNonNull(eventName);
        return eventName.copy();
    }

    /**
     * Returns a copy of attendee list.
     */
    public UniquePersonList getAttendees() {
        requireAllNonNull(attendees);
        UniquePersonList newList = new UniquePersonList();
        newList.setPersons(attendees);
        return newList;
    }

    /**
     * Adds a {@code Person} as an attendee to the event.
     */
    public void addAttendee(Person person) {
        requireAllNonNull(person);
        attendees.add(person);
    }

    /**
     * Removes a {@code Person} as an attendee to the event.
     */
    public void removeAttendee(Person person) {
        requireAllNonNull(person);
        attendees.remove(person);
    }

    /**
     * Returns true if both events have the same event name.
     * This defines a weaker notion of equality between two events.
     */
    public boolean isSameEvent(Event otherEvent) {
        if (otherEvent == this) {
            return true;
        }

        return otherEvent != null
                && otherEvent.getEventName().equals(getEventName());
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
        return otherEvent.getEventName().equals(getEventName())
                && otherEvent.getAttendees().equals(getAttendees());
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventName, attendees);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getEventName());
        if (!attendees.isEmpty()) {
            builder.append("; Attendees: ");
            attendees.forEach(builder::append);
        }
        return builder.toString();
    }
}
