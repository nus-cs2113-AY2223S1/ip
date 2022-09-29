package duke.parser;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.EmptyTaskDescriptionException;

public class Parser {
    public static String getCommand(String input) {
        String[] splitInput = input.split(" ");
        return splitInput[0];
    }

    public static String getTaskDetails (String line) throws DukeException {
        String[] breakLine = line.split(" ", 2);
        // // if there is no task description or the task description is empty
        if (breakLine.length == 1 || breakLine[1].equals("")) {
            throw new EmptyTaskDescriptionException("☹ OOPS!!! The description of a task cannot be empty.");
        }

        // if (breakLine[1].isBlank() || breakLine[1].isEmpty()) {
        //     throw new EmptyTaskDescriptionException("☹ OOPS!!! The description of a task cannot be empty.");
        // }
        return breakLine[1];
    }

    public static int getTaskId(String input) {
        int inputId = Integer.parseInt(input.replaceAll("[^0-9]", ""));    // gets the id
        return (inputId - 1);
    }

    // get Detail name before /
    public static String getDetailName(String input) {
        String[] splitInput = input.split("/");
        return splitInput[0];
    }

    // get Detail after /by (for deadline)
    public static String getDetailBy(String input) {
        String[] splitInput = input.split("/by");
        return splitInput[1];
    }

    // get Detail after /at (for event)
    public static String getDetailAt(String input) {
        String[] splitInput = input.split("/at");
        return splitInput[1];
    }

}
