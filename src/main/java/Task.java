public class Task {
    /*
    * a string storing the user's tasking.
    * */
    protected String description;
    /*
     * a boolean variable tracking the completeness of the tasking.
     */

    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

}