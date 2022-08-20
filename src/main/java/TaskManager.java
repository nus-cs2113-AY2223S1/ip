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
     * @param taskDescription String Task description
     * @return String Message for task status
     */
    public String addTask(String taskDescription) {
        tasks.add(new Task(taskDescription));
        return "added: " + taskDescription;
    }

    /**
     * Marks Task as done
     * @param taskNum Task number
     * @return String output Message for task status
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

    /**
     * Returns all completed and uncompleted tasks as a numbered list
     * @return String output Numbered tasks and their completion statuses
     */
    public String getAllTasks() {
        int counter = 1;
        StringBuilder output = new StringBuilder( "Here are the tasks in your list:\n");
        for (Task task: tasks) {
            output.append(String.format("%d. [%s] %s\n", counter++, task.getStatusIcon(), task.getDescription()));
        }
        return output.toString();
    }

}
