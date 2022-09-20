package duke;

import duke.exception.DukeInvalidTaskNumberException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.ui.parser.*;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * API to query and edit Task objects
 */
public class TaskList {
    ArrayList<Task> tasks = new ArrayList<>();
    ArrayList<Task> tasksLastShown = new ArrayList<>();

    /**
     * Default constructor
     */
    public TaskList() {

    }

    /**
     * Private helper method which uses Substitutability to add a ToDo, Deadline
     * or event task to the list of tasks
     * @param task Task description
     * @return Message for task status
     */
    private String addTask(Task task) {
        tasks.add(task);
        return "Got it. I've added this task:\n"
                + " " + task.toString() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Adds a new ToDo task to the list of tasks
     * @param taskDescription Name of task
     * @return Notification string for the added todo task
     */
    private String addToDo(String taskDescription) {
        String output = addTask(new ToDo(taskDescription));
        return output;
    }

    /**
     * Adds a new deadline task to the list of tasks
     * @param taskDescription Name of Task
     * @param date Date of Deadline
     * @param time Time of Deadline
     * @return Notification string for the added deadline task
     */
    private String addDeadline(String taskDescription, String date, String time) {
        String output = addTask(new Deadline(taskDescription, date, time));
        return output;
    }

    /**
     * Adds a new event task to the list of tasks
     * @param taskDescription Name of task
     * @param date Date of Deadline
     * @param time Time of Deadline
     * @return Notification string for the added event task
     */
    private String addEvent(String taskDescription, String date, String time) {
        String output = addTask(new Event(taskDescription, date, time));
        return output;
    }

    /**
     * Marks Task as done
     * @param taskNum Task number
     * @return String Output Message for task status
     */

    private String markTask(int taskNum) throws DukeInvalidTaskNumberException {
        String output;
        if (taskNum <= 0 || taskNum - 1 >= tasksLastShown.size()) {
            throw new DukeInvalidTaskNumberException(taskNum);
        }
        Task task =  tasksLastShown.get(taskNum - 1);
        task.markAsDone();
        output = "Nice! I've marked this task as done:\n"
                + "  [X] " + task.getDescription() + "\n";

        return output;
    }

    /**
     * Marks Task as undone
     * @param taskNum Task number
     * @return Output Message for task status
     */

    private String unmarkTask(int taskNum) throws DukeInvalidTaskNumberException {
        String output;
        if (taskNum <= 0 || taskNum - 1 >= tasksLastShown.size()) {
            throw new DukeInvalidTaskNumberException(taskNum);
        }

        Task task = tasksLastShown.get(taskNum - 1);
        task.markAsNotDone();
        output = "OK, I've marked this task as not done yet:\n"
                + "  [ ] " + task.getDescription() + "\n";

        return output;
    }

    /**
     * Deletes a task from the task list based on its task number of the most recent list displayed
     * @param taskNum Task number
     * @return Output message for the task deletion
     * @throws DukeInvalidTaskNumberException If the number provided is not a task number within the most recent task
     *                                        list
     */
    private String deleteTask(int taskNum) throws DukeInvalidTaskNumberException {
        String output;
        if (taskNum <= 0 || taskNum - 1 >= tasksLastShown.size()) {
            throw new DukeInvalidTaskNumberException(taskNum);
        }

        Task task = tasksLastShown.get(taskNum - 1);
        output = "Noted, I've removed this task:\n"
                + "  " + task.toString() + "\n"
                + "Now you have " + (tasks.size() - 1) + " tasks in the list.\n";
        tasksLastShown.remove(task);
        tasks.remove(task);

        return output;
    }

    /**
     * Searches for the task via its description
     * @param searchPhrase Phrase to search for within the tasks' descriptions
     * @return Search results
     */
    private String findTask(String searchPhrase) {
        ArrayList<Task> tasksToShow = tasks.stream().filter(n -> n.getDescription().contains(searchPhrase))
                .collect(Collectors.toCollection(ArrayList::new));

        tasksLastShown = tasksToShow;

        int counter = 0;
        StringBuilder output = new StringBuilder("Here are the matching tasks in your list:\n");
        for (Task task : tasksToShow) {
            output.append(String.format("%d. %s\n", ++counter, task.toString()));
        }

        output.append(String.format("\nNumber of search results: %d", counter));
        return output.toString();
    }


    /**
     * Returns all tasks as a numbered list
     * @return Output message of numbered tasks and their completion statuses
     */
    public String getAllTasks() {
        tasksLastShown = tasks;

        int counter = 1;
        StringBuilder output = new StringBuilder("Here are the tasks in your list:\n");
        for (Task task: tasks) {
            output.append(String.format("%d. %s\n", counter++, task.toString()));
        }

        return output.toString();
    }

    /**
     * Getter method to get all formatted task data to be saved into the data file
     * @return All task data in a format parse-able by the Storage class
     */
    public String getTaskData() {
        StringBuilder output = new StringBuilder();
        for (Task task : tasks) {
            output.append(task.getTaskData());
            output.append(System.lineSeparator());
        }
        return output.toString();
    }

    /**
     * Setter method to load parsed task data from the data file into the TaskList class
     * @param tasks List of tasks
     */
    public void setTaskData(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.tasksLastShown = tasks;
    }

    /**
     * Execute the command according to the command type
     * @param command Command
     * @return Output message command execution
     * @throws Exception All exceptions
     */
    public String executeCommand(Command command) throws Exception {
        String output;

        switch (command.getCommandType()) {
        case EXIT:
            output = "Byeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee";
            break;
        case LIST:
            output = getAllTasks();
            break;
        case TODO:
            output = addToDo(((CommandToDo) command).getDescription());
            break;
        case MARK:
            output = markTask(((CommandMark) command).getTaskNum());
            break;
        case UNMARK:
            output = unmarkTask(((CommandUnmark) command).getTaskNum());
            break;
        case DEADLINE:
            CommandDeadline commandDeadline = (CommandDeadline) command;
            output = addDeadline(commandDeadline.getDescription(),
                    commandDeadline.getDate(),
                    commandDeadline.getTime());
            break;
        case EVENT:
            CommandEvent commandEvent = (CommandEvent) command;
            output = addEvent(commandEvent.getDescription(), commandEvent.getDate(), commandEvent.getTime());
            break;
        case DELETE:
            output = deleteTask(((CommandDelete) command).getTaskNum());
            break;
        case FIND:
            output = findTask(((CommandFind) command).getSearchPhrase());
            break;
        default:
            // Program is not supposed to reach here
            output = "ERROR: Something went wrong with command execution.\n Congrats, you've broken DuckyMomo";
            break;
        }
        return output;
    }

}
