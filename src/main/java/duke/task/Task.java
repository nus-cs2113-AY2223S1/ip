package duke.task;

public class Task {
    protected String description;
    protected boolean isComplete;

    public Task(String description) {
        this.description = description;
        this.isComplete = false;
    }

    public String convertToString() {
        String taskStr = "";

        if (this instanceof Todo) {
            taskStr += "T";
        } else if (this instanceof Deadline) {
            taskStr += "D";
        } else if (this instanceof Event) {
            taskStr += "E";
        } else {
            taskStr += " ";
        }

        taskStr += " | ";

        if (this.isComplete) {
            taskStr += "1";
        } else {
            taskStr += "0";
        }

        taskStr += " | ";

        if (this instanceof Todo) {
            Todo todo = (Todo) this;
            taskStr += todo.getDescription();
        } else if (this instanceof Deadline) {
            Deadline deadline = (Deadline) this;
            taskStr += deadline.getDescription();
            taskStr += " | ";
            taskStr += deadline.by;
        } else if (this instanceof Event) {
            Event event = (Event) this;
            taskStr += event.getDescription();
            taskStr += " | ";
            taskStr += event.at;
        } else {
            taskStr += this.getDescription();
        }

        return taskStr;
    }

    public void print() {
        String printString = "[";

        if (this instanceof Todo) {
            printString += "T";
        } else if (this instanceof Deadline) {
            printString += "D";
        } else if (this instanceof Event) {
            printString += "E";
        } else {
            printString += " ";
        }

        printString += "][";

        if (isComplete) {
            printString += "X";
        } else {
            printString += " ";
        }

        printString += "] " + description;

        if (this instanceof Deadline) {
            Deadline deadline = (Deadline) this;
            printString += " (by: " + deadline.by + ")";
        } else if (this instanceof Event) {
            Event event = (Event) this;
            printString += " (at: " + event.at + ")";
        } else {
            printString += "";
        }
        System.out.println(printString);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }
}
