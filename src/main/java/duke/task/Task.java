package duke.task;

import duke.exception.EmptyDescriptionException;
import duke.exception.NoSpecificTimeException;
import duke.exception.NoSpecificDeadlineException;

public class Task {
    protected String task;
    protected boolean isDone;


    /**
     * Constructs a new task.
     *
     * @param task The task to be store in the list of task.
     * @throws EmptyDescriptionException If task is empty
     * @throws NoSpecificDeadlineException If /by is not specified for deadline task
     * @throws NoSpecificTimeException If /at is not specified for event task.
     */
    public Task(String task) throws EmptyDescriptionException, NoSpecificDeadlineException, NoSpecificTimeException {
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
                }
            } else if (isEvent) {
                boolean hasTime = task.contains("(at:");
                if (!hasTime) {
                    throw new NoSpecificTimeException();
                }
            }

            this.task = task;
        }
    }


    /**
     * Returns the task that is stored at current iteration of list of task.
     *
     * @return The task of current iteration in string.
     */
    public String getTask() {
        return task;
    }

    /**
     * Returns the mark status of the task at current iteration of list of task.
     *
     * @return "X" If task is marked and " " if task is unmarked.
     */
    public String getStatusIcon() {
        // Return X if marked and empty if unmarked
        return (isDone? "X" : " ");
    }


    /**
     * Sets the mark status of the task at current iteration of list of task.
     *
     * @param status Either "mark" or "unmark"
     */
    public void setStatusIcon(String status) {
        // If it's marked, isDone is changed to true and false if unmarked
        if (status.equals("mark")) {
            isDone = true;
        } else {
            isDone = false;
        }
    }



}
