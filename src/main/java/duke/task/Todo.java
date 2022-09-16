package duke.task;

public class Todo extends Task{
    public Todo(String description) {
        super(description);
    }

    /**
     * Method to convert the task to print format
     * @return string in print format
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Method to convert the task to output file format
     * @return string in output file format
     */
    @Override
    public String toOutputFileFormat() {
        String out = "todo " + description;
        if (this.isDone) {
            return (out + " X");
        } else {
            return (out + " O");
        }
    }
}
