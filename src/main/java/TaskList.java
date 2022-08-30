public class TaskList {

    private Task[] tasks;
    private int taskCount = 0;

    public TaskList() {
        this.tasks = new Task[100];
    }

    public void addDeadLine(Deadline deadline) {
        tasks[taskCount] = deadline;
        taskCount++;
    }

    public void addTodo(Todo todo) {
        tasks[taskCount] = todo;
        taskCount++;
    }

    public void addEvent(Event event) {
        tasks[taskCount] = event;
        taskCount++;
    }

    public void printList() {
        for (int i = 0; i < taskCount; i++) {
            System.out.println(tasks[taskCount]);
        }
    }
}
