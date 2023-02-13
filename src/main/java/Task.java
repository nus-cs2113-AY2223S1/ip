/**
 * Task class
 * stores all types of tasks
 * contains a description of task (String description)
 * contains a status of the task (boolean isDone)
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Finds out whether the task is done or not done
     * @return "X" if the task is done or " " if the task is not done in String format
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }


    public void markAsDone(){
        if (isDone == false) {
            this.isDone = true;
        }
    }

    public void markAsUndone(){
        if(isDone == true){
            this.isDone = false;
        }
    }

    public String toString() {
        return "[" + this.getStatusIcon()+"] " + this.description;
    }

    //...
}