package duke.commands.add;

import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Adds a todo task to the list.
 */
public class ToDoCommand extends AddCommand {
    public static final String COMMAND_WORD = "todo";
    private final String taskName;

    /**
     * Constructs constructor for ToDo command which stores the todo task name.
     *
     * @param taskName Name of todo task.
     */
    public ToDoCommand(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Adds a todo task to the list, prints confirmation message and updates duke.txt if successful.
     * Otherwise, prints exception message if external error occurs.
     *
     * @param taskList Used to access taskList information.
     * @param ui Used to print confirmation or exception messages.
     * @param storage Used to update task information in duke.txt.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addToDoTask(taskName);
        ui.printTaskAddedMessage(taskList);
        try {
            storage.addTaskToDukeTextFile("T | 0 | " + taskName);
        } catch (IOException e) {
            ui.showIOExceptionMessage();
        }
    }
}
