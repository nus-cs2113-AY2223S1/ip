package handler;

import task.Deadline;
import task.Event;
import task.TaskList;
import task.Todo;
import exception.DukeException;

public class TaskHandler {
    private static final String BYE_MESSAGE = "Bye! ;)";
    public static final int SPLIT_AMOUNT = 2;

    public static void handleBye() {
        System.out.println(BYE_MESSAGE);
    }

    public static void handleTodo(String input) throws DukeException {
        String[] splitInput = input.split(" ", SPLIT_AMOUNT);
        if (splitInput.length != SPLIT_AMOUNT) {
            throw new DukeException();
        }
        Todo todo = new Todo(splitInput[1]);
        TaskList.addTodo(todo);
    }

    public static void handleList() {
        TaskList.printList();
    }

    public static void handleMark(String input) throws DukeException {
        String[] splitInput = input.split(" ", SPLIT_AMOUNT);
        if (splitInput.length != SPLIT_AMOUNT) {
            throw new DukeException();
        }
        try {
            int target = Integer.parseInt(splitInput[1]);
            TaskList.markTarget(target);
        } catch (NullPointerException e) {
            System.out.println("There is no task with that index");
        } catch (NumberFormatException e) {
            System.out.println("Index is a number...");
        }
    }

    public static void handleUnmark(String input) throws DukeException {
        String[] splitInput = input.split(" ", SPLIT_AMOUNT);
        if (splitInput.length != SPLIT_AMOUNT) {
            throw new DukeException();
        }
        try {
            int target = Integer.parseInt(splitInput[1]);
            TaskList.markTarget(target);
        } catch (NullPointerException e) {
            System.out.println("There is no task with that index");
        } catch (NumberFormatException e) {
            System.out.println("Index is a number...");
        }
    }

    public static String[] splitTaskTime(String input) throws DukeException {
        String[] splitInput = input.split(" ", SPLIT_AMOUNT);
        if (splitInput.length != SPLIT_AMOUNT) {
            throw new DukeException();
        }
        return splitInput[1].split(" /by ", SPLIT_AMOUNT);
    }

    public static void handleDeadline(String input) throws DukeException {
        String[] splitCommand = splitTaskTime(input);
        if (splitCommand.length != SPLIT_AMOUNT) {
            throw new DukeException();
        }
        Deadline deadline = new Deadline(splitCommand[0], splitCommand[1]); //index 0 is description, index 1 is time
        TaskList.addDeadLine(deadline);
    }

    public static void handleEvent(String input) throws DukeException {
        String[] splitCommand = splitTaskTime(input);
        if (splitCommand.length != SPLIT_AMOUNT) {
            throw new DukeException();
        }
        Event event = new Event(splitCommand[0], splitCommand[1]); //index 0 is description, index 1 is time
        TaskList.addEvent(event);
    }

    public static void handleInvalid(String input) {
        System.out.println("\"" + input + "\"" + " is not a valid command, please try again!");
    }

}
