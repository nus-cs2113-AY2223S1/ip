package duke.manager;

import duke.command.Command;
import duke.exception.MissingArgumentException;
import duke.exception.TooManyArgumentsException;

import java.util.Arrays;

/**
 * This class deals with making sense of the user command.
 */
public class CommandParser {

    private static final String EMPTY_STRING = "";
    private static final String SPACES_BETWEEN_WORDS = " ";

    /**
     * Returns the keyword of the command in lowercase.
     *
     * @param input the user command that was loaded or read by the scanner
     * @return the keyword of the command in lowercase
     */
    public static String getKeyword(String input) {
        String[] splitInput = input.split(SPACES_BETWEEN_WORDS);
        return splitInput[0].toLowerCase();
    }

    /**
     * Parses the description in the user command and returns it.
     *
     * @param input the user command read in by the scanner
     * @param flagPosition where the "/by" or "/at" flag is in the command, if present
     * @return the parsed description of the command
     * @throws IndexOutOfBoundsException If there are missing arguments or if the flag does not exist
     */
    public static String getDescription(String input, int flagPosition) throws IndexOutOfBoundsException {
        String[] splitInput = input.split(SPACES_BETWEEN_WORDS);
        if (flagPosition < 0) { // todo
            String[] truncatedInput = Arrays.copyOfRange(splitInput, 1, splitInput.length);
            return String.join(SPACES_BETWEEN_WORDS, truncatedInput);
        }   else { // deadline + event
            String[] splitDescription = Arrays.copyOfRange(splitInput, 1, flagPosition);
            return String.join(SPACES_BETWEEN_WORDS, splitDescription);
        }
    }

    /**
     * Parses the time in the user command and returns it.
     *
     * @param input the user command read in by the scanner
     * @param flagPosition where the "/by" or "/at" flag is in the command, if present
     * @return the parsed time of the command
     * @throws MissingArgumentException If the user gave a command with missing time field
     */
    public static String getTime(String input, int flagPosition) throws MissingArgumentException {
        if (flagPosition < 1) {
            throw new MissingArgumentException("correct time flag");
        }

        String[] splitInput = input.split(SPACES_BETWEEN_WORDS);
        String[] splitTime = Arrays.copyOfRange(splitInput, flagPosition + 1, splitInput.length);
        return String.join(SPACES_BETWEEN_WORDS, splitTime);
    }

    /**
     * Returns the second word/argument of the command, which is after the word that
     * specifies the type of command.
     *
     * @param input the user command read in by the scanner
     * @return the second word/argument of the command
     */
    public static String getTaskArgument(String input) {
        String[] splitInput = input.split(SPACES_BETWEEN_WORDS);
        return splitInput[1];
    }

    /**
     * Checks if an argument in a command is empty. Returns true if empty and
     * false if not empty.
     *
     * @param argument an argument of a command to be checked
     * @return true if empty, false
     */
    public static boolean isStringEmpty(String argument) {
        return argument.equals(EMPTY_STRING);
    }

    /**
     * Takes a string and converts it into an integer.
     *
     * @param argument the string to convert to an integer
     * @return the integer in the string
     * @throws MissingArgumentException If the user did not provide enough arguments
     * @throws NumberFormatException If the user did not provide an integer when they should
     */
    public static int returnAsInt(String argument) throws MissingArgumentException, NumberFormatException {
        if (isStringEmpty(argument)) {
            throw new MissingArgumentException("task position");
        }
        return Integer.parseInt(argument);
    }

    /**
     * Searches a given string array for a word and returns the index if found. If not found
     * return -1.
     *
     * @param strings the string array to search in
     * @param target the string to search for
     * @return the index of the string if found
     */
    public static int searchArray(String[] strings, String target) {
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].toLowerCase().equals(target)) {
                return i;
            }
        }
        return -1; //not found
    }

    /**
     * Finds "/by" or "/at" flag in the user command depending on the type of command
     * and returns the index position of the flag.
     *
     * @param keyword the type of command
     * @param input the user command read in by the scanner
     * @return the index position of the flag in the command
     */
    public static int getFlagPosition(String keyword, String input) {
        String[] splitInput = input.split(SPACES_BETWEEN_WORDS);
        switch (keyword) {
        case "deadline":
            return searchArray(splitInput, "/by");
        case "event":
            return searchArray(splitInput, "/at");
        default:
            // if not found
            return -1;
        }
    }

    /**
     * Parse a command for information and stores it in the command object.
     *
     * @param command command object that is being parsed
     * @param input the user command read in by the scanner
     * @throws TooManyArgumentsException If the user provided too many arguments
     * @throws MissingArgumentException If the user did not provide enough arguments
     */
    public static void parse(Command command, String input) throws TooManyArgumentsException,
            MissingArgumentException {
        // parse keyword portion
        String[] splitInput = input.split(SPACES_BETWEEN_WORDS); // for command length testing
        String keyword = getKeyword(input);
        command.setKeyword(keyword);
        String time;
        /* description represents description for todo, deadline, event and task position for
        mark, unmark, delete and the word to search for find
         */
        String description;
        String taskArgument;
        int flagPosition;

        switch (keyword) {
        case "bye":
        case "list":
            if (splitInput.length > 1) {
                throw new TooManyArgumentsException();
            }
            break;
        case "mark":
        case "unmark":
        case "delete":
            if (splitInput.length > 2) {
                throw new TooManyArgumentsException();
            } else if (splitInput.length == 1) {
                throw new MissingArgumentException("integer");
            }
            taskArgument = getTaskArgument(input);
            command.setArgument(taskArgument, 0);
            break;
        case "todo":
            description = getDescription(input, -1);
            command.setArgument(description, 0);
            break;
        case "event":
        case "deadline":
            flagPosition = getFlagPosition(keyword, input);
            description = getDescription(input, flagPosition);
            time = getTime(input, flagPosition);
            command.setArgument(description, 0);
            command.setArgument(time, 1);
            break;
        case "find":
            if (splitInput.length > 2) {
                throw new TooManyArgumentsException();
            } else if (splitInput.length == 1) {
                throw new MissingArgumentException("keyword");
            }
            taskArgument = getTaskArgument(input);
            command.setArgument(taskArgument, 0);
            break;
        default:
            command.setLegal(false);
        }
    }
}
