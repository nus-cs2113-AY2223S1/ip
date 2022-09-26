package duke.manager;

import duke.command.Command;
import duke.exception.ExceptionChecker;

import java.util.Arrays;

public class Parser {

    private static String EMPTY_STRING = "";
    private static String SPACES_BETWEEN_WORDS = " ";

    /**
     * Returns the keyword of the command in lowercase
     *
     * @param input the entire command just read in by the scanner
     * @return the keyword of the command in lowercase
     */
    public static String getKeyword(String input) {
        String[] splitInput = input.split(SPACES_BETWEEN_WORDS);
        return splitInput[0].toLowerCase();
    }

    public static String getDescription(String input, int flagPosition) {
        String[] splitInput = input.split(SPACES_BETWEEN_WORDS);
        if (flagPosition < 0) { // todo
            String[] truncatedInput = Arrays.copyOfRange(splitInput, 1, splitInput.length);
            return String.join(SPACES_BETWEEN_WORDS, truncatedInput);
        }   else { // deadline + event
            String[] splitDescription = Arrays.copyOfRange(splitInput, 1, flagPosition);
            return String.join(SPACES_BETWEEN_WORDS, splitDescription);
        }
    }

    public static String getTime(String input, int flagPosition) {
        String[] splitInput = input.split(SPACES_BETWEEN_WORDS);
        String[] splitTime = Arrays.copyOfRange(splitInput, flagPosition + 1, splitInput.length);
        return String.join(SPACES_BETWEEN_WORDS, splitTime);
    }

    public static String getTaskParameter(String input) {
        String[] splitInput = input.split(SPACES_BETWEEN_WORDS);
        return splitInput[1];
    }

    public static int searchArray(String[] s, String target) {
        for (int i = 0; i < s.length; i++) {
            if (s[i].equals(target)) {
                return i;
            }
        }
        return -1; //not found
    }

    public static int getFlagPosition(String input, String keyword) {
        String[] splitInput = input.split(SPACES_BETWEEN_WORDS);
        switch (keyword) {
        case "deadline":
            return searchArray(splitInput, "/by");
        case "event":
            return searchArray(splitInput, "/at");
        default:
            return -1;
        }
    }

    public static Command parse(Command command, String input) {
        // parse keyword portion
        String keyword = getKeyword(input);
        command.setKeyword(keyword);
        String time = EMPTY_STRING;
        /* description represents description for todo, deadline, event and task position for
        mark, unmark, delete and the word to search for find
         */
        String description = EMPTY_STRING;
        String taskParameter;
        String lookingFor;
        int flagPosition;

        switch (keyword) {
        case "bye":
        case "list":
            if (ExceptionChecker.isExceptionFree(input, description, time, keyword)) {
                break;
            }
        case "mark":
        case "unmark":
        case "delete":
        case "find":
            taskParameter = getTaskParameter(input);
            if (ExceptionChecker.isExceptionFree(input, taskParameter, time, keyword)) {
                command.setArgument(taskParameter, 0);
                break;
            }
        case "todo":
            description = getDescription(input, -1);
            if (ExceptionChecker.isExceptionFree(input, description, time, keyword)) {
                command.setArgument(description, 0);
                break;
            }
        case "event":
        case "deadline":
            flagPosition = getFlagPosition(input, keyword);
            description = getDescription(input, flagPosition);
            time = getTime(input, flagPosition);
            if (ExceptionChecker.isExceptionFree(input, description, time, keyword)) {
                command.setArgument(description, 0);
                command.setArgument(time, 1);
                break;
            }
        default:
            command.setLegal(false);
        }
        return command;
    }
}
