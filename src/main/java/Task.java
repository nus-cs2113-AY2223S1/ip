/**
 * Base task file
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * marks item as done
     */
    public void markAsDone(){
        this.isDone = true;
    }

    /**
     * marks item as undone
     */
    public void unMark() {
        this.isDone = false;
    }

    /**
     * Gets the status icon based on if task is completed
     * @return string of either [] or [x]
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[]");
    }
    /**
     * Gets the command icon based on what type of task it is
     * @return string character of E,T,D
     */
    public String getCommandIcon() { return ""; }
    /**
     * Gets the description of the task
     * @return string value of the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * checks if task is done
     * @return int, either 1 or 0
     */
    public int getDone(){
        return isDone ? 1 : 0;
    }

    /**
     * gives back the filestring
     * @return String
     */
    public String fileString(){
        return ("");
    }
}
