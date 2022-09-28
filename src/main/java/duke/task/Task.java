package duke.task;

import duke.TaskManager;

public class Task extends TaskManager {
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
        if (done == false) {
            this.isDone = true;
        } else {
            this.isDone = false;
        }
    }

    @Override
    public String toString() {

        return getStatusIcon() + description;
//        return getStatusIcon() ;
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