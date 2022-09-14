package handler;

import task.Deadline;
import task.Event;
import task.TaskList;
import task.Todo;
import exception.DukeException;

public class TaskHandler {
    private static final String BYE_MESSAGE = "Bye! ;)";
    private static final String INDEX_NOT_FOUND = "There are no tasks with that index";
    private static final int SPLIT_AMOUNT = 2;

    public static void handleBye() {
        System.out.println(BYE_MESSAGE);
    }

    public static void handleTodo(String input) {
        Todo todo = new Todo(input);
        TaskList.addTodo(todo);
    }

    public static void handleList() {
        TaskList.printList();
    }

    public static void handleMark(String input) {
        try {
            int index = readIndex(input);
            TaskList.markTarget(index);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("There is no task with that index");
        } catch (NumberFormatException e) {
            System.out.println("Index is a number...");
        }
    }

    public static void handleUnmark(String input) {
        try {
            int index = readIndex(input);
            TaskList.unmarkTarget(index);
        } catch (NumberFormatException e) {
            System.out.println("Index is an integer");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index does not exist");
        }
    }

    public static void handleDeadline(String input) throws DukeException {
        String[] splitCommand = splitDeadlineTime(input);
        if (splitCommand.length != SPLIT_AMOUNT) {
            throw new DukeException();
        }
        String description = splitCommand[0];
        String by = splitCommand[1];
        Deadline deadline = new Deadline(description, by);
        TaskList.addDeadLine(deadline);
    }

    public static void handleEvent(String input) throws DukeException {
        String[] splitCommand = splitEventTime(input);
        if (splitCommand.length != SPLIT_AMOUNT) {
            throw new DukeException();
        }
        String description = splitCommand[0];
        String at = splitCommand[1];
        Event event = new Event(description, at);
        TaskList.addEvent(event);
    }

    public static void handleDelete(String input) {
        try {
            int index = readIndex(input);
            TaskList.deleteTask(index);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index does not exist.");
        }
    }

    private static int readIndex(String input) throws NumberFormatException {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw e;
        }
    }

    private static String[] splitDeadlineTime(String input) throws DukeException {
        return input.split(" /by ", SPLIT_AMOUNT);
    }

    private static String[] splitEventTime(String input) throws DukeException {
        return input.split(" /at ", SPLIT_AMOUNT);
    }

    public static void handleInvalid(String input) {
        System.out.println("\"" + input + "\"" + " is not a valid command, please try again!");
    }
}
