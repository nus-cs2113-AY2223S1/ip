package duke.input;

import duke.Duke;
import duke.error.exceptions.*;
import duke.tasks.tasktypes.DeadlineTask;
import duke.tasks.tasktypes.EventTask;

public class InputValidator {
    /**
     * Checks if the keyword {@link Duke#COMMAND_TERMINATE} is present at front of input string
     *
     * @param input input string to check
     * @return boolean, true or false
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
     * Checks if the keywords {@link Duke#COMMAND_DEADLINE},  {@link Duke#COMMAND_EVENT}
     * or {@link Duke#COMMAND_TODO} is present at front of input string
     *
     * @param input input string to check
     * @return boolean, true or false
     */
    public static boolean isAddInput(String input) throws
            NoCommandArgumentException,
            NoSubCommandArgumentException,
            TooManySubCommandsException, NoSubCommandException {

        String firstWord = input.split(" ", 2)[0];
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

    private static boolean hasValidSubCommand(String input, String subCommand) throws
            NoSubCommandArgumentException,
            TooManySubCommandsException, NoSubCommandException, NoCommandArgumentException {

        String firstWord = input.split(" ")[0];
        if (input.toLowerCase().contains(subCommand)) {
            return validateLength(input, firstWord, subCommand);
        } else {
            throw new NoSubCommandException(firstWord, subCommand);
        }
    }

    private static boolean validateLength(String input, String command, String subCommand) throws
            NoSubCommandArgumentException, TooManySubCommandsException, NoCommandArgumentException {
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
     */
    public static boolean stringContainsEnforceOneWord(String input, String command) throws
            NoCommandArgumentException, TooManyWordsException {
        if (input.equalsIgnoreCase(command)) {
            throw new NoCommandArgumentException(command);
        } else if (stringContains(input, command)){
            if (splitCount(input, " ") != 2) {
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
     */
    public static boolean isUnmarkInput(String input) throws
            NoCommandArgumentException, TooManyWordsException {
        return stringContainsEnforceOneWord(input, Duke.COMMAND_UNMARK);
    }

    /**
     * Check if the given input contains a substring at the start
     *
     * @param input     input to be checked
     * @param substring substring to be checked if it is contained in input
     * @return boolean true or false
     */
    public static boolean stringContains(String input, String substring) {
        return input.split(" ")[0].equalsIgnoreCase(substring);
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

    public static boolean isNumber(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static int splitCount(String input, String separator) {
        return input.split(separator).length;
    }
}
