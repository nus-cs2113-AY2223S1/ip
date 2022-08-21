public class TaskManager {
    private Task[] tasks = new Task[100];
    private int taskCount = 0;

    public void greet() {
        System.out.println("  ____________________________________________________________");
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println("  ____________________________________________________________");
    }

    public void bye() {
        System.out.println("  ____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("  ____________________________________________________________");
    }

    public void addTask(String line) {
        tasks[taskCount] = new Task(line);
        taskCount++;
        System.out.println("  ____________________________________________________________");
        System.out.println("\tAdded: " + line);
        System.out.println("  ____________________________________________________________");
    }

    public void listTasks() {
        System.out.println("  ____________________________________________________________");
        System.out.println("\tThese are the tasks in your list: ");
        for (int i = 0; i < taskCount; i += 1) {
            int index = i + 1;
            System.out.println("\t" + index + ".[" + tasks[i].getStatus() + "] " + tasks[i].getDescription());
        }
        System.out.println("  ____________________________________________________________");
    }

    public void markTask(String line) {
        String taskIndex = line.substring(5);
        int index = Integer.parseInt(taskIndex);
        tasks[index - 1].setCompletion(true);
        System.out.println("  ____________________________________________________________");
        System.out.println("\tWell done! I have marked this task as completed: ");
        System.out.println("\t  [" + tasks[index - 1].getStatus() + "] " + tasks[index - 1].getDescription());
        System.out.println("  ____________________________________________________________");
    }

    public void unmarkTask(String line) {
        String taskIndex = line.substring(7);
        int index = Integer.parseInt(taskIndex);
        tasks[index - 1].setCompletion(false);
        System.out.println("  ____________________________________________________________");
        System.out.println("\tNoted. I have marked this task as incomplete: ");
        System.out.println("\t  [" + tasks[index - 1].getStatus() + "] " + tasks[index - 1].getDescription());
        System.out.println("  ____________________________________________________________");
    }
}
