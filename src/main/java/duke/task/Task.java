package duke.task;
public class Task {
    public static int numberOfTasks = 0;
    public String description;
    public boolean isDone;
    public String taskType = "[ ]";

    public Task(String description) {

        this.description = description;
        this.isDone = false;
        numberOfTasks++;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); //Mark done with X
    }
    public void printTask(){
        System.out.println(this.taskType + this.getStatusIcon() + " " + this.description);
    }
}
