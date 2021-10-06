package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Finds and displays the details of person whose name matches the keyword exactly.
 * Keyword matching is exact and case sensitive.
 */
public class DetailsCommand extends Command {

    public static final String COMMAND_WORD = "details";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays the details of a member from the "
            + "specified keywords (case-sensitive and exact match).\n"
            + "Parameters: NAME (case-sensitive)\n"
            + "Example: " + COMMAND_WORD + " Xiao Ming";

    private final Name name;

    public DetailsCommand(Name name) {
        this.name = name;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<Person> persons = model.getAddressBook().getPersonList();
        for (Person person : persons) {
            if (person.getName().equals(name)) {
                return new CommandResult(
                        String.format(Messages.MESSAGE_PERSON_DETAILS_FOUND, name));
            }
        }
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSON_DETAILS_NOT_FOUND, name));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DetailsCommand // instanceof handles nulls
                && name.equals(((DetailsCommand) other).name)); // state check
    }
}
