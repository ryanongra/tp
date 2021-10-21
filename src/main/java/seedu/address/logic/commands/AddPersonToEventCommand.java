package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_EVENT_DETAILS_NOT_FOUND;
import static seedu.address.commons.core.Messages.MESSAGE_PERSON_DETAILS_NOT_FOUND;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EVENTS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventNameEqualKeywordPredicate;
import seedu.address.model.person.NameEqualKeywordPredicate;
import seedu.address.model.person.Person;

/**
 * Adds a person to the address book.
 */
public class AddPersonToEventCommand extends Command {

    public static final String COMMAND_WORD = "addPersonToEvent";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a existing person in the address book "
            + "to the Event specified. "
            + "Parameters: "
            + PREFIX_NAME + "PERSON_NAME "
            + PREFIX_EVENT_NAME + "EVENT_NAME \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_EVENT_NAME + "Great Party Event";

    public static final String MESSAGE_SUCCESS = "Person %1$s added to %2$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the Event";

    private final NameEqualKeywordPredicate personPredicate;
    private final EventNameEqualKeywordPredicate eventPredicate;

    /**
     * Creates an AddCommand to add the specified {@code Person} matched by the name predicate to the event
     * matched by the event name predicate.
     */
    public AddPersonToEventCommand(NameEqualKeywordPredicate personPredicate,
                                   EventNameEqualKeywordPredicate eventPredicate) {
        requireAllNonNull(personPredicate, eventPredicate);
        this.personPredicate = personPredicate;
        this.eventPredicate = eventPredicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> personsMatched = model.getPersons(personPredicate);
        List<Event> eventsMatched = model.getEvents(eventPredicate);

        assert personsMatched.size() < 2;
        assert eventsMatched.size() < 2;

        if (personsMatched.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_PERSON_DETAILS_NOT_FOUND, personPredicate));
        }

        if (eventsMatched.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_EVENT_DETAILS_NOT_FOUND, eventPredicate));
        }

        Person toAdd = personsMatched.get(0);
        Event target = eventsMatched.get(0);

        if (target.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        target.addPerson(toAdd);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd.getName(), target.getEventName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddPersonToEventCommand // instanceof handles nulls
                && personPredicate.equals(((AddPersonToEventCommand) other).personPredicate)
                && eventPredicate.equals(((AddPersonToEventCommand) other).eventPredicate));
    }
}
