package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.index.Index;
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

    public static final int MODE_ALL = 0;
    public static final int MODE_RANGE = 1;

    public int flag;
    public int mode;
    public Index begin;
    public Index end;

    public ClearCommand(int flag, int mode, Index begin, Index end) {
        this.flag = flag;
        this.mode = mode;
        this.begin = begin;
        this.end = end;
    }

    public CommandResult executeInPersonMode(Model model) {
        if (mode == MODE_ALL) {
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
