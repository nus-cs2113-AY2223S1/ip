package duke.parser;

import duke.exception.DukeException;
import duke.exception.EmptyTaskDescriptionException;

public class Parser {
    public static String parseCommand(String input) {
        String[] splitInput = input.split(" ");
        return splitInput[0];
    }

    public static String parseTaskDetails (String line) throws DukeException {
        String[] breakLine = line.split(" ", 2);
        // if there is no task description
        if (breakLine.length == 1 || breakLine[1].isBlank()) {
            throw new EmptyTaskDescriptionException("☹ OOPS!!! The description of a task cannot be empty.");
        }
        return breakLine[1];
    }
    
    public static String parseDate(String input) {
        String[] splitInput = input.split(" ");
        String date = "";
        for (int i = 3; i < splitInput.length; i++) {
            date += splitInput[i] + " ";
        }
        return date;
    }

    public static String parseIndex(String input) {
        String[] splitInput = input.split(" ");
        return splitInput[1];
    }

    public static int parseTaskId(String input) {
        int inputId = Integer.parseInt(input.replaceAll("[^0-9]", ""));    // gets the id
        return (inputId - 1);
    }
}
