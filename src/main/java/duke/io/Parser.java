package duke.io;

import duke.Duke;
import duke.error.exceptions.NoCommandArgumentException;
import duke.error.exceptions.TooManyWordsException;
import duke.error.exceptions.UnneededArgumentsException;
import duke.error.exceptions.subcommand.NoSubCommandArgumentException;
import duke.error.exceptions.subcommand.NoSubCommandException;
import duke.error.exceptions.subcommand.TooManySubCommandsException;
import duke.tasks.tasktypes.DeadlineTask;
import duke.tasks.tasktypes.EventTask;

/**
 * Class for input validation and string parsing related to input.
 */
public class Parser {
    /**
     * Checks if the keyword {@link Duke#COMMAND_TERMINATE} is present at front of input string
     *
     * @param input input string to check
     * @return boolean, true or false
     * @throws UnneededArgumentsException When argument is given but none are required.
     */
    public static boolean isTerminatingInput(String input) throws UnneededArgumentsException {
        return stringContainsEnforceNoArgs(input, Duke.COMMAND_TERMINATE);
    }

    /**
     * Checks if the keyword {@link Duke#COMMAND_LIST} is present at front of input string
     *
     * @param input input string to check
     * @return boolean, true or false
     * @throws UnneededArgumentsException When argument is given but none are required.
     */
    public static boolean isListInput(String input) throws UnneededArgumentsException {
        return stringContainsEnforceNoArgs(input, Duke.COMMAND_LIST);
    }

    /**
     * Makes sure that given input has no arguments after the command
     * and check if input and command are equal.
     *
     * @param input   input string
     * @param command command string
     * @return true if {@code input} is equal to {@code command}.
     * @throws UnneededArgumentsException When argument is given but none are required.
     */
    private static boolean stringContainsEnforceNoArgs(String input, String command) throws
            UnneededArgumentsException {
        if (input.equalsIgnoreCase(command)) {
            return true;
        } else if (stringContains(input, command)) {
            throw new UnneededArgumentsException(command);
        } else {
            return false;
        }
    }

    /**
     * Checks if the given input string contains an adding command <br>
     * ({@link Duke#COMMAND_DEADLINE}, {@link Duke#COMMAND_EVENT}, {@link Duke#COMMAND_TODO})
     *
     * @param input input string
     * @return true if an add command is the first word in input string, false otherwise
     * @throws NoCommandArgumentException    If the command has no argument after it
     * @throws NoSubCommandArgumentException If a subcommand has no argument after it
     *                                       (for {@link DeadlineTask} and {@link EventTask})
     * @throws NoSubCommandException         If a subcommand is not found
     * @throws TooManySubCommandsException   If there is more than one subcommand in the input
     */
    public static boolean isAddInput(String input) throws
            NoCommandArgumentException, NoSubCommandArgumentException,
            NoSubCommandException, TooManySubCommandsException {

        String firstWord = input.split(Duke.STRING_DELIMITER, 2)[0];
        String[] commands = {Duke.COMMAND_TODO, Duke.COMMAND_DEADLINE, Duke.COMMAND_EVENT};

        if (stringEquals(input, commands)) {
            throw new NoCommandArgumentException(firstWord);
        } else if (stringContains(input, Duke.COMMAND_TODO)) {
            return true;
        } else if (stringContains(input, Duke.COMMAND_DEADLINE)) {
            return hasValidSubCommand(input, DeadlineTask.COMMAND_BY);
        } else if (stringContains(input, Duke.COMMAND_EVENT)) {
            return hasValidSubCommand(input, EventTask.COMMAND_AT);
        }
        return false;
    }

    /**
     * Checks if a given subcommand is present in input string.
     *
     * @param input      given input string
     * @param subCommand subcommand to check against
     * @return true if the subcommand is present and valid, false if not
     * @throws NoCommandArgumentException    If the command has no argument after it
     * @throws NoSubCommandArgumentException If a subcommand has no argument after it
     *                                       (for {@link DeadlineTask} and {@link EventTask})
     * @throws NoSubCommandException         If a subcommand is not found
     * @throws TooManySubCommandsException   If there is more than one subcommand in the input
     */
    private static boolean hasValidSubCommand(String input, String subCommand) throws
            NoCommandArgumentException, NoSubCommandArgumentException,
            NoSubCommandException, TooManySubCommandsException {

        String firstWord = input.split(Duke.STRING_DELIMITER)[0];
        if (input.toLowerCase().contains(subCommand)) {
            return validateCommand(input, firstWord, subCommand);
        } else {
            throw new NoSubCommandException(firstWord, subCommand);
        }
    }

