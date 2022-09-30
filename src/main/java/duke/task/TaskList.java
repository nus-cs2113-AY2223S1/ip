package duke.task;

import duke.Storage;
import duke.exception.EmptyNumberInputException;
import duke.exception.InvalidDeadlineInputException;
import duke.exception.InvalidEventInputException;
import duke.exception.InvalidTaskDescriptionException;
import duke.parser.Parser;

import java.util.ArrayList;

import static duke.Ui.*;

/**
 * Class contains methods to deal with any operations related to the task list and contains the task list.
 */
public class TaskList {

    ArrayList<Task> tasks;

    /**
     * Initialises the TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        Storage.loadInputFile(this.tasks);
    }

    /**
     * Adds a Todo task to the task list from the user input.
     * @param tasks The task list.
     * @param fullCommand The entire user input.
     */
    public static void addTodo(TaskList tasks, String fullCommand) {
        try {
            String description;
            description = Parser.getDescription(fullCommand).trim();
            Task t = new Todo(description);
            tasks.addTask(t);
            printSuccessfulAdd(tasks);
        } catch (InvalidTaskDescriptionException e) {
            showInvalidTodoInputExceptionMessage();
        }
    }

    /**
     * Adds a Deadline task to the task list from the user input.
     * @param tasks The task list.
     * @param fullCommand The entire user input.
     */
    public static void addDeadline(TaskList tasks, String fullCommand) {
        try {
            String[] description = Parser.parseDeadlineDescription(fullCommand);
            String deadlineName = description[0].trim();
            String deadlineDetails = description[1].trim();
            Task t = new Deadline(deadlineName, deadlineDetails);
            tasks.addTask(t);
            printSuccessfulAdd(tasks);
        } catch (InvalidDeadlineInputException e) {
            showInvalidDeadlineInputExceptionMessage();
        } catch (ArrayIndexOutOfBoundsException e) {
            showInvalidDeadlineInputExceptionMessage();
        }
    }

    /**
     * Adds an Event task to the task list from the user input.
     * @param tasks The task list.
     * @param fullCommand The entire user input.
     */
    public static void addEvent(TaskList tasks, String fullCommand) {
        try {
            String[] description = Parser.parseEventDescription(fullCommand);
            String eventName = description[0].trim();
            String eventDetails = description[1].trim();
            Task t = new Event(eventName, eventDetails);
            tasks.addTask(t);
            printSuccessfulAdd(tasks);
        } catch (InvalidEventInputException e) {
            showInvalidEventInputExceptionMessage();
        } catch (ArrayIndexOutOfBoundsException e) {
            showInvalidEventInputExceptionMessage();
        }
    }

    /**
     * Looks through the task list and prints out all the tasks that contain the keyword.
     * The search is case-insensitive. e.g `book` will match `Book`
     * The order of the keywords does not matter. e.g. `Book meeting` will match `meeting Book`
     * Only the name is searched.
     * Only full words will be matched e.g. `Book` will not match `Books`
     * Tasks matching at least one keyword will be returned (i.e. `OR` search).
     * @param tasks The task list.
     * @param fullCommand The entire user input.
     */
    public static void findMatchingTasks(TaskList tasks, String fullCommand) {
        try {
            String description = Parser.getDescription(fullCommand);
            String[] keywords = description.split(" ");
            ArrayList<Task> matchingTasks = new ArrayList<>();
            for (Task task : tasks.tasks) {
                for (String keyword : keywords) {
                    if (task.getName().toLowerCase().contains(keyword.toLowerCase())) {
                        matchingTasks.add(task);
                        break;
                    }
                }
            }
            printMatchingTasks(matchingTasks);
        } catch (InvalidTaskDescriptionException e) {
            showInvalidFindDescriptionExceptionMessage();
        }
    }

    /**
     * Delete a task that is specified by the user. The task is specified by the index of the task in the task list.
     * @param tasks The task list.
     * @param fullCommand The entire user input.
     */
    public static void deleteTask(TaskList tasks, String fullCommand) {
        try {
            int taskId = Parser.getTaskId(fullCommand);
            Task taskToBeDeleted = tasks.get(taskId);
            int taskSize = tasks.size() - 1;
            System.out.println("\t_____________________");
            System.out.println("\tNoted. I've removed this task:");
            System.out.println("\t  " + taskToBeDeleted);

            if (taskSize == 1 || taskSize == 0) {
                System.out.println("\tNow you have " + taskSize + " task in the list.");
            } else {
                System.out.println("\tNow you have " + taskSize + " tasks in the list.");
            }
            System.out.println("\t_____________________");
            tasks.removeTask(taskId);
        } catch (NumberFormatException e) {
            showInvalidDeleteInputExceptionMessage();
        } catch (IndexOutOfBoundsException e) {
            showInvalidDeleteInputExceptionMessage();
        } catch (EmptyNumberInputException e) {
            showInvalidDeleteInputExceptionMessage();
        }
    }

    /**
     * Marks a task as done. The task is specified by the index of the task in the task list.
     * @param tasks The task list.
     * @param fullCommand The entire user input.
     */
    public static void markTask(TaskList tasks, String fullCommand) {
        try {
            int taskId = Parser.getTaskId(fullCommand);
            printMark(tasks, taskId);
        } catch (NumberFormatException e) {
            showInvalidMarkTaskInputExceptionMessage();
        } catch (IndexOutOfBoundsException e) {
            showInvalidMarkTaskInputExceptionMessage();
        } catch (EmptyNumberInputException e) {
            showInvalidMarkTaskInputExceptionMessage();
        }
    }

    /**
     * Unmarks a task as undone. The task is specified by the index of the task in the task list.
     * @param tasks The task list.
     * @param fullCommand The entire user input.
     */
    public static void unmarkTask(TaskList tasks, String fullCommand) {
        try {
            int taskId = Parser.getTaskId(fullCommand);
            printUnmark(tasks, taskId);
        } catch (NumberFormatException e) {
            showInvalidUnmarkTaskInputExceptionMessage();
        } catch (IndexOutOfBoundsException e) {
            showInvalidUnmarkTaskInputExceptionMessage();
        } catch (EmptyNumberInputException e) {
            showInvalidUnmarkTaskInputExceptionMessage();
        }
    }

    /**
     * Prints out the total number of tasks in the task list.
     * @param tasks The task list.
     * @param fullCommand The entire user input.
     */
    public static void totalTask(TaskList tasks, String fullCommand) {
        int taskSize = tasks.size();
        printTotalNumberOfItems(tasks, taskSize);
    }

    // get size of tasks
    public int size() {
        return tasks.size();
    }

    // get task from tasks
    public Task get(int index) {
        return tasks.get(index);
    }

    // add task to tasks
    public void addTask(Task task) {
        tasks.add(task);
    }

    // remove task from tasks
    public void removeTask(int index) {
        tasks.remove(index);
    }
}
