package duke.task;

/**
 * This class stores the information of a task which has a Deadline
 */
public class Deadline extends Task{
    public String getTaskIcon() {
        return "D";
    }

    public Deadline(String description) {
        super(description);
    }

    /**
     * Parses the input string and returns a string of String datatype, in a preferred format
     * @return the string representing the description of the event
     */
    public String getDescription() {
        int firstDivider = description.indexOf("deadline");
        int secondDivider = description.indexOf("/by");
        String descTask = description.substring(firstDivider + "deadline".length() + 1,secondDivider - 1);
        String duration = description.substring(secondDivider + "/by".length() + 1);
        String descString = descTask + " (by: " + duration + ")";
        return descString;
    }
    /**
     * Returns the raw description entered in by the user
     * @return the raw description of a Deadline
     */
    public String getRawDescription() {
        return description;
    }
}
