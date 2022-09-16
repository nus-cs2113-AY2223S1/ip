package duke;

import duke.exceptions.DukeException;

/**
 * Class that handles user inputs and carries out the appropriate action
 * Throws relevant errors
 */
public class Parser {

    /**
     * Method to handle the user input. Splits "input" into the command
     * @param input user input
     * @param myTaskList task manager to carry out the tasks
     */
    public static void handleInput(String input, TaskList myTaskList) {
        String command;
        if (input.contains(" ")) {
            command = input.substring(0, input.indexOf(" "));
        } else {
            command = input;
        }
        try {
            handleInput(input, command, myTaskList);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method to handle the user input and carry out the relevant method
     * @param input user input
     * @param command the command from the user
     * @param myTaskList task manager to carry out the tasks
     * @throws DukeException if command is unknown or invalid
     */
    public static void handleInput(String input, String command, TaskList myTaskList) throws DukeException {
        switch (command) {
        case "list":
            myTaskList.print();
            break;
        case "mark":
            myTaskList.markAsDone(input);
            break;
        case "unmark":
            myTaskList.removeMark(input);
            break;
        case "todo":
            myTaskList.addTodo(input);
            break;
        case "deadline":
            myTaskList.addDeadline(input);
            break;
        case "event":
            myTaskList.addEvent(input);
            break;
        case "delete":
            myTaskList.deleteTask(input);
            break;
        case "find":
            myTaskList.findTask(input);
            break;
        default:
            throw new DukeException();
        }
    }
}
