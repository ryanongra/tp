package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
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

    public static final String MESSAGE_SUCCESS = "Person added to event: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the Event";

    private final Person toAdd;
    private final Event target;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddPersonToEventCommand(Person person, Event event) {
        requireAllNonNull(person, event);
        target = event;
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (target.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        target.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddPersonToEventCommand // instanceof handles nulls
                && toAdd.equals(((AddPersonToEventCommand) other).toAdd)
                && target.equals(((AddPersonToEventCommand) other).target));
    }
}
