public class Task {
    private String name;
    private boolean isDone;
    private char type;

    public Task(String name, boolean isDone, char type) {
        setname(name);
        setDone(isDone);
        this.type = type;
    }
    public String toString() {
        return name;
    }
    public String getName() {
        return name;
    }
    public void setname(String name) {
        this.name = name;
    }
    public boolean isDone() {
        return isDone;
    }
    public void setDone(boolean done) {
        isDone = done;
    }
    public char getType() {
        return type;
    }
    public void setType(char type) {
        this.type = type;
    }
}
