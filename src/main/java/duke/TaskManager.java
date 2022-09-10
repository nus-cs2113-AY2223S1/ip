package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;

/**
 * API to query and edit Task objects
 */
public class TaskManager {
    ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Default Constructor
     */
    public TaskManager() {
    }

    /**
     * Private helper method which uses Substitutability to add a ToDo, Deadline
     * or event task to the list of tasks
     * @param task Task description
     * @return String Message for task status
     */
    private String addTask(Task task) {
        tasks.add(task);
        return "Got it. I've added this task:\n"
                + " " + task.toString() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.\n";
    }

    /**
     * Add a new ToDo task to the list of tasks
     * @param taskDescription Name of task
     * @return String Notification string for the added todo task
     */
    public String addToDo(String taskDescription) {
        String output = addTask(new ToDo(taskDescription));
        return output;
    }

    /**
     * Add a new deadline task to the list of tasks
     * @param taskDescription Name of Task
     * @param by Deadline
     * @return String Notification string for the added deadline task
     */
    public String addDeadline(String taskDescription, String by) {
        String output = addTask(new Deadline(taskDescription, by));
        return output;
    }

    /**
     * Add a new event task to the list of tasks
     * @param taskDescription Name of task
     * @param at When the event takes place
     * @return String Notification string for the added event task
     */
    public String addEvent(String taskDescription, String at) {
        String output = addTask(new Event(taskDescription, at));
        return output;
    }

    /**
     * Marks Task as done
     * @param taskNum Task number
     * @return String Output Message for task status
     */

    public String markTask(int taskNum) {
        String output;
        if (taskNum <= 0 || taskNum-1 >= tasks.size()) {
            output = "Not a valid task number\n";
        } else {
            Task task =  tasks.get(taskNum-1);
            task.markAsDone();
            output = "Nice! I've marked this task as done:\n"
                    + "  [X] "
                    + task.getDescription() + "\n";
        }
        return output;
    }

    /**
     * Marks Task as undone
     * @param taskNum Task number
     * @return String output Message for task status
     */

    public String unmarkTask(int taskNum) {
        String output;
        if (taskNum <= 0 || taskNum-1 >= tasks.size()) {
            output = "Not a valid task number\n";
        } else {
            Task task =  tasks.get(taskNum-1);
            task.markAsNotDone();
            output = "OK, I've marked this task as not done yet:\n"
                    + "  [ ] "
                    + task.getDescription() + "\n";
        }
        return output;
    }

    public String deleteTask(int taskNum) {
        String output;
        if (taskNum <= 0 || taskNum-1 >= tasks.size()) {
            output = "Not a valid task number\n";
        } else {
            Task task =  tasks.get(taskNum-1);
            output = "Noted, I've removed this task:\n"
                    + "  " + task.toString() + "\n"
                    + "Now you have " + (tasks.size() - 1) + " tasks in the list.\n";
            tasks.remove(taskNum-1);
        }
        return output;
    }

    /**
     * Returns all completed and uncompleted tasks as a numbered list
     * @return String output Numbered tasks and their completion statuses
     */
    public String getAllTasks() {
        int counter = 1;
        StringBuilder output = new StringBuilder( "Here are the tasks in your list:\n");
        for (Task task: tasks) {
            output.append(String.format("%d. %s\n", counter++, task.toString()));
        }
        return output.toString();
    }

    public String getTaskData() {
        StringBuilder output = new StringBuilder();
        for (Task task : tasks) {
            output.append(task.getTaskData());
            output.append(System.lineSeparator());
        }
        return output.toString();
    }

    public void setTaskData(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

}
