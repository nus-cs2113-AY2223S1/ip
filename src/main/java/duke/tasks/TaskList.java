package duke.tasks;

import java.util.ArrayList;

import duke.exceptions.*;

/**
 * Represents an ArrayList of Task <code>tasks</code> represents the list of all the tasks.
 */

public class TaskList {
    public static ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Parser delimiters
     */

    public static final String GET_DESCRIPTION_DELIMITER = "/.+";
    public static final String GET_AT_DELIMITER = ".+/at ";
    public static final String GET_BY_DELIMITER = ".+/by ";
    public static final String DIGITS_DELIMITER = "\\d+";
    public static final String DEADLINE_DELIMITER = ".+/by.+";
    public static final String EVENT_DELIMITER = ".+/at.+";

    /**
     * Constructor
     */

    public TaskList() {
    }

    /**
     * Gets acknowledgement of updated number of tasks in taskList
     *
     * @return String containing an acknowledgement message of how many tasks in taskList there are
     */
    public static String getAcknowledgement() {
        int taskCount = taskList.size();
        String latestTaskAdded = taskList.get(taskCount - 1).showTask();
        return "Got it. I've added this task:\n  " +
                latestTaskAdded +
                "\nNow you have " + taskCount + " tasks in the list";
    }

    /**
     * Adds a Todo task to taskList
     *
     * @param taskDescription Arguments of the user's input
     * @param isDone isDone status of task
     */
    public static void addTodo(String taskDescription, boolean isDone){
        Task newTask = new Todo(taskDescription, isDone);
        taskList.add(newTask);
    }
    /**
     * Adds a Todo task to taskList
     *
     * @param commandArgument Arguments of the user's input
     * @throws TodoException if task description is empty
     */
    public static String addTodo(String commandArgument) throws TodoException {
        if (commandArgument.equals("") || commandArgument.equals("todo")) {
            throw new TodoException();
        }
        Task newTask = new Todo(commandArgument);
        taskList.add(newTask);
        return getAcknowledgement();
    }

    /**
     * Adds an Event task to taskList
     *
     * @param taskDescription Arguments of the user's input
     * @param isDone isDone status of task
     */

    public static void addEvent(String taskDescription, String at, boolean isDone) {
        Task newTask = new Event(taskDescription, at, isDone);
        taskList.add(newTask);
    }
    /**
     * Adds an Event task to taskList
     *
     * @param commandArgument Arguments of the user's input
     * @return Acknowledgement message of successful addition of a task
     * @throws EventException if task description do not follow the format of
     *  description /at time
     */

    public static String addEvent(String commandArgument) throws EventException {
        if (!commandArgument.matches(EVENT_DELIMITER)) {
            throw new EventException();
        }

        String taskDescription = commandArgument.replaceAll(GET_DESCRIPTION_DELIMITER, "");
        String at = commandArgument.replaceAll(GET_AT_DELIMITER, "");

        Task newTask = new Event(taskDescription, at);
        taskList.add(newTask);
        return getAcknowledgement();
    }

    /**
     * Adds a Deadline task to taskList
     *
     * @param taskDescription Arguments of the user's input
     * @param by time Deadline is due
     * @param isDone isDone status of task
     */
    public static void addDeadline(String taskDescription, String by, boolean isDone) {
        Task newTask = new Deadline(taskDescription, by, isDone);
        taskList.add(newTask);
    }
    /**
     * Adds an Event task to taskList
     *
     * @param commandArgument Arguments of the user's input
     * @throws DeadlineException if task description do not follow the format of
     * description /by time
     * @return Acknowledgement message of successful addition of a Deadline task
     */
    public static String addDeadline(String commandArgument) throws DeadlineException {
        if (!commandArgument.matches(DEADLINE_DELIMITER)) {
            throw new DeadlineException();
        }

        String taskDescription = commandArgument.replaceAll(GET_DESCRIPTION_DELIMITER, "");
        String by = commandArgument.replaceAll(GET_BY_DELIMITER, "");

        Task newTask = new Deadline(taskDescription, by);
        taskList.add(newTask);
        return getAcknowledgement();
    }
    /**
     * Removes a task from taskList
     *
     * @param commandArgument Arguments of the user's input
     * @return deleteCompleteMessage a string containing latest number of tasks after successful deletion.
     * @throws DeleteException if user's input does not match format of a single integer
     * @throws DeleteRangeException if user input index is out of bounds of taskList
     */
    public static String deleteTask(String commandArgument) throws DeleteException, DeleteRangeException {

        if (!commandArgument.matches(DIGITS_DELIMITER)) {
            throw new DeleteException();
        }

        int index = Integer.parseInt(commandArgument) - 1;

        if (index >= taskList.size()) {
            throw new DeleteRangeException();
        }

        String deleteCompleteMessage = "Noted. I've removed this task: "
                + taskList.get(index).showTask();
        taskList.remove(index);
        deleteCompleteMessage += "\n Now you have " + taskList.size()
                + " tasks in the list.";

        return deleteCompleteMessage;
    }


    /**
     * Function that retrieves all
     * tasks in taskList.
     *
     * @return String of all tasks
     */
    public static String getTaskList() throws EmptyListException {

        if (taskList.size() == 0) {
            throw new EmptyListException();
        }

        StringBuilder listOutput = new StringBuilder();


        for (int i = 0; i < taskList.size(); i++) {
            listOutput.append(i + 1);
            listOutput.append(". ");
            listOutput.append(taskList.get(i).showTask());
            listOutput.append("\n");
        }
        return listOutput.toString();
    }

    /**
     * Function sets marks in task-list to be done
     * @return Acknowledgement message of successful marking of the task as done
     * @throws DoneFormatException when user input format does not follow done <integer>
     * @throws DoneRangeException when user inputs index out of range of taskList
     * @throws DoneAlreadyException when user tries to mark a task that is already marked as done
     */

    public static String markAsDone(String commandArguments) throws DoneFormatException, DoneRangeException, DoneAlreadyException {

        if (!commandArguments.matches(DIGITS_DELIMITER)) {
            throw new DoneFormatException();
        }
        int index = Integer.parseInt(commandArguments) - 1;

        if (index < 0 || index >= taskList.size()) {
            throw new DoneRangeException();
        }

        if (taskList.get(index).isDone) {
            throw new DoneAlreadyException();
        }

        taskList.get(index).markDone();

        return "Nice! I've marked this task as done:\n  "
                + taskList.get(index).showTask()
                + "\n";
    }

    /**
     * Default empty command to be used when unrecognised command is given
     * @throws DoNothingException when command is not recognised from user input
     */

    public static void doNothing() throws DoNothingException {
        throw new DoNothingException();
    }

    /**
     * Find tasks which have description containing search term
     *
     * @param commandArguments Search term to find within tasks' description
     * @return a String representing the list of items that contain the search term
     * @throws NotFoundException when matching search term is not found
     */
    public static String findTask(String commandArguments) throws NotFoundException {
        StringBuilder found = new StringBuilder("Here are the tasks in your list that contains '" + commandArguments + "'");

        int numTasksFound = 0;

        for (int i = 0; i < taskList.size(); i++){
            if (taskList.get(i).getTaskDescription().toLowerCase().contains(commandArguments.toLowerCase())){
                numTasksFound++;
                found.append("\n").append(i + 1).append(". ").append(taskList.get(i).showTask());
            }
        }

        if (numTasksFound == 0){
            throw new NotFoundException();
        }
        return found.toString();
    }
}
