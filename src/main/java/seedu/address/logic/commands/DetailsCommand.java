package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.NameEqualKeywordPredicate;

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

    private final NameEqualKeywordPredicate predicate;

    public DetailsCommand(NameEqualKeywordPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        if (model.getFilteredPersonList().size() == 0) {
            return new CommandResult(
                    String.format(Messages.MESSAGE_PERSON_DETAILS_NOT_FOUND, predicate),
                    false, false, true);
        } else {
            return new CommandResult(
                    String.format(Messages.MESSAGE_PERSON_DETAILS_FOUND, predicate),
                    false, false, true);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DetailsCommand // instanceof handles nulls
                && predicate.equals(((DetailsCommand) other).predicate)); // state check
    }
}
