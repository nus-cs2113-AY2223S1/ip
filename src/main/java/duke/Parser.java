package duke;

import duke.exceptions.DukeException;

public class Parser {
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
        default:
            throw new DukeException();
        }
    }
}
