public class Deadlines extends Task {
    private static String by;

    public Deadlines(String taskName, String by) {
        super(taskName);
        this.by = by;
    }

    public String toString() {
        char done = this.getIsDone() ? 'X' : ' ';
        return "[D][" + done + "] " + this.getTaskName() + " (by: " + by + " )";
    }
}