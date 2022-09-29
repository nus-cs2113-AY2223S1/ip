public class Todo extends Task {

    public Todo(String description) throws DukeException {
        super(description);
    }

    /**
     * Returns formatted string
     *
     * @return string to print
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns formatted string for saved file
     *
     * @return string to save
     */
    @Override
    public String toSaveString() {
        return "T|" + super.toSaveString();
    }
}
