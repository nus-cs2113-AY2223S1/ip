public class Event extends Task {

    private String eventTime;

    public Event(String name, String eventTime) {
        super(name);
        this.eventTime = eventTime;
    }

    @Override
    public char taskType() {
        return 'E';
    }

    @Override
    public String taskDescription() {
        return String.format("%s (%s)", this.getName(), this.eventTime);
    }
}
