package duke.commands;

import duke.common.ErrorMessages;
import duke.data.TaskList;
import duke.data.exception.TodoNoDescriptionException;
import duke.data.task.Task;
import duke.storage.Storage;
import duke.ui.TextUi;

public class TodoCommand extends AddCommand {
    public static final String COMMAND = "todo";

    public TodoCommand(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public void execute(TextUi ui, TaskList tasks, Storage storage) {
        try {
            checkTodoFormat(description);
            Task task = tasks.addTodo(description, isDone);
            ui.showAddTaskInfo(task.getTaskDetails(), tasks.getTaskCount());
            writeToFile(ui, tasks, storage);
        } catch (TodoNoDescriptionException | ArrayIndexOutOfBoundsException e) {
            ui.showCustomText(ErrorMessages.MESSAGE_ERROR_INVALID_TODO_FORMAT);
        }
    }
}
