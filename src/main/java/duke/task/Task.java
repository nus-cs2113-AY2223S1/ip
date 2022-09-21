package duke.task;

import duke.exception.EmptyDescriptionException;
import duke.exception.NoSpecficTimeException;
import duke.exception.NoSpecificDeadlineException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String task;
    protected boolean isDone;


    //Constructor
    public Task(String task) throws EmptyDescriptionException, NoSpecificDeadlineException, NoSpecficTimeException {
        boolean isEmpty = (task.length() == 0);
        boolean isDeadline = this.getClass().getSimpleName().equals("Deadline");
        boolean isEvent = this.getClass().getSimpleName().equals("Event");


        if (isEmpty) {
            throw new EmptyDescriptionException();
        } else {

            // Initialise task and print completion message
            if (isDeadline) {
                boolean hasDeadline = task.contains("(by:");
                if (!hasDeadline) {
                    throw new NoSpecificDeadlineException();
                } else {
                    this.task = task;
                }
            } else if (isEvent) {
                boolean hasTime = task.contains("(at:");
                if (!hasTime) {
                    throw new NoSpecficTimeException();
                } else {
                    this.task = task;
                }
            } else {
                this.task = task;

            }
            this.task = task;
        }
    }

    public String getTask() {
        // Return task
        return task;
    }

    public String getStatusIcon() {
        // Return X if marked and empty if unmarked
        return (isDone? "X" : " ");
    }

    public void setStatusIcon(String status) {
        // If it's marked, isDone is changed to true and false if unmarked
        if (status.equals("mark")) {
            isDone = true;
        } else {
            isDone = false;
        }
    }



}
