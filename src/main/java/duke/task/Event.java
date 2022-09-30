package duke.task;

/**
 * This class stores the information of a task which is an Event
 */
public class Event extends Task{
    public String getTaskIcon() {
        return "E";
    }

    public Event(String description) {
        super(description);
    }

    /**
     * Parses the input string and returns a string of String datatype, in a preferred format
     * @return the string representing the description of the event
     */
    public String getDescription() {
        int firstDivider = description.indexOf("event");
        int secondDivider = description.indexOf("/at");
        String descTask = description.substring(firstDivider + "event".length() + 1,secondDivider - 1);
        String duration = description.substring(secondDivider + "/at".length() + 1);
        String descString = descTask + " (at: " + duration + ")";
        return descString;
    }

    /**
     * Returns the raw description entered in by the user
     * @return the raw description of Event
     */
    public String getRawDescription() {
        return description;
    }
}
