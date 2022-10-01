package duke.task;

public class Task {
    public String description;
    public boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Method to convert task to print format
     * @return string task in print format
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

    /**
     * Method to convert task to output file format, will be over rid in subclass
     * @return empty string as temporary placeholder
     */
    public String toOutputFileFormat() {
        return " ";
    }
}
