package duke.data;

import duke.common.InfoMessages;
import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.Task;
import duke.data.task.Todo;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Provides the management of a task list array such as adding tasks,
 * deleting tasks and listing tasks.
 */
public class TaskList {
    private static final int INDENT_COUNT = 4;
    private static final String INDENT_SPACE = "    ";
    private static final String EMPTY_STRING = "";
    private static final String DELIMITER = " | ";
    private static final String DONE = "X";
    private static final String NUMERIC_DONE = "1";
    private static final String NUMERIC_NOT_DONE = "0";
    private static final String TASK_TYPE_TODO = "T";
    private static final String TASK_TYPE_EVENT = "E";
    private static final String TASK_TYPE_DEADLINE = "D";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    private ArrayList<Task> tasks;

    /**
     * Instantiates a new task list when user initialises a new instance of this class.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Gets a list of tasks stored in the program.
     *
     * @return A task list that the program has been working with.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Gets the number of tasks that has been stored in the program.
     *
     * @return An integer that states the current number of tasks in the task list.
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Adds a todo task into the program.
     *
     * @param title A string that represents the title of the task.
     * @param isDone A boolean that indicates whether the task is done or undone.
     * @return A todo task.
     */
    public Task addTodo(String title, boolean isDone) {
        Todo todo = new Todo(title, isDone);
        tasks.add(todo);
        return todo;
    }

    /**
     * Adds a deadline task into the program.
     *
     * @param title A string that represents the title of the task.
     * @param dueBy A string that represents the deadline of the task.
     * @param isDone A boolean that indicates whether the task is done or undone.
     * @return A deadline task.
     * @throws DateTimeParseException If an error is caught in converting the string to datetime format.
     */

    public Task addDeadline(String title, String dueBy, boolean isDone) throws DateTimeParseException {
        Deadline deadline = new Deadline(title, dueBy, isDone);
        tasks.add(deadline);
        return deadline;
    }

    /**
     * Adds an event task into the program.
     *
     * @param title A string that represents the title of the task.
     * @param time A string that represents the time that the task will be held at.
     * @param isDone A boolean that indicates whether the task is done or undone.
     * @return An event task.
     */
    public Task addEvent(String title, String time, boolean isDone) {
        Event event = new Event(title, time, isDone);
        tasks.add(event);
        return event;
    }

    /**
     * Marks or unmark a task in the program.
     *
     * @param taskIndex An integer that indicates the index number of the task in the task list.
     * @param isDone A boolean that indicates whether the task is done or undone.
     * @return A string containing the information for the task that has been marked or unmarked.
     */
    public String markTask(int taskIndex, boolean isDone) {
        tasks.get(taskIndex).setStatus(isDone);
        String taskDetails = tasks.get(taskIndex).getTaskDetails();
        return taskDetails;
    }

    /**
     * Deletes a task in the program.
     *
     * @param taskIndex An integer that indicates the index number of the task in the task list.
     * @return A string containing the information for the task that has been deleted.
     */
    public String deleteTask(int taskIndex) {
        String taskDetails = tasks.get(taskIndex).getTaskDetails();
        tasks.remove(taskIndex);
        return taskDetails;
    }

    /**
     * Formats a task into the required format for writing to file.
     *
     * @param task A todo, deadline or event task.
     * @return A string of the formatted task.
     */
    public String getTaskFileEntry(Task task) {
        // Sets the task status as 1 or 0 based on the task completion status boolean indicated in isDone
        String taskStatus = task.getStatusIcon().equals(DONE) ? NUMERIC_DONE : NUMERIC_NOT_DONE;
        String taskTitle = task.getTitle();
        // Formats the task based on the task type: T, D or E
        String taskFileEntry = TASK_TYPE_TODO + DELIMITER + taskStatus + DELIMITER + taskTitle;
        if (task instanceof Deadline) {
            taskFileEntry = TASK_TYPE_DEADLINE + DELIMITER + taskStatus + DELIMITER + taskTitle
                    + DELIMITER + task.getDueBy();
        } else if (task instanceof Event) {
            taskFileEntry = TASK_TYPE_EVENT + DELIMITER + taskStatus + DELIMITER + taskTitle
                    + DELIMITER + task.getTime();
        }
        return taskFileEntry;
    }

    /**
     * Formats a list of tasks stored in the program into a string.
     *
     * @return A string containing the formatted task list.
     */
    public String listTasks() {
        String tasksList = EMPTY_STRING;
        // Loops each task from the task list
        for (Task task : tasks) {
            tasksList += INDENT_SPACE + task.getTaskDetails() + LINE_SEPARATOR;
        }
        if (!tasksList.equals(EMPTY_STRING)) {
            // Removes the space indent from the first task
            tasksList = tasksList.substring(INDENT_COUNT);
            // Includes the count of the tasks with the task list
            tasksList += String.format("%s%s", INDENT_SPACE, String.format(InfoMessages.MESSAGE_INFO_TASK_COUNT.toString(), tasks.size()));
        }

        return tasksList;
    }

    /**
     * Formats a filtered list of tasks stored in the program into a string.
     *
     * @param keyword A string containing the keywords used in the search expression.
     * @return A string containing the formatted task list.
     */
    public String findTasks(String keyword) {
        String tasksList = EMPTY_STRING;
        // Loops each task from the task list
        for (Task task : tasks) {
            // Includes only tasks that contain the keywords used in the search expression
            if (task.getTaskDetails().contains(keyword)) {
                tasksList += INDENT_SPACE + task.getTaskDetails() + LINE_SEPARATOR;
            }
        }
        if (!tasksList.equals(EMPTY_STRING)) {
            // Removes the space indent from the first task
            tasksList = tasksList.substring(INDENT_COUNT);
            // Includes the count of the tasks with the task list
            tasksList += String.format("%s%s", INDENT_SPACE, String.format(InfoMessages.MESSAGE_INFO_TASK_COUNT_FIND.toString(), tasks.size()));
        }
        return tasksList;
    }
}
