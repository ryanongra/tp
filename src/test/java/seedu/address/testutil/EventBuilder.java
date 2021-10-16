package seedu.address.testutil;

import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;

public class EventBuilder {

    public static final String DEFAULT_EVENT_NAME = "CS2103 Party";
    // TODO: add list of persons
    private EventName eventName;

    /**
     * Creates a {@code EventBuilder} with the default details.
     */
    public EventBuilder() {
        eventName = new EventName(DEFAULT_EVENT_NAME);
    }

    /**
     * Initializes the EventBuilder with the data of {@code eventToCopy}.
     */
    public EventBuilder(Event eventToCopy) {
        eventName = eventToCopy.getEventName();
    }

    /**
     * Sets the {@code EventName} of the {@code Event} that we are building.
     */
    public EventBuilder withEventName(String eventName) {
        this.eventName = new EventName(eventName);
        return this;
    }

    public Event build() {
        return new Event(eventName);
    }

}
