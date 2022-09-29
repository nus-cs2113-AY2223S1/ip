import java.util.List;
public class TaskList{
    private final List<Task> tasks;
    private final Ui ui = new Ui();
    public TaskList(List<Task> fileTasks) {
        tasks = fileTasks;
    }
    public List<Task> getTaskList() {
        return this.tasks;
    }
    public void showList() {
       ui.showList(tasks);
    }

    public void showMatchedTasks(String keyword) {
        ui.showMatchedTasks(tasks, keyword);
    }

    /**
     * Marks the specified task as done and updates both the task list and data file
     * @param index gets the specified task
     */
    public void mark(int index) {
        Task task = tasks.get(index - 1);
        task.setDone(true);
        tasks.set(index - 1, task);
        ui.markTaskMessage(task);
    }

    /**
     * deletes the specified task and updates both the task list and data file
     * @param index gets the specified task
     */
    public void delete(int index) {
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        ui.deleteTaskMessage(task, tasks);
    }
    /**
     * Marks the specified task as not done and updates both the task list and data file
     * @param index gets the specified task
     */
    public void unmark(int index) {
        Task task = tasks.get(index - 1);
        task.setDone(false);
        tasks.set(index - 1, task);
        ui.unmarkTaskMessage(task);
    }

    /**
     * Adds the newly created task to the task list
     * @param task is the new task the user has created
     */
    public void addTask(Task task) {
        tasks.add(task);
        ui.addTaskMessage(task, tasks);
    }
}
