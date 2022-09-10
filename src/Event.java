public class Event extends Task {
    String at;
    public Event(String description, String time) {
        super(description);
        at = time;
    }
    @Override
    public String getDescription() {
        return this.description + this.at;
    }

    @Override
    public String getDescriptionAndStatus() {
        return "[E][" + this.getStatus() + "] " + this.getDescription();
    }

    @Override
    public String fileFormat() {
        return "E | " + this.getStatus() + " | " + this.description + " | " + this.at;
    }
}