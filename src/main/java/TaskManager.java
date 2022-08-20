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
     * Adds task to the TaskManager
     * @param task
     * @return String Message for task status
     */
    public String addTask(String task) {
        tasks.add(new Task(task));
        return "added: " + task;
    }

    /**
     * Marks Task as done
     * @param taskNum Task number
     * @return String Message for task status
     */

    public String markTask(int taskNum) {
        Task task =  tasks.get(taskNum-1);
        task.markAsDone();
        return "Nice! I've marked this task as done:\n"
                + "  [X] "
                + task.getDescription() + "\n";
    }

    /**
     * Marks Task as undone
     * @param taskNum Task number
     * @return String Message for task status
     */

    public String unmarkTask(int taskNum) {
        Task task =  tasks.get(taskNum-1);
        task.markAsNotDone();
        return "OK, I've marked this task as not done yet:\n"
                + "  [ ] "
                + task.getDescription() + "\n";
    }

    /**
     * Returns all completed and uncompleted tasks as a numbered list
     * @return String Output with numbered tasks and their completion statuses
     */
    public String getAllTasks() {
        int counter = 1;
        String output = "Here are the tasks in your list:\n";
        for (Task task: tasks) {
            output = output.concat(counter++ + "."
                    + "[" + task.getStatusIcon() + "] "
                    + task.getDescription()
                    + "\n");
        }
        return output;
    }

}
