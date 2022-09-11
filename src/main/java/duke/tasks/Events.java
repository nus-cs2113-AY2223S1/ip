package duke.tasks;

public class Events extends Task {
    private static String happeningAt;

    public static String getHappeningAt() {
        return happeningAt;
    }

    public Events(String taskName, String happeningAt) {
        super(taskName);
        this.happeningAt = happeningAt;
        this.type = "E";
    }

    public String toString() {
        String done = this.getIsDone() ? "[X]" : "[ ]";
        return "[E]" + done + this.getTaskName() + " (at: " + happeningAt + " )";
    }
}