package duke.taskmanager.tasks;

public class Deadline extends Todo {
    protected String by;

    public Deadline(String command, Character lastChar) {
        super(command, lastChar);
        int byStartIdx = command.indexOf('/') + "by _".length();
        command = command.substring(byStartIdx);
        findDateAndTime(command);
    }

    public String getBy() {
        return formatDate() + " " + this.time;
    }

    @Override
    protected String getTypeIcon() {
        return "[D]";
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + getBy() + ")";
    }


}
