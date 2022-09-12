package duke;
public class Task {
    public String getSYMBOL;
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    // mark done task with X
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public boolean getStatus() {
        return isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    public String print() {
        String s = new String("[" + this.getStatusIcon() + "] " + this.getDescription());
        return s;
    }

    public String printToFile() {
        String isDoneStr = new String((this.isDone) ? "1" : "0");
        String s = new String("T|" + isDoneStr + "|" + this.description + "\n");
        return s;
    }

}