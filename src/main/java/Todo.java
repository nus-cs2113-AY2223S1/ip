public class Todo extends Task {

    public Todo(String description) throws DukeException {
        super(description);
        if (description == "") {
            throw new DukeException("TodoDescriptionError");
        }
    }

    @Override
    protected String getTaskType() {
        return "T";
    }
}
