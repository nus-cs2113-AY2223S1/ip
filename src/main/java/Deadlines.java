public class Deadlines extends Task {
    private static String toBeDonBy;

    public Deadlines(String taskName, String toBeDonBy) {
        super(taskName);
        this.toBeDonBy = toBeDonBy;
    }

    public String toString() {
        String done = this.getIsDone() ? "[X]" : "[ ]";
        return "[D]" + done + this.getTaskName() + " (by: " + toBeDonBy + " )";
    }
}