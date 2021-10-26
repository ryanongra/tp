package seedu.address.logic.parser;

import seedu.address.logic.commands.ClearCommand;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.FLAG_PERSON;
import static seedu.address.logic.parser.CliSyntax.FLAG_EVENT;
import seedu.address.logic.parser.exceptions.ParseException;

import java.util.stream.Stream;

public class ClearCommandParser implements Parser<ClearCommand> {
    public static final int PERSON_MODE = 0;
    public static final int EVENT_MODE = 1;

    public ClearCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, FLAG_PERSON, FLAG_EVENT);

        boolean isClearingPerson = arePrefixesPresent(argMultimap, FLAG_PERSON);
        boolean isClearingEvent = arePrefixesPresent(argMultimap, FLAG_EVENT);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearCommand.MESSAGE_USAGE));
        } else if (isClearingPerson && isClearingEvent) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearCommand.MESSAGE_USAGE));
        } else if (!isClearingPerson && !isClearingEvent) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearCommand.MESSAGE_USAGE));
        }
        if (isClearingPerson) {
            String mode = argMultimap.getValue(FLAG_PERSON).get();
            return new ClearCommand(PERSON_MODE, mode);
        } else {
            String mode = argMultimap.getValue(FLAG_EVENT).get();
            return new ClearCommand(EVENT_MODE, mode);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

