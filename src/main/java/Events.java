public class Events extends Task {
    private static String at;

    public Events(String taskName, String at) {
        super(taskName);
        this.at = at;
    }

    public String toString() {
        char done = this.getIsDone() ? 'X' : ' ';
        return "[E][" + done + "] " + this.getTaskName() + " (at: " + at + " )";
    }
}