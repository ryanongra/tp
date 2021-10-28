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
    public static final String MESSAGE_SUCCESS2 = "Event book has been cleared!";
    public static final String MESSAGE_USAGE = "Clear command usage - later fill";
    public static final int PERSON_FLAG = 0;
    public static final int EVENT_FLAG = 1;
    public static final String MODE_ALL = "all";

    public int flag;
    public String mode;

    //constructor
    public ClearCommand(int flag, String mode) {
        this.flag = flag;
        this.mode = mode;
    }

    public CommandResult executeInPersonMode(Model model) {
        if (mode.equals(MODE_ALL)) {
            model.setAddressBook(new AddressBook());
        } else {
            // to be implemented for other features later
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }

    public CommandResult executeInEventMode(Model model) {
        // to be implemented for other features later
        return new CommandResult(MESSAGE_SUCCESS2);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (flag == PERSON_FLAG) {
            return executeInPersonMode(model);
        } else {
            return executeInEventMode(model);
        }
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof ClearCommand);
    }
}
