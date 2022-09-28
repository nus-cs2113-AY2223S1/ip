package Duke.Tasks;

public class Task {
    public String description;
    public boolean isDone;
    public char taskType;

    public Task(String description, char taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public char getTaskType() {
        return taskType;
    }

    public String taskStatusWithDescriptionText() {
        String output = "[" + this.getTaskType() + "]";
        output += "[" + this.getStatusIcon() + "] ";
        output += this.description;
        return output;
    }

    public String taskDataFileText() {
        String isDoneString = (this.isDone) ? "1" : "0";
        return taskType + " | " + isDoneString + " | " + description;
    }
}