    /**
     * Makes sure give input string is valid for a string that has both a command and
     * subcommand.
     *
     * @param input      input string
     * @param command    command string
     * @param subCommand subcommand string
     * @return true if input string is valid, false if not
     * @throws NoSubCommandArgumentException If a subcommand has no argument after it
     *                                       (for {@link DeadlineTask} and {@link EventTask})
     * @throws TooManySubCommandsException   If there is more than one subcommand in the input
     * @throws NoCommandArgumentException    If the command has no argument after it
     */
    private static boolean validateCommand(String input, String command, String subCommand) throws
            NoSubCommandArgumentException, TooManySubCommandsException,
            NoCommandArgumentException {
        int length = splitCount(input, subCommand);
        if (length == 2) {
            // both command and subcommand are present
            if (input.split(subCommand, 2)[1].contains(subCommand)) {
                // edge case where one subcommand is in the middle and one is at the end
                throw new TooManySubCommandsException(command, subCommand);
            }
            if (!input.replace(command, "").strip().split(subCommand)[0].isEmpty()) {
                // subcommand is not at beginning of string, valid argument for command
                return true;
            } else {
                throw new NoCommandArgumentException(command);
            }
        } else if (length < 2) {
            // subcommand is at end of string, no argument after
            throw new NoSubCommandArgumentException(command, subCommand);
        } else {
            // more than one subcommand
            throw new TooManySubCommandsException(command, subCommand);
        }
    }

    /**
     * Checks if the keyword {@link Duke#COMMAND_MARK} is present at front of input string
     *
     * @param input input string to check
     * @return boolean, true or false
     * @throws NoCommandArgumentException If the command has no argument after it
     * @throws TooManyWordsException      If there is more than one space-separated
     *                                    substring after the command.
     */
    public static boolean isMarkInput(String input) throws
            NoCommandArgumentException, TooManyWordsException {
        return stringContainsEnforceOneWord(input, Duke.COMMAND_MARK);
    }

    /**
     * Makes sure that the argument is <b>exactly one</b> space-separated word after
     * the command and checks if input contains command.
     *
     * @param input   input string
     * @param command command to check equality against
     * @return true if input has command as the first word
     * @throws NoCommandArgumentException When no command argument is given
     * @throws TooManyWordsException      If there is more than one space-separated
     *                                    substring after the command.
     */
    public static boolean stringContainsEnforceOneWord(String input, String command) throws
            NoCommandArgumentException, TooManyWordsException {
        if (input.equalsIgnoreCase(command)) {
            throw new NoCommandArgumentException(command);
        } else if (stringContains(input, command)) {
            if (splitCount(input, Duke.STRING_DELIMITER) != 2) {
                throw new TooManyWordsException(command);
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * Checks if the keyword {@link Duke#COMMAND_UNMARK} is present at front of input string
     *
     * @param input input string to check
     * @return boolean, true or false
     * @throws NoCommandArgumentException When no command argument is given
     * @throws TooManyWordsException      If there is more than one space-separated
     *                                    substring after the command.
     */
    public static boolean isUnmarkInput(String input) throws
            NoCommandArgumentException, TooManyWordsException {
        return stringContainsEnforceOneWord(input, Duke.COMMAND_UNMARK);
    }

    /**
     * Checks if the keyword {@link Duke#COMMAND_DELETE} is present at front of input string
     *
     * @param input input string to check
     * @return boolean, true or false
     * @throws NoCommandArgumentException When no command argument is given
     * @throws TooManyWordsException      If there is more than one space-separated
     *                                    substring after the command.
     */
    public static boolean isDeleteInput(String input) throws
            NoCommandArgumentException, TooManyWordsException {
        return stringContainsEnforceOneWord(input, Duke.COMMAND_DELETE);
    }

    /**
     * Checks if the keyword {@link Duke#COMMAND_FIND} is present at front of input string
     *
     * @param input input string to check
     * @return boolean, true or false
     * @throws NoCommandArgumentException When no command argument is given
     * @throws TooManyWordsException      If there is more than one space-separated
     *                                    substring after the command.
     */
    public static boolean isFindInput(String input) throws NoCommandArgumentException, TooManyWordsException {
        if (stringEquals(input, Duke.COMMAND_FIND)) {
            throw new NoCommandArgumentException(Duke.COMMAND_FIND);
        } else {
            return stringContains(input, Duke.COMMAND_FIND);
        }
    }

    /**
     * Check if the given input contains a substring at the start
     *
     * @param input     input to be checked
     * @param substring substring to be checked if it is contained in input
     * @return boolean true or false
     */
    public static boolean stringContains(String input, String substring) {
        return input.split(Duke.STRING_DELIMITER)[0].equalsIgnoreCase(substring);
    }

    /**
     * Check if the given input is equal to any substring in a given array
     *
     * @param input      input to be checked
     * @param substrings array of substrings to be checked for equality
     * @return boolean true or false
     */
    public static boolean stringEquals(String input, String[] substrings) {
        for (String substring : substrings) {
            if (input.equalsIgnoreCase(substring)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the given input is equal to any substring in a given array
     *
     * @param input     input to be checked
     * @param substring substrings to be checked for equality
     * @return boolean true or false
     */
    public static boolean stringEquals(String input, String substring) {
        return input.equalsIgnoreCase(substring);
    }

    /**
     * Checks if given input string is an integer.
     *
     * @param input input string to be checked
     * @return true if input string can be parsed as an integer, false if not
     */
    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Counts the length of an array after a {@link String#split} operation.
     *
     * @param input     input string to be split
     * @param separator substring that input should be split with
     * @return length of split array
     */
    public static int splitCount(String input, String separator) {
        return input.split(separator).length;
    }


}
