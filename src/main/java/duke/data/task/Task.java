package duke.data.task;

import java.time.LocalDate;

public class Task {
    public static final String TYPE_TASK_WRAP = "[ ]";
    public static final String LIMITER = " | ";
    public static final String PARSE_LIMITER = "\\|";

    public static int numberOfTasks = 0;
    public String description;
    public LocalDate date;
    public boolean isDone;
    public String taskType;
    public String taskTypeWrap = TYPE_TASK_WRAP;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        numberOfTasks++;
    }

    public void setIsDone(boolean status) {
        this.isDone = status;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); //Mark done with X
    }

    public String toString() {
        return (this.taskTypeWrap + this.getStatusIcon() + " " + this.description);
    }

    public String toSave() {
        return (this.taskType + LIMITER + this.isDone + LIMITER + this.description + "\n");
    }
    public boolean isDateNull(){
        return true;
    }
    public LocalDate getDate(){
        return this.date;
    }
}
