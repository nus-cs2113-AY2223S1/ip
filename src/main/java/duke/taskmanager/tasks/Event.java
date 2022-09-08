package duke.taskmanager.tasks;

public class Event extends Todo {
    protected String at;

    public Event(String command, Character lastChar) {
        super(command, lastChar);
        int atStartIdx = command.indexOf('/') + "at _".length();
        setAt(command.substring(atStartIdx));
    }

    public String getAt() {
        return this.at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    @Override
    public String getTypeIcon() {
        return "[E]";
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + getAt() + ")";
    }
}
