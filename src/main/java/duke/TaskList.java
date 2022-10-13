package duke;

/*
base case for Task
 */

public class TaskList {
    protected String name;
    static public int currTotal = 0;
    public int index;
    protected boolean isDone;
    protected String dateDeadline;

    /**
     * Constructs a Task object.
     * @param name The description of task.
     */
    public TaskList(String name) {
        this.name = name;
        this.index = currTotal + 1;
        currTotal += 1;
        this.isDone = false;
    }


    /**
     * Gets the icon depending on isDone status.
     * @return [X] if not done, [ ] is done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void UpdateStatus(){
        this.isDone = !this.isDone;
    }

    public static void countRemainingTasks() {
        System.out.println("Now you have " + currTotal + " tasks in the list");
    }
    /**
     * Update the index of a particular task to be index -1 after removal.
     */
    public void UpdateRemoval(){
        this.index -=1;
    }

    /**
     * Helper function to call while deleting a task, to keep track of the total number of tasks.
     * @param task The task being deleted.
     */
    public static void removeTask(TaskList task) {
        currTotal -= 1;
    }


    public String toString(){
        return ("["+this.getStatusIcon()+"] " + this.name);
    }
    /**
     * Gets the "1" or "0" depending on isDone status.
     * @return 1 if not done, 0 is done.
     */
    public String getStatusString() {
        return (this.isDone ? "1" : "0");
    }
    public String recordString() {
        return getStatusString() + " | " + name;
    }

}