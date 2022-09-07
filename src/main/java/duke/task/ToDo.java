package duke.task;

import duke.task.Task;

public class ToDo extends Task {
    /** To check if the task is to do task */
    protected boolean isToDo;

    public ToDo(String description) {
        super(description);
        this.isToDo = false;
    }

    @Override
    public String getStatusOfTypeTask() {
        String status = " ";
        if (isToDo) {
            status = "T";
        }
        return status;
    }

    @Override
    public void markTypeTask() {
        isToDo = true;
    }
}
