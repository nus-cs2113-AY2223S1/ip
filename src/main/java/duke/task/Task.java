package duke.task;

import duke.TaskList;

public class Task extends TaskList {
    public String description;
    public boolean isDone;
    

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
            return isDone ? "[X] " : "[ ] ";
    }

    public String getDescription() {
        return description;
    }

    public void setDone(boolean done) {
        this.isDone = !done;
    }

    @Override
    public String toString() {
        return getStatusIcon() + description;
    }

    public boolean isDone() {
        return isDone;
    }

    public static String getBoolValue(Task task) {
        String boolValue;
        if (task.isDone()) {
            boolValue = "1";
        } else {
            boolValue = "0";
        }
        return boolValue;
    }
}