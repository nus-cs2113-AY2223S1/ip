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
    public void list() {
       ui.showList(tasks);
    }
    public void mark(int index) {
        Task task = tasks.get(index - 1);
        task.setDone(true);
        tasks.set(index - 1, task);
        ui.markTaskMessage(task);
    }

    public void delete(int index) {
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        ui.deleteTaskMessage(task, tasks);
    }

    public void unmark(int index) {
        Task task = tasks.get(index - 1);
        task.setDone(false);
        tasks.set(index - 1, task);
        ui.unmarkTaskMessage(task);
    }
    public void addTask(Task task) {
        tasks.add(task);
        ui.addTaskMessage(task, tasks);
    }
}
