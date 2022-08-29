public class TaskManager {
    Task[] tasks;
    int numTasks;
    static final int MAX_NUM_TASKS = 100;

    public static final String DEADLINE_SEPERATOR = " /by ";
    public static final String EVENT_SEPERATOR = " /at ";

    public TaskManager() {
        tasks = new Task[MAX_NUM_TASKS];
        numTasks = -1;
    }

    public Task[] getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks[++numTasks] = task;
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + task);
        System.out.println("Now you have " + (numTasks + 1) + " task(s) in the list.");
    }

    public void markTaskAsDone(int taskNumber) {
        if (taskNumber > numTasks || taskNumber < 0) {
            System.out.println("\tInvalid input! Please key in an existing task number!");
        } else if (tasks[taskNumber].isDone()) {
            System.out.println("\tThis task has already been marked as done!");
        } else {
            System.out.println("\tNice! I've marked this task as done: ");
            tasks[taskNumber].markAsDone();
            System.out.println("\t  " + tasks[taskNumber]);
        }
    }

    public void markTaskAsUndone(int taskNumber) {
        if (taskNumber > numTasks || taskNumber < 0) {
            System.out.println("\tInvalid input! Please key in an existing task number!");
        } else if (!tasks[taskNumber].isDone()) {
            System.out.println("\tThis task has already been marked as not done!");
        } else {
            System.out.println("\tOK, I've marked this task as not done yet: ");
            tasks[taskNumber].markAsUndone();
            System.out.println("\t  " + tasks[taskNumber]);
        }
    }

    public void listTasks() {
        if (numTasks == -1) {
            System.out.println("\tNothing in list right now!");
        }
        System.out.println("\tHere are the tasks in your list: ");
        for (int i = 0; i <= numTasks; i++) {
            System.out.println("\t" + (i + 1) + ". " + tasks[i]);
        }
    }

}
