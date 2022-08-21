public class TaskManager {
    protected Task[] tasks;
    protected int taskCount;

    public TaskManager() {
        tasks = new Task[100];
        taskCount = 0;
    }

    public void addTask(String input) {
        tasks[taskCount] = new Task(input);
        System.out.println("Added: " + tasks[taskCount].description);
        taskCount++;
    }

    public void markAsDone(String input) {
        int taskNum = Integer.parseInt(input.substring(input.length() - 1));
        taskNum--;
        if (taskNum >= 0 && taskNum < taskCount) {
            tasks[taskNum].markAsDone();
        }
    }

    public void removeMark(String input) {
        int taskNum = Integer.parseInt(input.substring(input.length() - 1));
        taskNum--;
        if (taskNum >= 0 && taskNum < taskCount) {
            tasks[taskNum].removeMark();
        }
    }

    public void print() {
        for (int i = 0; i < taskCount; i++) {
            System.out.print(i+1);
            System.out.print(" " + tasks[i].description);
            System.out.println(" " + tasks[i].getStatusIcon());
        }
    }

}
