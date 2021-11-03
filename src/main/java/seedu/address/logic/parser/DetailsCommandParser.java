package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DetailsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameEqualKeywordPredicate;

/**
 * Parses input arguments and creates a new DetailsCommand object
 */
public class DetailsCommandParser implements Parser<DetailsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DetailsCommand
     * and returns a DetailsCommand object for execution.
     *
     * @param args The arguments parsed.
     * @return Command created from the arguments parsed.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DetailsCommand parse(String args) throws ParseException {
        if (args.trim().equals("")) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DetailsCommand.MESSAGE_USAGE));
        }

        try {
            Index index = ParserUtil.parseIndex(args);
            return new DetailsCommand(index);
        } catch (ParseException pe) {
            Name name = ParserUtil.parseName(args);
            return new DetailsCommand(new NameEqualKeywordPredicate(name));
        }
    }

}
