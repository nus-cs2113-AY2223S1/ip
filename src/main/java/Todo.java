/**
 * Defines The todo task, which has a description
 */
public class Todo extends Task {

    /**
    * Initializes a todo task with a user input
    * @param description the description of a task
    * @throws DukeException If user input is invalid 
    */
    public Todo(String description) throws DukeException {
        super(description);
        if (description == "") {
            throw new DukeException("TodoDescriptionError");
        }
    }

    /**
    * Initializes a todo task with direct information
    * (usually used when restoring task from disk)
    * @param description the description of the task
    * @param isDone the boolean var to state whether the task is done
    */
    public Todo(String description, boolean isDone){
        super(description,isDone);
    }

    @Override
    protected String getTaskType() {
        return "T";
    }
}
