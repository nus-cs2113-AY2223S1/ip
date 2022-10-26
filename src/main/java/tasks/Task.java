package tasks;

import ui.Ui;

import static ui.Messages.*;

/**
 * Stores the Task class, its attributes and methods.
 */
public class Task {
    protected String description;
    protected boolean isMarked;

    public Task(String description) {
        this.description = description;
        this.isMarked = false;
    }

    public Task(boolean isMarked) {
        this.isMarked = isMarked;
    }

    /**
     * Returns marked status of task as an icon (either X or blank)
     * @return String statusIcon
     */
    public String getStatusIcon() {
        return (isMarked ? "X" : " ");
    }

    /**
     * Returns a string of the marked status of a task and its description
     * @return
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Prints out a message when the user adds a task to the task list
     */
    public void taskMessage() {
        defaultAddMessage();
        System.out.println("added: " + description + "\n");
    }

    /**
     * Prints out a message when the user removes a task from the task list
     */
    public void deleteMessage(){
        defaultDeleteMessage();
        System.out.println("\n" + this);
    }

    /**
     * Sets the task to Marked and prints out a message saying that the task has been marked
     */
    public void setMarked() {
        markMessage();
        isMarked = true;
        System.out.println(this + "\n");
        Ui.border();
    }

    /**
     * Sets the task to Unmarked and prints out a message saying that the task has been unmarked
     */
    public void setUnmarked() {
        isMarked = false;
        unmarkMessage();
        System.out.println(this + "\n");
        Ui.border();
    }

}
