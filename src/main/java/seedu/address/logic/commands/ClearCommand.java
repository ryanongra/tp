package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";
    public static final String MESSAGE_USAGE = "Clear command usage - later fill";
    public static final int PERSON_MODE = 0;
    public static final int EVENT_MODE = 1;
    public int mode;

    //constructor
    public ClearCommand(int mode) {
        this.mode = mode;
    }

    public CommandResult executeInPersonMode(Model model) {
        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }

    public CommandResult executeInEventMode(Model model) {
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (mode == PERSON_MODE) {
            return executeInPersonMode(model);
        } else {
            return executeInEventMode(model);
        }
    }
}
