package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.util.ArrayList;

/**
 * This class contains an {@code ArrayList} of {@code Task}s, and contains methods to do a variety of methods involving these tasks.
 * These include adding, removing, deleting, marking and unmarking as done, and finding.
 *
 * @author Dhanish
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    static final String[] LIST_OF_COMMANDS = {"help", "todo", "mark", "unmark", "list", "bye", "deadline", "event", "delete"};

    public static final String DEADLINE_SEPERATOR = " /by ";
    public static final String EVENT_SEPERATOR = " /at ";

    /**
     * Constructor that initialises an empty {@code ArrayList} of {@code Task}s.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Retrieves the {@code Task} corresponding to the input task number.
     *
     * @param taskNumber - number of the {@code Task} to be retrieved.
     * @return the corresponding {@code Task}.
     */
    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber);
    }

    /**
     * Checks if input command {@code String} is a valid command
     *
     * @param command - {@code String} to be checked whether it is a valid command
     * @return {@code true} if input {@code String} is a valid command, {@code false} otherwise.
     */
    public static boolean isValidCommand(String command) {
        for (String validCommand : LIST_OF_COMMANDS) {
            if (command.equals(validCommand))
                return true;
        }
        return false;
    }

    /**
     * @return the {@code ArrayList} of {@code Task}s in this {@code TaskList}.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    private int getTotalNumberOfTasks() {
        return tasks.size();
    }

    private boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * This method adds the input {@code Task} into the {@code TaskList} and notifies the user that the {@code Task} has been added.
     * It also prints the total number of tasks after adding.
     *
     * @param task - {@code Task} to be added into he {@code TaskList}.
     */
    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + task);
        System.out.println("\tNow you have " + (getTotalNumberOfTasks()) + " task(s) in the list.");
    }

    /**
     * This method checks if the input task number corresponds to a valid {@code Task} in the {@code TaskList}
     * If yes, it removes the corresponding {@code Task} from the {@code TaskList}
     *
     * @param taskNumber - number of the {@code Task} to be removed from the {@code TaskList}
     */
    public void removeTask(int taskNumber) {
        if (taskNumber >= getTotalNumberOfTasks() || taskNumber < 0) {
            System.out.println("\tInvalid input! Please key in an existing task number!");
            return;
        }
        Task task = tasks.get(taskNumber);
        tasks.remove(taskNumber);
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t  " + task);

        if (isEmpty()) {
            System.out.println("Nothing in list right now!");
        } else {
            System.out.println("\tNow you have " + (getTotalNumberOfTasks()) + " task(s) in the list.");
        }
    }


    /**
     * adds a {@code Task} into the {@code TaskList} 'silently', without printing any output.
     * Used when appropriate (such as initial loading of {@code Task}s from data file.
     *
     * @param task - {@code Task} to be added into the {@code TaskList} 'silently'.
     */
    public void loadTask(Task task) {
        tasks.add(task);
    }

    /**
     * This method marks a {@code Task} as done.
     *
     * @param taskNumber - the number of the {@code Task} to be marked as done.
     */
    public void markTaskAsDone(int taskNumber) {
        if (taskNumber >= getTotalNumberOfTasks() || taskNumber < 0) {
            System.out.println("\tInvalid input! Please key in an existing task number!");
            return;
        }

        if (tasks.get(taskNumber).isDone()) {
            System.out.println("\tThis task has already been marked as done!");
            return;
        }

        System.out.println("\tNice! I've marked this task as done: ");
        tasks.get(taskNumber).markAsDone();
        System.out.println("\t  " + getTask(taskNumber));
    }

    /**
     * This method marks a {@code Task} as undone.
     *
     * @param taskNumber - the number of the {@code Task} to be marked undone.
     */
    public void markTaskAsUndone(int taskNumber) {
        if (taskNumber >= getTotalNumberOfTasks() || taskNumber < 0) {
            System.out.println("\tInvalid input! Please key in an existing task number!");
            return;
        }

        if (!tasks.get(taskNumber).isDone()) {
            System.out.println("\tThis task has already been marked as not done!");
            return;
        }

        System.out.println("\tOK, I've marked this task as not done yet: ");
        tasks.get(taskNumber).markAsUndone();
        System.out.println("\t  " + getTask(taskNumber));

    }

    /**
     * Lists all the present tasks in a numbered list.
     */
    public void listTasks() {
        if (isEmpty()) {
            System.out.println("\tNothing in list right now!");
            return;
        }
        System.out.println("\tHere are the tasks in your list: ");
        for (int taskNumber = 0; taskNumber < getTotalNumberOfTasks(); taskNumber++) {
            System.out.println("\t" + (taskNumber + 1) + ". " + getTask(taskNumber));
        }
    }

    /**
     * Given the type of {@code Task}, the description of the {@code Task}, the completion status of the {@code Task} and time (if any),
     * this method creates a {@code Task} that encapsulates the above data and returns it
     *
     * @param taskType
     * @param description
     * @param status
     * @param time
     * @return the {@code Task} that contains the above information.
     */
    public static Task initialiseTaskFromParameters(String taskType, String description, String status, String time) {
        Task task;
        switch (taskType) {
        case Todo.TASK_TYPE:
            task = new Todo(description);
            break;
        case Event.TASK_TYPE:
            task = new Event(description, time);
            break;
        case Deadline.TASK_TYPE:
            task = new Deadline(description, time);
            break;
        default:
            System.out.println("Error in data file!");
            Duke.exit(-1);
            return null;
        }
        if (status.equals(Task.getDoneIcon())) {
            task.markAsDone();
        }
        return task;
    }

}
