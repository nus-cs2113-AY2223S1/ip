package duke.parser;

import duke.exception.DukeException;
import duke.exception.InvalidTaskDescriptionException;

public class Parser {
    // parse keyword from user input
    public static String getKeyword(String input) {
        String[] words = input.split(" ");
        return words[0];
    }

    public static String getTaskDescription (String line) throws DukeException {
        String[] breakLine = line.trim().split(" ", 2);
        // // if there is no task description or the task description is empty
        if (breakLine.length == 1 || breakLine[1].equals("")) {
            throw new InvalidTaskDescriptionException("☹ OOPS!!! The description of a task cannot be empty.");
        } return breakLine[1];
    }

    // get Detail name before /
    public static String getDetailName(String details) {
        String[] splitInput = details.split("/");
        return splitInput[0];
    }

    // parse deadline description into details and by
    public static String[] parseDeadlineDetails(String description) throws DukeException {
        String[] split = description.split(" /by ");
        return split;
    }

    // parse event description into details and at
    public static String[] parseEventDetails(String description) throws     DukeException {
        String[] split = description.split(" /at ");
        return split;
    }

    public static int getTaskId(String input) {
        int inputId = Integer.parseInt(input.replaceAll("[^0-9]", ""));    // gets the id
        return (inputId - 1);
    }

    
}
