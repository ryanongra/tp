package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;


/**
 * Clears the address book with the given range.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_CLEAR_PERSON_SUCCESS =
            "The person list has been cleared with the given range!";
    public static final String MESSAGE_CLEAR_EVENT_SUCCESS =
            "The event list has been cleared with the given range!";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Clears the person or event list with the given index range used in the displayed list"
            + "Parameters: " + "[flag] (can be [-p] for person list and [-e] for event)"
            + "RANGE (must either be all or a valid range like 1-10) \n"
            + "Example - clearing all entries in person list: " + COMMAND_WORD + "-p all \n"
            + "Example - clearing entries from index 1 to 10 (inclusive) in event list: "
            + COMMAND_WORD + "-e 1-10 \n";

    public static final int PERSON_FLAG = 0;
    public static final int EVENT_FLAG = 1;

    public static final int MODE_ALL = 0;
    public static final int MODE_RANGE = 1;

    public int flag;
    public int mode;
    public Index begin;
    public Index end;

    /**
     * Constructor for delete command for person.
     * @param flag which list to operate on, 0 for Person and 1 for Event
     * @param mode which mode to operate, 0 for clearing all and 1 for clearing in a specified range
     * @param begin The beginning index (inclusive) for clearing in a specified range
     * @param end The ending index (inclusive) for clearing in a specified range
     */
    public ClearCommand(int flag, int mode, Index begin, Index end) {
        this.flag = flag;
        this.mode = mode;
        this.begin = begin;
        this.end = end;
    }

    public CommandResult executePersonClear(Model model) throws CommandException {
        if (mode == MODE_ALL) {
            model.setAddressBook(new AddressBook());
        } else {
            List<Person> lastShownList = model.getFilteredPersonList();

            if (end.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            if (begin.getZeroBased() > end.getZeroBased()) {
                throw new CommandException(Messages.MESSAGE_INVALID_RANGE);
            }
            for (int i = end.getZeroBased(); i >= begin.getZeroBased(); i--) {
                Person personToDelete = lastShownList.get(i);
                model.deletePerson(personToDelete);
            }
        }
        return new CommandResult(MESSAGE_CLEAR_PERSON_SUCCESS);
    }

    public CommandResult executeEventClear(Model model) throws CommandException {
        int beginIndex, endIndex;
        List<Event> lastShownList = model.getFilteredEventList();
        if (mode == MODE_ALL) {
            beginIndex = 0;
            endIndex = lastShownList.size() - 1;
        } else {
            if (end.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            if (begin.getZeroBased() > end.getZeroBased()) {
                throw new CommandException(Messages.MESSAGE_INVALID_RANGE);
            }
            beginIndex = begin.getZeroBased();
            endIndex = end.getZeroBased();
        }

        for (int i = endIndex; i >= beginIndex; i--) {
            Event eventToDelete = lastShownList.get(i);
            model.deleteEvent(eventToDelete);
        }
        return new CommandResult(MESSAGE_CLEAR_EVENT_SUCCESS);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (flag == PERSON_FLAG) {
            return executePersonClear(model);
        } else {
            return executeEventClear(model);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof ClearCommand) {
            ClearCommand c = (ClearCommand) other;
            boolean result = true;
            boolean isFlagEqual = (flag == c.flag);
            boolean isModeEqual = (mode == c.mode);
            result = result && isFlagEqual && isModeEqual;
            if (begin == null && end == null) {
                result = result && c.begin == null && c.end == null;
            } else {
                if (c.begin == null || c.end == null) {
                    result = false;
                } else {
                    result = result && begin.equals(c.begin) && end.equals(c.end);
                }
            }
            return result;
        } else {
            return false;
        }
    }
}
