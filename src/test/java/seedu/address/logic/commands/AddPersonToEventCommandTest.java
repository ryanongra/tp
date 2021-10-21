package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME_DINNER;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;
import seedu.address.model.person.Person;
import seedu.address.testutil.EventBuilder;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.PersonBuilder;

class AddPersonToEventCommandTest {

    @Test
    public void constructor_nullPersonNullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddPersonToEventCommand(null, null));
    }

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
            -> new AddPersonToEventCommand(null, new EventBuilder().build()));
    }

    @Test
    public void constructor_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
            -> new AddPersonToEventCommand(new PersonBuilder().build(), null));
    }

    @Test
    public void execute_personAcceptedByEvent_addSuccessful() throws Exception {
        Model model = new ModelStub();
        Person validPerson = new PersonBuilder().build();
        EventStub validEvent = new EventStub(new EventName(VALID_EVENT_NAME_DINNER));
        AddPersonToEventCommand command = new AddPersonToEventCommand(validPerson, validEvent);

        CommandResult commandResult = command.execute(model);

        assertEquals(String.format(AddPersonToEventCommand.MESSAGE_SUCCESS, validPerson),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPerson), validEvent.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        Event validEvent = new EventBuilder().withPerson(validPerson).build();
        AddPersonToEventCommand command = new AddPersonToEventCommand(validPerson, validEvent);
        ModelStub modelStub = new ModelStub();

        assertThrows(CommandException.class, AddPersonToEventCommand.MESSAGE_DUPLICATE_PERSON, ()
            -> command.execute(modelStub));
    }

    private class EventStub extends Event {
        final ArrayList<Person> personsAdded = new ArrayList<>();

        public EventStub(EventName eventName) {
            super(eventName);
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }
    }

    @Test
    public void equals() {
        Person alice = new PersonBuilder().withName("Alice").build();
        Person bob = new PersonBuilder().withName("Bob").build();
        Event lunch = new EventBuilder().withEventName("lunch").build();
        Event dinner = new EventBuilder().withEventName("dinner").build();
        AddPersonToEventCommand aliceLunch = new AddPersonToEventCommand(alice, lunch);
        AddPersonToEventCommand bobLunch = new AddPersonToEventCommand(bob, lunch);
        AddPersonToEventCommand aliceDinner = new AddPersonToEventCommand(alice, dinner);
        AddPersonToEventCommand bobDinner = new AddPersonToEventCommand(bob, dinner);

        // same object -> returns true
        assertTrue(aliceLunch.equals(aliceLunch));

        // same values -> returns true
        AddPersonToEventCommand aliceLunchCopy = new AddPersonToEventCommand(alice, lunch);
        assertTrue(aliceLunch.equals(aliceLunchCopy));

        // different types -> returns false
        assertFalse(aliceLunch.equals(1));

        // null -> returns false
        assertFalse(aliceLunch.equals(null));

        // different event -> returns false
        assertFalse(aliceLunch.equals(bobLunch));
        assertFalse(aliceLunch.equals(aliceDinner));
        assertFalse(aliceLunch.equals(bobDinner));
    }
}
