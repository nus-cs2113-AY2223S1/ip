public class Todo extends Task {

    public Todo(String description) throws DukeException {
        super(description);
        if (description == "") {
            throw new DukeException("TodoDescriptionError");
        }
    }

    public Todo(String description, boolean isDone){
        super(description,isDone);
    }

    @Override
    protected String getTaskType() {
        return "T";
    }
}
