package tasks;

/**
 * Represents a Deadline task in this application. Inherits from Task.
 */
public class Deadline extends Task{
    public String by;

    public Deadline(String descriptionString, String by) {
        super(descriptionString);
        this.by = by;
    }

    /**
     * Creates the string representation of the Deadline that will be printed to user
     * 
     * @return formatted string representing Deadline and associated information
     */
    @Override
    public String toString() {
        return "[D]" + super.getStatusIcon() + " " + super.descriptionString + " (by:" + this.by + ")";
    }

    /**
     * Creates the string which represents the Deadline for 
     * saving into the file.
     * 
     * @return formatted string representing Deadline and associated information
     */
    @Override
    public String createFileString() {
        String marked = "N";
        if (this.isDone) {
            marked = "Y";
        }
        return "D|" + marked + "|" + super.descriptionString + "|" + this.by;
    }
}
