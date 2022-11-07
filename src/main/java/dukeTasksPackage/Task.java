package dukeTasksPackage;

public abstract class Task {
    public String description;
    protected int number;
    public  char status = ' ';
    public char alphabet = ' ';
    public boolean isDone;

    public String toFileString() {
        return alphabet + " | " + status + " | " + description;
    }
}
