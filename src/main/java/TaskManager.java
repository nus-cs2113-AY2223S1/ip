public class TaskManager {
    private static final int MAX_NUM_OF_TASKS = 100;
    private Task[] tasks = new Task[MAX_NUM_OF_TASKS];
    private int taskCount = 0;

    public void addTask(String text) {
        final String ADD_PHRASE = "added: ";

        Task newTask = new Task(text);

        tasks[taskCount] = newTask;
        taskCount += 1;

        System.out.println(ADD_PHRASE + text);
    }

    public void printList() {
        for (int i = 0; i < taskCount; i += 1) {
            System.out.println(Integer.toString(i + 1) + ".[" + tasks[i].getStatusIcon() + "] "
                    + tasks[i].description);
        }
    }

    public void markTask(int num) {
        tasks[num].setDone();

        System.out.println("Completed! The following task is marked as done:");
        System.out.println(
                "[" + tasks[num].getStatusIcon() + "] " + tasks[num].getDescription());
    }

    public void unmarkTask(int num) {
        tasks[num].setUndone();

        System.out.println("Oh no! The following task is marked as undone:");
        System.out.println(
                "[" + tasks[num].getStatusIcon() + "] " + tasks[num].getDescription());
    }
}
