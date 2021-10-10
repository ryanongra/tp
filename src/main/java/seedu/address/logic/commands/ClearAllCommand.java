package seedu.address.logic.commands;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Clears every person in the address book.
 */
public class ClearAllCommand extends Command {

    public static final String COMMAND_WORD = "clear ALL ENTRIES";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Clears all entries from the address book.\n";

    public static final String MESSAGE_CLEAR_ALL_SUCCESS = "Successfully cleared all entries";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> currentList = model.getAddressBook().getPersonList();

        if (currentList.size() == 0) {
            throw new CommandException(Messages.MESSAGE_EMPTY_LIST);
        }

        model.clearAllPerson();
        return new CommandResult(MESSAGE_CLEAR_ALL_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof ClearAllCommand);
    }
}
