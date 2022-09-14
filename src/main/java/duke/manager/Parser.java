package duke.manager;

import duke.command.Command;
import duke.task.TaskList;
import duke.exception.ExceptionChecker;

import java.util.Arrays;

public class Parser {

    /**
     * Returns the keyword of the command in lowercase
     *
     * @param input the entire command just read in by the scanner
     * @return the keyword of the command in lowercase
     */
    public static String getKeyword(String input) {
        String[] splitInput = input.split(" ");
        return splitInput[0].toLowerCase();
    }

    public static String getDescription(String input, int flagPosition) {

        String[] splitInput = input.split(" ");
        // todo
        if (flagPosition < 0) {
            String[] truncatedInput = Arrays.copyOfRange(splitInput, 1, splitInput.length);
            return String.join(" ", truncatedInput);
        }   else { // deadline + event
            String[] splitDescription = Arrays.copyOfRange(splitInput, 1, flagPosition);
            return String.join(" ", splitDescription);
        }
    }

    public static String getTime(String input, int flagPosition) {

        String[] splitInput = input.split(" ");
        String[] splitTime = Arrays.copyOfRange(splitInput, flagPosition + 1, splitInput.length);
        return String.join(" ", splitTime);
    }

    public static String getTaskPosition(String input) {

        String[] splitInput = input.split(" ");
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

        String[] splitInput = input.split(" ");
        switch (keyword) {
        case "deadline":
            return searchArray(splitInput, "/by");
        case "event":
            return searchArray(splitInput, "/at");
        default:
            return -1;
        }
    }

    public static Command parse(TaskList taskList, Command c, String input) {

        // parse keyword portion
        String keyword = getKeyword(input);
        c.setKeyword(keyword);
        String time = "";
        String description = "";
        String taskPosition;
        int flagPosition;

        switch (keyword) {
        case "bye":
        case "list":
            if (ExceptionChecker.isExceptionFree(taskList, input, description, time, keyword)) {
                break;
            }
        case "mark":
        case "unmark":
        case "delete":
            taskPosition = getTaskPosition(input);
            if (ExceptionChecker.isExceptionFree(taskList, input, taskPosition, time, keyword)) {
                c.setArgument(taskPosition, 0);
                break;
            }
        case "todo":
            description = getDescription(input, -1);
            if (ExceptionChecker.isExceptionFree(taskList, input, description, time, keyword)) {
                c.setArgument(description, 0);
                break;
            }
        case "event":
        case "deadline":
            flagPosition = getFlagPosition(input, keyword);
            description = getDescription(input, flagPosition);
            time = getTime(input, flagPosition);
            if (ExceptionChecker.isExceptionFree(taskList, input, description, time, keyword)) {
                c.setArgument(description, 0);
                c.setArgument(time, 1);
                break;
            }
        default:
            c.setLegal(false);
        }
        return c;
    }
}
