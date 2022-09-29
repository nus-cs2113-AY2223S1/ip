package duke.taskmanager.tasks;

public class Event extends Todo {
    public Event(String command, Character lastChar) {
        super(command, lastChar);
        int atStartIdx = command.indexOf('/') + "at _".length();
        command = command.substring(atStartIdx);
        findDateAndTime(command);
    }

    public String getAt() {
        return formatDate() + " " + this.time;
    }

    @Override
    protected String getTypeIcon() {
        return "[E]";
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + getAt() + ")";
    }
}
