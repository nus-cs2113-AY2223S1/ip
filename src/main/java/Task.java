public class Task {
    protected String description;
    protected boolean isDone;
    protected int id;
    protected static int numberOfTasks = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        numberOfTasks ++;
        this.id = numberOfTasks;
    }

    public String getStatusIcon() { return (isDone ? "X" : " "); }

    public String toString() { return "[ ]" + "[" + getStatusIcon() + "] " + description; }

    public void setDone() { isDone = true; }

    public void setNotDone() { isDone = false; }

    public static int getNumberOfTasks() { return numberOfTasks; }

    public static void printHorizontalLine() {
        System.out.println("    ____________________________________________________________");
    }

    public void printNewTask() {
        printHorizontalLine();
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + this);
        System.out.println("     Now you have " + Task.getNumberOfTasks() + " tasks in the list.");
        printHorizontalLine();
    }

    public static void printTaskList(Task[] tasks) {
        Task.printHorizontalLine();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < Task.getNumberOfTasks(); i++) {
            System.out.println("     " + Integer.toString(tasks[i].id) + "." + tasks[i]);
        }
        Task.printHorizontalLine();
    }

    public void printMark() {
        Task.printHorizontalLine();
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + this);
        Task.printHorizontalLine();
    }

    public void printUnmark() {
        Task.printHorizontalLine();
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("       " + this);
        Task.printHorizontalLine();
    }
}