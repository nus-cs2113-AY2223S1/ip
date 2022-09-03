public class Event extends Task {

    private String eventTime;

    public Event(String arguments) throws DukeException {
        super(arguments);
        String eventTime = extractTime(arguments);
        if (eventTime.length() == 0) {
            throw new DukeException(ExceptionType.MISSING_TIME);
        }
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
