package consoleCommands;

import exception.DateParseException;
import exception.InvalidArgumentsException;
import exception.NotEnoughArgumentsException;
import exception.TaskDoesNotExistException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.util.ArrayList;

import static consoleCommands.Ui.printLine;

/**
 * Class to execute commands on taskList
 * Class is used to modify taskList or print various excerpts (full or specific tasks) of taskList
 * All methods in this class takes in taskList as a parameter, which is an ArrayList of tasks
 */
@SuppressWarnings("DuplicatedCode")
public class TaskList {
    public static final String COMMAND_MARKED = "mark";
    public static final String COMMAND_UNMARKED = "unmark";
    public static final String COMMAND_DEADLINE_BY = "/by";
    public static final String COMMAND_EVENT_AT = "/at";
    public static final String ADDED_MESSAGE = "Got it. I've added this task:";
    public static final String DELETED_MESSAGE = "Noted. I've removed this task:";
    public static final String MARKED_MESSAGE = "Nice! I've marked this task as done:";
    public static final String UNMARKED_MESSAGE = "OK, I've marked this task as not done yet:";
    public static final String EMPTY = "";

    /**
     * Method marks/unmarks status of task in taskList based on command given
     * If task is marked, it sets the .isDone of task to true,
     * the checkbox will appear as [X] when printed
     * If task is unmarked, it sets the .isDone of task to false,
     * the checkbox will appear as [ ] when printed
     * Method takes in argument string, and converts it into an integer
     * @param command is the command of "mark" or "unmark"
     * @param arguments is the index of the task to marked or unmarked
     * @throws NotEnoughArgumentsException if arguments is an empty string
     * @throws TaskDoesNotExistException if index provided from argument string is > size
     * of taskList
     */
    public static void markStatus(String command, String arguments, ArrayList<Task> taskList)
            throws TaskDoesNotExistException, NotEnoughArgumentsException {
        printLine();
        if (arguments.equals(EMPTY)) {
            System.out.println("This command should be 'delete (task index number)'.");
            throw new NotEnoughArgumentsException();
        }
        int index = Integer.parseInt(arguments);
        if (index > taskList.size()) {
            throw new TaskDoesNotExistException();
        }
        if (command.equalsIgnoreCase(COMMAND_MARKED)) {
            taskList.get(index-1).isDone = true;
            System.out.println(MARKED_MESSAGE);
        } else if (command.equalsIgnoreCase(COMMAND_UNMARKED)) {
            taskList.get(index-1).isDone = false;
            System.out.println(UNMARKED_MESSAGE);
        }
        System.out.println("  " + taskList.get(index-1));
        printLine();
    }

    /**
     * Method reads in taskList and prints the full list of tasks if taskList is not empty
     */
    public static void printList(ArrayList<Task> taskList) {
        printLine();
        if (taskList.isEmpty()) {
            System.out.println("Your list is empty.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.print((i + 1) + ".");
                System.out.println(taskList.get(i));
            }
        }
        printLine();
    }

