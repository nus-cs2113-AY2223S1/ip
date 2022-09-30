package Tasks;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    /**
     * Converts task to string.
     * @return Task in string.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts task to string to be saved.
     * @return String to be saved.
     */
    @Override
    public String toSave() {
        return "T," + super.toSave();
    }
}