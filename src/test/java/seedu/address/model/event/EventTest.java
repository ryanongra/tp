package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME_DINNER;
import static seedu.address.testutil.TypicalEvents.DINNER_EVENT;
import static seedu.address.testutil.TypicalEvents.PARTY_EVENT;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EventBuilder;

class EventTest {
    @Test
    public void equals() {
        // same values -> returns true
        Event partyEventCopy = new EventBuilder(PARTY_EVENT).build();
        assertTrue(PARTY_EVENT.equals(partyEventCopy));

        // same object -> returns true
        assertTrue(PARTY_EVENT.equals(PARTY_EVENT));

        // null -> returns false
        assertFalse(PARTY_EVENT.equals(null));

        // different type -> returns false
        assertFalse(PARTY_EVENT.equals(5));

        // different event -> returns false
        assertFalse(PARTY_EVENT.equals(DINNER_EVENT));

        // different eventName -> returns false
        Event editedPartyEvent = new EventBuilder(PARTY_EVENT).withEventName(VALID_EVENT_NAME_DINNER).build();
        assertFalse(PARTY_EVENT.equals(editedPartyEvent));
    }
}
