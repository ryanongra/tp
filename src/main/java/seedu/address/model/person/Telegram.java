package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTelegram(String)}
 */
public class Telegram {

    public static final String MESSAGE_CONSTRAINTS = "Telegram handles can take any values, and it should not be "
            + "blank or contain any whitespace.";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public static final String UNSPECIFIED_INPUT = "No telegram handle specified";

    public final String value;

    /**
     * Constructs an {@code Telegram}.
     *
     * @param telegram A valid telegram handle.
     */
    public Telegram(String telegram) {
        requireNonNull(telegram);
        if (telegram.equals(UNSPECIFIED_INPUT)) {
            value = UNSPECIFIED_INPUT;
        } else {
            checkArgument(isValidTelegram(telegram), MESSAGE_CONSTRAINTS);
            value = telegram.charAt(0) == '@' ? telegram : "@" + telegram;
        }
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidTelegram(String test) {
        return test.matches(VALIDATION_REGEX) && !test.contains(" ");
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Telegram // instanceof handles nulls
                && value.equals(((Telegram) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
