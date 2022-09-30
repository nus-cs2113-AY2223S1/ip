package duke.parser;

import duke.exception.EmptyInputException;
import duke.exception.EmptyNumberInputException;
import duke.exception.InvalidDeadlineInputException;
import duke.exception.InvalidEventInputException;
import duke.exception.InvalidTaskDescriptionException;

/**
 * Class contains methods to parse the user input.
 */
public class Parser {
    public static String getKeyword(String fullCommand) throws EmptyInputException {
        String[] words = fullCommand.split(" ");
        // if input is empty or only contains spaces 
        if (words.length == 0) {
            throw new EmptyInputException("Input cannot be empty!");
        }
        return words[0];
    }

    public static String getDescription(String fullCommand) throws InvalidTaskDescriptionException {
        String[] breakLine = fullCommand.trim().split(" ", 2);
        if (breakLine.length == 1 || breakLine[1].isBlank()) {
            throw new InvalidTaskDescriptionException("Please enter a valid todo description");
        }
        return breakLine[1];
    }

    // This is used only for Deadline and Event; to parse the description into name and the date/time.
    public static String removeCommand(String input) {
        String[] words = input.split(" ", 2);
        return words[1];
    }

    /**
     * Firstly, remove the keyword from the input.
     * Then, split the description into two parts, the name and the date.
     * If the name or date is of an invalid format, throw an exception.
     * @param fullCommand The entire user input. 
     * @return Returns an array of the name and date of the deadline.
     * @throws InvalidDeadlineInputException If the name or date is of an invalid format or empty.
     */
    public static String[] parseDeadlineDescription(String fullCommand) throws InvalidDeadlineInputException {
        String description = removeCommand(fullCommand);
        String[] split = description.split(" /by ");
        if (split.length != 2 || split[0].isBlank() || split[0].isEmpty() || split[1].isBlank() || split[1].isEmpty()) {
            throw new InvalidDeadlineInputException("Please enter a valid deadline");
        }
        return split;
    }

    /**
     * Firstly, remove the keyword from the input.
     * Then, split the description into two parts, the name and the event detail.
     * If the name or event detail is of an invalid format, throw an exception.
     * @param fullCommand The entire user input.
     * @return Returns an array of the name and date of the event.
     * @throws InvalidEventInputException If the name or event detail is of an invalid format or empty.
     */
    public static String[] parseEventDescription(String input) throws InvalidEventInputException {
        String description = removeCommand(input);
        String[] split = description.split(" /at ");
        if (split.length != 2 || split[0].isBlank() || split[0].isEmpty() || split[1].isBlank() || split[1].isEmpty()) {
            throw new InvalidEventInputException("Please enter a valid event");
        }
        return split;
    }

    // This is used only for Done and Delete; to parse the number of the task.
    public static int getTaskId(String input) throws EmptyNumberInputException {
        int inputId = Integer.parseInt(input.trim().split(" ")[1]);
        return (inputId - 1);
    }
}
