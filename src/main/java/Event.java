public class Event extends Task{
    protected String at;

    public String getAt() {
        return at;
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
        return super.toString() + " (at: " + at + ")";
    }

    public Event(String command, Character lastChar) {
        super(command, lastChar);
        int byStartIdx = command.indexOf('/') + 4;
        this.at = command.substring(byStartIdx);
    }
}
