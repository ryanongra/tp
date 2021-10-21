package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddPersonToEventCommand;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventNameEqualKeywordPredicate;
import seedu.address.model.person.NameEqualKeywordPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EventBuilder;
import seedu.address.testutil.PersonBuilder;

class AddPersonToEventCommandParserTest {
    private AddPersonToEventCommandParser parser = new AddPersonToEventCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person person = new PersonBuilder().build();
        Event event = new EventBuilder().build();

        AddPersonToEventCommand expectedCommand = new AddPersonToEventCommand(
                new NameEqualKeywordPredicate(person.getName()),
                new EventNameEqualKeywordPredicate(event.getEventName()));
        assertParseSuccess(parser, " n/" + person.getName() + " e/" + event.getEventName(), expectedCommand);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPersonToEventCommand.MESSAGE_USAGE);

        Person person = new PersonBuilder().build();
        Event event = new EventBuilder().build();

        assertParseFailure(parser, " n/" + person.getName(), expectedMessage);
        assertParseFailure(parser, " e/" + event.getEventName(), expectedMessage);
    }
}
