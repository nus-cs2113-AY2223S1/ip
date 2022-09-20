package duke.ui.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import duke.exception.*;

public abstract class Command {
    /**
     * Available command types
     */
    public enum CommandType {
        EXIT, LIST, MARK, UNMARK, DEADLINE, EVENT, TODO, DELETE, FIND
    }

    protected CommandType commandType;
    protected Integer minArguments = null;
    protected Integer maxArguments = null;
    protected ArrayList<String> flags;
    protected String rawArguments;
    protected ArrayList<String> splitArguments;


    /**
     * Checks the number of arguments in the raw user input.
     * rawArguments is a substring of the raw user input without the command type. This method ignores the text content
     * and counts the number of tokens separated by whitespaces.
     * @throws DukeMissingArgumentException If there are not enough tokens
     * @throws DukeExtraArgumentException If there are too many tokens
     * @throws DukeMissingDescriptionException If the task description
     */
    protected void checkArgumentLength() throws DukeMissingArgumentException, DukeExtraArgumentException,
            DukeMissingDescriptionException {
        if (flags.size() > 0 && rawArguments.indexOf(flags.get(0)) == 0) {
            throw new DukeMissingDescriptionException();
        }

        if (maxArguments != null && splitArguments.size() > maxArguments) {
            throw new DukeExtraArgumentException(rawArguments);
        }

        if (minArguments != null && splitArguments.size() < minArguments) {
            throw new DukeMissingArgumentException(rawArguments);
        }

    }


    /**
     * Checks if the command's flags are present
     * @throws DukeMissingFlagException If a flag is absent
     * @throws DukeMissingArgumentException If any of the flags has no arguments that follow it
     */
    protected void checkFlags() throws DukeMissingFlagException, DukeMissingArgumentException {
        for (int i = 0; i < flags.size(); i++) {
            String flag = flags.get(i);

            int indexOfFlag = rawArguments.indexOf(flag);
            if (indexOfFlag == -1) {
                throw new DukeMissingFlagException(flag);
            }

            if (i + 1 >= flags.size()) {
                break;
            }

            int indexOfNextFlag = rawArguments.indexOf(flags.get(i + 1));

            if ((indexOfFlag + flag.length() >= rawArguments.length()) ||
                (indexOfFlag + flag.length() >= indexOfNextFlag)) {
                throw new DukeMissingArgumentException(rawArguments);
            }
        }
    }

    /**
     * Helper method to split the argument portion of the raw user input around spaces to form tokens
     * @param rawArguments The argument portion of the raw user input
     * @return List of tokens in the raw arguments
     */
    protected ArrayList<String> splitArguments(String rawArguments) {
        //@@Author CodeVsColor
        //Reused from https://www.codevscolor.com/java-remove-empty-values-while-split
        //with minor modifications
        return Arrays.stream(rawArguments.split(" "))
                .filter(e -> e.trim().length() > 0)
                .collect(Collectors.toCollection(ArrayList::new));

        //@@author
    }

    /**
     * Checks the argument type
     * @throws DukeNotIntegerException If an integer argument is expected but not provided
     */
    protected abstract void checkArgumentType() throws DukeNotIntegerException;

    /**
     * Converts saves the arguments into the command's attributes
     * @throws DukeDateTimeFormatException If the date and time entered do not follow the format in DukeDateTime
     *                                     or the year, month, day, hours or minutes are out of the logical range
     */
    protected abstract void parse() throws DukeDateTimeFormatException;

    /**
     * Getter for command type
     * @return Command type
     */
    public CommandType getCommandType() {
        return commandType;
    }

    /**
     * Checks the correctness of and parses the raw user input
     * @throws Exception Many exceptions
     */
    public void verifyAndParse() throws Exception {
        checkArgumentLength();
        checkFlags();
        checkArgumentType();
        parse();
    }

}