    /**
     * Method deletes a task of a specific index, read in from the arguments, from the taskList
     * @param arguments is the index of the task to be deleted
     * @throws NotEnoughArgumentsException if arguments is an empty string
     * @throws TaskDoesNotExistException if index provided from argument string is > size
     * of taskList
     */
    public static void deleteTask(String arguments, ArrayList<Task> taskList)
            throws TaskDoesNotExistException, NotEnoughArgumentsException {
        printLine();
        if (arguments.equals(EMPTY)) {
            System.out.println("This command should be 'delete (task index number)'.");
            throw new NotEnoughArgumentsException();
        }
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

    /**
     * Method adds a todo task to taskList
     * Method will print an ADDED_MESSAGE after successfully adding task to taskList, as a
     * confirmation.
     * @param arguments is the name of the todo task to be added
     * @throws NotEnoughArgumentsException if arguments is an empty string
     */
    public static void addTodo (String arguments, ArrayList<Task> taskList)
            throws NotEnoughArgumentsException {
        printLine();
        if (arguments.equals(EMPTY)) {
            System.out.println("This command should be 'todo (task name)'.");
            throw new NotEnoughArgumentsException();
        }
        taskList.add(new Todo(arguments));
        System.out.println(ADDED_MESSAGE);
        System.out.println("  " + taskList.get(taskList.size() - 1).toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        printLine();
    }

    /**
     * Method adds an event task to taskList
     * Method will print an ADDED_MESSAGE after successfully adding task to taskList, as a
     * confirmation.
     * @param arguments is a string of the name of event, and date and time of the event. Date
     * must be provided in the format yyyy-MM-dd.
     * @throws InvalidArgumentsException if arguments does not contain a "/at"
     * @throws NotEnoughArgumentsException if arguments does not provide name or
     * date and time of the event
     * @throws DateParseException if date is given in the wrong format
     */
    public static void addEvent (String arguments, ArrayList<Task> taskList)
            throws InvalidArgumentsException, NotEnoughArgumentsException, DateParseException {
        printLine();
        if (!arguments.contains(COMMAND_EVENT_AT)) {
            System.out.println("This command should be 'event (task name) /at (YYYY-MM-DD time)'.");
            throw new InvalidArgumentsException();
        }
        String[] eventInstructions = arguments.split(" /at ");
        if (eventInstructions.length != 2) {
            System.out.println("This command should be 'event (task name) /at (YYYY-MM-DD time)'.");
            throw new NotEnoughArgumentsException();
        }
        taskList.add(new Event(eventInstructions[0],eventInstructions[1]));
        System.out.println(ADDED_MESSAGE);
        System.out.println("  " + taskList.get(taskList.size() - 1).toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        printLine();
    }

    /**
     * Method adds a deadline task to taskList
     * Method will print an ADDED_MESSAGE after successfully adding task to taskList, as a
     * confirmation.
     * @param arguments is a string of the name of deadline, and date and time of the deadline.
     * Date must be provided in the format yyyy-MM-dd.
     * @throws InvalidArgumentsException if arguments does not contain a "/by"
     * @throws NotEnoughArgumentsException if arguments does not provide name or
     * date and time of the deadline
     * @throws DateParseException if date is given in the wrong format
     */
    public static void addDeadline (String arguments, ArrayList<Task> taskList)
            throws InvalidArgumentsException, NotEnoughArgumentsException, DateParseException {
        printLine();
        if (!arguments.contains(COMMAND_DEADLINE_BY)) {
            System.out.println("This command should be 'deadline (task name) /by (YYYY-MM-DD time)'.");
            throw new InvalidArgumentsException();
        }
        String[] deadlineInstructions = arguments.split(" /by ");
        if (deadlineInstructions.length != 2) {
            System.out.println("This command should be 'deadline (task name) /by (YYYY-MM-DD time)'.");
            throw new NotEnoughArgumentsException();
        }
        taskList.add(new Deadline(deadlineInstructions[0],deadlineInstructions[1]));
        System.out.println(ADDED_MESSAGE);
        System.out.println("  " + taskList.get(taskList.size() - 1).toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        printLine();
    }

    /**
     * Method uses keywords and iterates through the full list of tasks
     * Method will print the task if Task.Description contains keywords
     * @param arguments is a string of the keywords used to filter tasks
     * @throws NotEnoughArgumentsException if arguments is an empty string
     */
    public static void findTask (String arguments, ArrayList<Task> taskList)
            throws NotEnoughArgumentsException {
        printLine();
        if (arguments.equals(EMPTY)) {
            System.out.println("This command should be 'find (keywords)'.");
            throw new NotEnoughArgumentsException();
        }
        ArrayList<Task> tasksFound = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            Task currTask = taskList.get(i);
            if (currTask.getDescription().contains(arguments)) {
                tasksFound.add(currTask);
            }
        }
        if (tasksFound.isEmpty()) {
            System.out.println("There are no tasks found with the keyword: '" + arguments + "'.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            int count = 1;
            for (int i = 0; i < tasksFound.size(); i++) {
                System.out.print((count) + ".");
                System.out.println(tasksFound.get(i));
                count++;
            }
        }
        printLine();
    }
}
