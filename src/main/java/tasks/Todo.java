package tasks;

/**
 * Represents a Todo type task in this application.  Inherits from Task.
 */
public class Todo extends Task{

    public Todo(String descriptionString) {
        super(descriptionString);
    }

    /**
     * Creates the string representation of the Todo that will be printed to user
     * 
     * @return formatted string representing Todo and associated information
     */
    @Override
    public String toString() {
        return "[T]" + super.getStatusIcon() + " " + super.descriptionString;
    }

    /**
     * Creates the string which represents the Todo for 
     * saving into the file.
     * 
     * @return formatted string representing Todo and associated information
     */
    @Override
    public String createFileString() {
        String marked = "N";
        if (this.isDone) {
            marked = "Y";
        }
        return "T|" + marked + "|" + super.descriptionString;
    }
}
