package Duke.data.tasks;

import Duke.data.exception.DukeException;

public class Todo extends Task{
    public Todo(String userInput) throws DukeException {
        super();
        if(userInput.equals(" ")) {
            throw new DukeException();
        }
        super.taskType = "T";
        super.description = userInput;
        super.dueDate = "";
    }
}
