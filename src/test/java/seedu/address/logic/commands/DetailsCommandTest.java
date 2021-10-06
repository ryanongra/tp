package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSON_DETAILS_FOUND;
import static seedu.address.commons.core.Messages.MESSAGE_PERSON_DETAILS_NOT_FOUND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameEqualKeywordPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code DetailsCommand}.
 */
class DetailsCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        NameEqualKeywordPredicate firstPredicate =
                new NameEqualKeywordPredicate(new Name("first"));
        NameEqualKeywordPredicate secondPredicate =
                new NameEqualKeywordPredicate(new Name("second"));

        DetailsCommand findFirstCommand = new DetailsCommand(firstPredicate);
        DetailsCommand findSecondCommand = new DetailsCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        DetailsCommand findFirstCommandCopy = new DetailsCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String name = "cool name";
        String expectedMessage = String.format(MESSAGE_PERSON_DETAILS_NOT_FOUND, name);
        NameEqualKeywordPredicate predicate = new NameEqualKeywordPredicate(new Name(name));
        DetailsCommand command = new DetailsCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        Name name = CARL.getName();
        String expectedMessage = String.format(MESSAGE_PERSON_DETAILS_FOUND, name);
        NameEqualKeywordPredicate predicate = new NameEqualKeywordPredicate(name);
        DetailsCommand command = new DetailsCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(CARL), model.getFilteredPersonList());
    }
}
