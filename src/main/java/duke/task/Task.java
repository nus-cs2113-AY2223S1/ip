package duke.task;

import duke.TaskManager;

public class Task extends TaskManager {
    protected String description;
    public boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        if (isDone) { // mark done task with X
            return "[X] ";
        } else {
            return "[ ] ";
        }
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
}