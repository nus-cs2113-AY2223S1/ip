public class Task {
    protected String description;

    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    public String getStatusIcon() {
        if (isDone) { // mark done task with X
            return "[X] ";
        } else {
            return "[ ] ";
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDone(boolean done) {
        if (done == false) {
            this.isDone = true;
        } else {
            this.isDone = false;
        }
    }

    @Override
    public String toString() {

        return getStatusIcon() + description;
//        return getStatusIcon() ;
    }
}