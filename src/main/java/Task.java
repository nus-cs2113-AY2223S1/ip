
/**
 * Task stores the information of a task
 */
public class Task {

    // can be todo, deadline, event
    protected String type;

    public String getDescription() {
        return description;
    }

    protected String description;
    protected boolean isDone;

    public Task(String type, String description) {
        this.type = type;
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public String getTypeLetter() {
        switch (type){
            case "todo":
                return "T";
            case "deadline":
                return "D";
            case "event":
                return "E";
        }
        return "Invalid";
    }
    public void markAsDone(boolean done) {
        this.isDone = done;
    }

    public void printTask(){
        System.out.println("["+getTypeLetter()+"]["+getStatusIcon()+"] " + description);
    }


}
