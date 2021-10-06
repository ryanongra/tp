package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;

/**
 * Finds and displays the details of person whose name matches the keyword exactly.
 * Keyword matching is exact and case sensitive.
 */
public class DetailsCommand extends Command {

    public static final String COMMAND_WORD = "details";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays the details of a member.\n"
            + "Parameters: NAME (Case Sensitive)\n"
            + "Example: " + COMMAND_WORD + " Xiao Ming";

    private final String keyword;

    public DetailsCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        // TODO: update the logic here
        requireNonNull(model);
        System.out.println(keyword);
//        return new CommandResult(
//                String.format(Messages.MESSAGE_PERSON_DETAILS_NOT_FOUND, keyword));
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSON_DETAILS_FOUND, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DetailsCommand // instanceof handles nulls
                && keyword.equals(((DetailsCommand) other).keyword)); // state check
    }
}
