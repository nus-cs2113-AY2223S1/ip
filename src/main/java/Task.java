public class Task {
    protected String description;
    protected boolean isDone;

    public Task() {
        setDescription(null);
        setDone(false);
    }

    public String getTypeIcon() {
        return "[T]";
    }

    public String getStatusIcon() {
        return (this.isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    @Override
    public String toString() {
        return this.getTypeIcon() + this.getStatusIcon()
                + this.getDescription();
    }

    public void markDone(boolean done) {
        setDone(done);
        if (done) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(this);
    }
}
