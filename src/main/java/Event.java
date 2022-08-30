public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String complexString) {
        super();
        int i = complexString.indexOf("/");
        this.description = complexString.substring(0, i);
        this.at = complexString.substring(i + SEPARATOR_LENGTH);
        this.isDone = false;
    }

    public String getAt() {
        return this.at;
    }

    public void setAt(String At) {
        this.at = at;
    }

    @Override
    public String getStatusIcon() {
        String statusIcon = "[E]";
        return statusIcon + super.getStatusIcon();
    }

    @Override
    public String toString() {
        return String.format("%s %s(at: %s)", this.getStatusIcon(), description, this.at);
    }
}
