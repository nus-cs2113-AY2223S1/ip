public class TaskManager {
    private static final int MAX_NUM_OF_TASKS = 100;
    private Task[] tasks = new Task[MAX_NUM_OF_TASKS];

    public void addTask(String text) {
        final String ADD_PHRASE = "added: ";

        Task newTask = new Task(text);

        // change to zero-index
        tasks[Task.getTaskCount() - 1] = newTask;

        System.out.println(ADD_PHRASE + text);
    }

    public void printList() {
        for (int i = 0; i < Task.getTaskCount(); i += 1) {
            System.out.println(Integer.toString(i + 1) + ".[" + tasks[i].getStatusIcon() + "] "
                    + tasks[i].description);
        }
    }

    public void markTask(int num) {
        // note that num here is zero-index
        tasks[num].setDone();

        System.out.println("Completed! The following task is marked as done:");
        System.out.println("[" + tasks[num].getStatusIcon() + "] " + tasks[num].getDescription());
    }

    public void unmarkTask(int num) {
        // note that num here is zero-index
        tasks[num].setUndone();

        System.out.println("Oh no! The following task is marked as undone:");
        System.out.println("[" + tasks[num].getStatusIcon() + "] " + tasks[num].getDescription());
    }
}
