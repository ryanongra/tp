package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_EVENT_NAME = new Prefix("ev/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_TELEGRAM = new Prefix("t/");
    public static final Prefix PREFIX_TAG = new Prefix("tag/");
    public static final Prefix PREFIX_ALIAS = new Prefix("a/");
    public static final Prefix PREFIX_COMMAND = new Prefix("c/");
    public static final Prefix PREFIX_EVENT_FLAG = new Prefix("-e ");
    public static final Prefix PREFIX_PERSON_FLAG = new Prefix("-p ");
}
