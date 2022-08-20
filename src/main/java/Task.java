public class Task {

    private int taskNumber;
    private String name;
    private boolean done;
    private static int tasksCount = 0;

    public Task(String name) {
        tasksCount++;
        setTaskNumber(tasksCount);
        setName(name);
        this.done = false;
    }

    public void setTaskNumber(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static int getTasksCount() {
        return tasksCount;
    }

    public void markAsDone() {
        this.done = true;
        System.out.println("Well done. I've marked this task as done:");
        listTask();
    }

    public void markAsNotDone() {
        this.done = false;
        System.out.println("Boo! I've marked this task as not done yet:");
        listTask();
    }

    public char doneIcon() {
        if(this.done) {
            return 'X';
        }
        return ' ';
    }

    public void listTask() {
        System.out.println(this.taskNumber + ".[" + doneIcon() + "] " + this.name);
    }

}
