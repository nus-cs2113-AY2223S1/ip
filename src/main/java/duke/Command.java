package duke;

/**
 * Provides the different commands which can be given by the user,
 * so that the Parser can parse and interpret and
 * TaskList can execute the respective commands accordingly.
 */
public enum Command {
    TODO, EVENT, DEADLINE, LIST, MARK, UNMARK, DELETE, EXIT, INVALID, FIND
}
