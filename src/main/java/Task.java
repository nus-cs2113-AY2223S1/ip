public class Task {
    protected String description;
    protected boolean isComplete;

    public Task(String description) {
        this.description = description;
        this.isComplete = false;
    }

    public void print() {
        String printString = "[";

        if (this instanceof Todo) {
            printString += "T";
        } else if (this instanceof Deadline) {
            printString += "D";
        } else if (this instanceof Event) {
            printString += "E";
        } else {
            printString += " ";
        }

        printString += "][";

        if (isComplete) {
            printString += "X";
        } else {
            printString += " ";
        }

        printString += "] " + description;

        if (this instanceof Deadline) {
            Deadline deadline = (Deadline) this;
            printString += " (by: " + deadline.by + ")";
        } else if (this instanceof Event) {
            Event event = (Event) this;
            printString += " (at: " + event.at + ")";
        } else {
            printString += "";
        }
        System.out.println(printString);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }
}
