package duke.parser;

import duke.exceptions.*;
import duke.exceptions.NumberFormatException;
import duke.tasklist.Tasklist;
import duke.command.Command;
import duke.ui.Ui;

/**
 * Class used to deal with making sense of the user command
 */
public class Parser {

    public Parser() {

    }

    /**
     * Split the input command into command and taskDescription respectively
     *
     * @param fullCommand is the user input command
     * @return Command class with command and taskDescription params respectively
     * @throws MissingArgumentException when there is insufficient/no task description
     * @throws InvalidCommandException when the input command is invalid
     */
   public Command parse(String fullCommand) throws MissingArgumentException, InvalidCommandException {
       String[] ArrCommand = fullCommand.split(" ", 2);
       if (ArrCommand.length == 2) {
           String command = ArrCommand[0];
           String taskDescription = ArrCommand[1];
           return new Command(command, taskDescription);
       }
       return new Command(fullCommand, "");
   }

    /**
     * Convert the string to integer
     *
     * @param argument task description
     * @return the task number in the list
     * @throws InvalidTaskNoException when the task_no does not exist in the list
     */
    public static int parseMark(String argument) throws InvalidTaskNoException {
        int task_no = Integer.parseInt(argument) - 1;
        if (task_no >= Tasklist.tasks.size()) {
            throw new InvalidTaskNoException();
        }
       return task_no;
    }

    /**
     * Convert the string to integer
     *
     * @param argument task description
     * @return the task number in the list
     * @throws InvalidTaskNoException when the task_no does not exist in the list
     */
    public static int parseUnmark(String argument) throws InvalidTaskNoException {
        int task_no = Integer.parseInt(argument) - 1;
        if (task_no >= Tasklist.tasks.size()) {
            throw new InvalidTaskNoException();
        }
        return task_no;
    }

    /**
     * Convert the string to integer
     *
     * @param argument task description
     * @return the task number in the list
     * @throws InvalidTaskNoException when the task_no does not exist in the list
     */
    public static int parseDelete(String argument) throws InvalidTaskNoException {
        int task_no = Integer.parseInt(argument) - 1;
        if (task_no >= Tasklist.tasks.size()) {
            throw new InvalidTaskNoException();
        }
        return task_no;
    }

    /**
     * Method called to get the deadline description
     *
     * @param taskDescription deadline description + deadline date
     * @return deadline description
     * @throws MissingArgumentException when there is no task description
     * @throws InvalidArgumentException when keyword "/by" is missing in the user input command
     */
    public static String parseDeadline(String taskDescription) throws MissingArgumentException, InvalidArgumentException {
        if (!taskDescription.contains("/by ")) {
            throw new InvalidArgumentException();
        }
        String description = taskDescription.split("/by ")[0];
        if (description == "") {
            throw new MissingArgumentException();
        }
        return description;
    }

    /**
     * Method called to get the deadline date
     *
     * @param taskDescription deadline description + deadline date
     * @return deadline date
     * @throws MissingDateException when there is no task date
     * @throws InvalidArgumentException when keyword "/by" is missing in the user input command
     */
    public static String parseDeadlineDate(String taskDescription) throws MissingDateException, InvalidArgumentException {
        if (!taskDescription.contains("/by ")) {
            throw new InvalidArgumentException();
        }
        String date = taskDescription.split("/by ")[1];
        if (date == "") {
            throw new MissingDateException();
        }
        return date;
    }

    /**
     * Method called to get the event description
     *
     * @param taskDescription event description + event date
     * @return event description
     * @throws MissingArgumentException when there is no event description
     * @throws InvalidArgumentException when keyword "/at" is missing in the user input command
     */
    public static String parseEvent(String taskDescription) throws MissingArgumentException, InvalidArgumentException {
        if (!taskDescription.contains("/at ")) {
            throw new InvalidArgumentException();
        }
        String description = taskDescription.split("/at ")[0];
        if (description == "") {
            throw new MissingArgumentException();
        }
        return description;
    }

    /**
     * Method called to get the event date
     *
     * @param taskDescription event description + event date
     * @return event date
     * @throws MissingDateException when there is no event date
     * @throws InvalidArgumentException when keyword "/at" is missing in the user input command
     */
    public static String parseEventDate(String taskDescription) throws MissingDateException, InvalidArgumentException {
        if (!taskDescription.contains("/at ")) {
            throw new InvalidArgumentException();
        }
        String date = taskDescription.split("/at ")[1];
        if (date == "") {
            throw new MissingDateException();
        }
        return date;
    }

}
