package duke.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String getTaskInfo() {
        String statusIcon = isDone ? "X" : " ";
        return "[T]" +"[" + statusIcon + "] " + this.description;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }
    @Override
    public String toString () {
        int isDoneInNumber = this.isDone ? 1 : 0;
        return String.format("T | %d | %s", isDoneInNumber, description) + System.lineSeparator();
    }
}

