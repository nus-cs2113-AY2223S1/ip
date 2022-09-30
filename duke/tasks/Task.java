package duke.tasks;
public class Task {
    protected String name;
    public boolean isDone;
    protected String type;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
        this.type = "none";
    }

    /**
     * Returns the status of the task
     * @return X if done, space if neither
     */
    public String getStatusIcon() {
        if (this.isDone) {
            return "X";
        } else {
            return " ";
        }
    }

    public String getType() {
        return this.type;
    }
    public String getName() { return this.name; }

    public boolean getIsDone() { return this.isDone; }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    /**
     * Returns all the information of the task as a string
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.name; 
    }
    public String getDate() { return ""; }
}