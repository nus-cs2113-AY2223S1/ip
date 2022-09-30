package duke.task;

import duke.Storage;

public class Task {
    private String description;
    private boolean isDone;
    public final String isDoneString = "X";
    public final String isNotDoneString = " ";

    /**
     * creates a new task
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * marks a task as done and prints message informing the client
     */
    public void markDone() {
        System.out.println("Nice! I've marked this task as done:");
        setMark();
        System.out.println(" [X] " + description);
    }

    /**
     * sets isDone to be true
     */
    public void setMark() {
        isDone = true;
    }


    /**
     * marks a task as undone and prints message informing the client
     */
    public void unmarkDone() {
        System.out.println(" OK, I've marked this task as not done yet:");
        isDone = false;
        System.out.println(" [ ] " + description);
    }

    /**
     * returns string representing to print to the user according to the isDone marking
     */
    public String getIsDoneMarking() {
        if (isDone) {
            return isDoneString;
        }
        return isNotDoneString;
    }


    /**
     * returns string representing to print to in file format
     */
    public String isDoneFileFormat() {
        String isDone = "0";
        if (getIsDone()) {
            isDone = "1";
        }
        return isDone;
    }

    /**
     * returns isDone status
     */
    public boolean getIsDone() {
        return isDone;
    }


    /**
     * returns task description
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * returns file format print for the task
     */
    public String getFileFormat() {
        return Storage.divider + isDoneFileFormat()
                + Storage.divider + getDescription();
    }

}
