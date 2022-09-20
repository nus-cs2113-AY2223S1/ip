package duke.commands;

import duke.data.TaskList;
import duke.data.exception.TodoNoDescriptionException;
import duke.storage.Storage;
import duke.ui.TextUi;

public class AddCommand extends Command {
    protected String description;
    protected boolean isDone;

    public AddCommand(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void checkTodoFormat(String description) throws TodoNoDescriptionException {
        if (description.isBlank()) {
            throw new TodoNoDescriptionException();
        }
    }

    public void execute(TextUi ui, TaskList tasks, Storage storage) {}
}
