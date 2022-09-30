package tasks;

/**
 * Represents an Event type task in this application.  Inherits from Task.
 */
public class Event extends Task {
    public String at;

    public Event(String descriptionString, String at) {
        super(descriptionString);
        this.at = at;
    }

    /**
     * Creates the string representation of the Event that will be printed to user
     * 
     * @return formatted string representing Event and associated information
     */
    @Override
    public String toString() {
        return "[E]" + super.getStatusIcon() + " " + super.descriptionString + " (at:" + this.at + ")";
    }

    /**
     * Creates the string which represents the Event for 
     * saving into the file.
     * 
     * @return formatted string representing Event and associated information
     */
    @Override
    public String createFileString() {
        String marked = "N";
        if (this.isDone) {
            marked = "Y";
        }
        return "E|" + marked + "|" + super.descriptionString + "|" + this.at;
    }
}
