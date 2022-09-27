/**
 * Base task file
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }
    public void markAsDone(){
        this.isDone = true;
    }

    public void unMark(){
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
    public String getCommandIcon(){return "";}
    /**
     * Gets the description of the task
     * @return string value of the description
     */
    public String getDescription() {
        return description;
    }
    public int getDone(){
        return isDone ? 1 : 0;
    }
    public String fileString(){
        return ("");
    }
}
