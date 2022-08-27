import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Prints all tasks in list to standard out.
     */
    public void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            int taskNumber = i + 1;
            System.out.print(taskNumber + ".");
            Task task = tasks.get(i);
            task.print();
        }
    }

    /**
     * Returns number of tasks in task manager.
     */
    public int getNumTasks() {
        return tasks.size();
    }

    /**
     * Adds a task to list of tasks.
     *
     * @param task Task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Marks a task as completed.
     *
     * @param taskNumber Task number of task as shown by the function {@link #printTasks()}.
     */
    public void markTaskAsCompleted(int taskNumber) {
        int index = taskNumber - 1;
        tasks.get(index).setComplete(true);
        String taskDescription = tasks.get(index).getDescription();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  [X] " + taskDescription);
    }

    /**
     * Marks a task as uncompleted.
     *
     * @param taskNumber Task number of task as shown by the function {@link #printTasks()}.
     */
    public void markTaskAsUncompleted(int taskNumber) {
        int index = taskNumber - 1;
        tasks.get(index).setComplete(false);
        String taskDescription = tasks.get(index).getDescription();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  [ ] " + taskDescription);
    }
}
