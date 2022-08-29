public class Task {
    protected String name;
    protected boolean isDone;
    protected String type;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
        this.type = "none";
    }

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

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.name; 
    }
}