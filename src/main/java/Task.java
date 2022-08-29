public class Task {
    protected String description;
    protected boolean isDone;

    public Task() {
        this.description = null;
        this.isDone = false;
    }
    public Task(String command, Character lastChar) {
        int DescStartIdx = command.indexOf(' ') + 1;
        String description;
        if(lastChar.equals(' ')){
            description = command.substring(DescStartIdx);
        } else {
            int DescEndIdx = command.indexOf('/');
            description = command.substring(DescStartIdx,DescEndIdx);
        }
        this.description = description;
        this.isDone = false;
    }
    public String getTypeIcon() {
        return "[T]"; // mark done task with X
    }
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    //...

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        return this.getTypeIcon() + this.getStatusIcon()
                + this.getDescription();
    }
    public void markDone(boolean done){
        setDone(done);
        if (done) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(this);
    }
}
