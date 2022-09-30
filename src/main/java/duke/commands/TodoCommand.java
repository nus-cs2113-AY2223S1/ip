package duke.commands;

import duke.common.ErrorMessages;
import duke.data.TaskList;
import duke.data.exception.TodoNoTitleException;
import duke.data.task.Task;
import duke.storage.Storage;
import duke.ui.TextUi;

/**
 * Represents a todo command object that will execute the operations for Todo command.
 */
public class TodoCommand extends AddCommand {
    public static final String COMMAND = "todo";

    /**
     * Initialises the variables of the TodoCommand class.
     *
     * @param description A string that represents the title of the task.
     * @param isDone A boolean that indicates whether the task is done or undone.
     */
    public TodoCommand(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Executes the operations related to the command.
     *
     * @param ui An instance of the TextUi class.
     * @param tasks An instance of the TaskList class.
     * @param storage An instance of the Storage class.
     */
    @Override
    public void execute(TextUi ui, TaskList tasks, Storage storage) {
        try {
            // Checks the format of todo task to ensure that it has a task title
            checkTodoFormat(description);
            // Adds the todo task into the task list
            Task task = tasks.addTodo(description, isDone);
            // Shows information for the task that has been added into the program
            ui.showAddTaskInfo(task.getTaskDetails(), tasks.getTaskCount());
            // Writes each task from the updated task list into the file
            writeToFile(ui, tasks, storage);
        } catch (TodoNoTitleException | ArrayIndexOutOfBoundsException e) {
            ui.showCustomText(ErrorMessages.MESSAGE_ERROR_INVALID_TODO_FORMAT);
        }
    }
}
