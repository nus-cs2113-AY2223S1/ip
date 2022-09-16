package duke.task;

public class Event extends Task{
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Method to convert the event to print format
     * @return string in print format
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Method to convert the event to output file format
     * @return string in output file format
     */
    @Override
    public String toOutputFileFormat() {
        String out = "event " + description + " /at " + at;
        if (this.isDone) {
            return (out + " X");
        } else {
            return (out + " O");
        }
    }
}