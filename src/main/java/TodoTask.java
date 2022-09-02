public class TodoTask extends Task {
    public TodoTask(String name) throws DukeException {
        super(name);
        if ("".equals(name)) {
            throw new DukeException("Todo name cannot be empty");
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
