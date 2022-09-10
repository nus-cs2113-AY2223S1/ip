package duke.task;

import duke.EmptyDescriptionException;
import duke.NoSpecficTimeException;
import duke.NoSpecificDeadlineException;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;

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
    public void printTask(int index) {
        // Print the task in numbered list format.
        System.out.println((index + 1) + ". [" + getStatusIcon() + "] " + getTask());
    }


}
