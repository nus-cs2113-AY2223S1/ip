package consoleCommands;

import exception.InvalidArgumentsException;
import exception.NotEnoughArgumentsException;
import exception.TaskDoesNotExistException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import java.util.ArrayList;

import static consoleCommands.Ui.printLine;

public class TaskList {
    public static final String COMMAND_MARKED = "mark";
    public static final String COMMAND_DEADLINE_BY = "/by";
    public static final String COMMAND_EVENT_AT = "/at";
    public static final String ADDED_MESSAGE = "Got it. I've added this task:";
    public static final String DELETED_MESSAGE = "Noted. I've removed this task:";
    public static final String MARKED_MESSAGE = "Nice! I've marked this task as done:";
    public static final String UNMARKED_MESSAGE = "OK, I've marked this task as not done yet:";
    public static final String EMPTY = "";
    public static void markStatus(String command, String arguments, ArrayList<Task> taskList)
            throws TaskDoesNotExistException {
        printLine();
        int index = Integer.parseInt(arguments);
        if (index > taskList.size()) {
            throw new TaskDoesNotExistException();
        }
        if (command.equals(COMMAND_MARKED)) {
            taskList.get(index-1).isDone = true;
            System.out.println(MARKED_MESSAGE);
        } else {
            taskList.get(index-1).isDone = false;
            System.out.println(UNMARKED_MESSAGE);
        }
        System.out.println("  " + taskList.get(index-1));
        printLine();
    }
    public static void printList(ArrayList<Task> taskList) {
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.print((i + 1) + ".");
            System.out.println(taskList.get(i).toString());
        }
        printLine();
    }
    public static void deleteTask(String arguments, ArrayList<Task> taskList)
            throws TaskDoesNotExistException {
        printLine();
        int index = Integer.parseInt(arguments);
        if (index > taskList.size()) {
            throw new TaskDoesNotExistException();
        }
        System.out.println(DELETED_MESSAGE);
        System.out.println("  " + taskList.get(index-1).toString());
        taskList.remove(index-1);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        printLine();
    }
    public static void addTodo (String arguments, ArrayList<Task> taskList) throws NotEnoughArgumentsException {
        printLine();
        if (arguments.equals(EMPTY)) {
            throw new NotEnoughArgumentsException();
        }
        taskList.add(new Todo(arguments));
        System.out.println(ADDED_MESSAGE);
        System.out.println("  " + taskList.get(taskList.size() - 1).toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        printLine();
    }
    public static void addEvent (String arguments, ArrayList<Task> taskList)
            throws InvalidArgumentsException, NotEnoughArgumentsException {
        printLine();
        if (!arguments.contains(COMMAND_EVENT_AT)) {
            throw new InvalidArgumentsException();
        }
        String[] eventInstructions = arguments.split(" /at ");
        if (eventInstructions.length != 2) {
            throw new NotEnoughArgumentsException();
        }
        taskList.add(new Event(eventInstructions[0],eventInstructions[1]));
        System.out.println(ADDED_MESSAGE);
        System.out.println("  " + taskList.get(taskList.size() - 1).toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        printLine();
    }
    public static void addDeadline (String arguments, ArrayList<Task> taskList)
            throws InvalidArgumentsException, NotEnoughArgumentsException {
        printLine();
        if (!arguments.contains(COMMAND_DEADLINE_BY)) {
            throw new InvalidArgumentsException();
        }
        String[] deadlineInstructions = arguments.split(" /by ");
        if (deadlineInstructions.length != 2) {
            throw new NotEnoughArgumentsException();
        }
        taskList.add(new Deadline(deadlineInstructions[0],deadlineInstructions[1]));
        System.out.println(ADDED_MESSAGE);
        System.out.println("  " + taskList.get(taskList.size() - 1).toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        printLine();
    }
    public static void findTask (String arguments, ArrayList<Task> taskList) throws NotEnoughArgumentsException{
        printLine();
        if (arguments.equals(EMPTY)) {
            throw new NotEnoughArgumentsException();
        }
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            Task currTask = taskList.get(i);
            if (currTask.getDescription().contains(arguments)) {
                System.out.print((i + 1) + ".");
                System.out.println(currTask.toString());
            }
        }
        printLine();
    }
}
