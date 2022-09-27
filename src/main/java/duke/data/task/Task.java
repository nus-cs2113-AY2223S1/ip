package duke.data.task;

import java.time.format.DateTimeFormatter;

/**
 * Stores tasks.
 */
public class Task {
    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final String DATE_TIME_FORMAT = "dd/MM/yyyy HHmm";

    protected String description;
    protected boolean isComplete;

    /**
     * Initializes task object.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isComplete = false;
    }

    private void printTodo() {
        Todo todo = (Todo) this;

        String printString = "[T][";
        if (todo.isComplete) {
            printString += "X";
        } else {
            printString += " ";
        }
        printString += "] " + todo.description;

        System.out.println(printString);
    }

    private void printDeadline() {
        Deadline deadline = (Deadline) this;

        String printString = "[D][";
        if (deadline.isComplete) {
            printString += "X";
        } else {
            printString += " ";
        }
        printString += "] ";
        printString += deadline.description;
        printString += " (by: ";
        printString += deadline.by.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
        printString += ")";

        System.out.println(printString);
    }

    private void printEvent() {
        Event event = (Event) this;

        String printString = "[E][";
        if (event.isComplete) {
            printString += "X";
        } else {
            printString += " ";
        }
        printString += "] ";
        printString += event.description;
        printString += " (at: ";
        printString += event.startAt.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
        printString += " to ";
        printString += event.endAt.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
        printString += ")";

        System.out.println(printString);
    }

    private void printTask() {
        String printString = "[ ][";
        if (isComplete) {
            printString += "X";
        } else {
            printString += " ";
        }
        printString += "] ";
        printString += description;

        System.out.println(printString);
    }

    /**
     * Prints task to standard out.
     */
    public void print() {
        if (this instanceof Todo) {
            printTodo();
        } else if (this instanceof Deadline) {
            printDeadline();
        } else if (this instanceof Event) {
            printEvent();
        } else {
            printTask();
        }
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
