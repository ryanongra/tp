package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TelegramTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Telegram(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new Telegram(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> Telegram.isValidAddress(null));

        // invalid addresses
        assertFalse(Telegram.isValidAddress("")); // empty string
        assertFalse(Telegram.isValidAddress(" ")); // spaces only

        // valid addresses
        assertTrue(Telegram.isValidAddress("Blk 456, Den Road, #01-355"));
        assertTrue(Telegram.isValidAddress("-")); // one character
        assertTrue(Telegram.isValidAddress("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long address
    }
}
