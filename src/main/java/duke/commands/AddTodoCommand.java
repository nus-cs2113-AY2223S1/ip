package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.InvalidCommandFormatException;

/**
 * When executed, it adds a todo task to taskList
 */
public class AddTodoCommand extends AddCommand {
    public static final String TODO_COMMAND = "todo";
    protected TaskList taskList;

    public AddTodoCommand(String[] taskDescription, TaskList taskList) {
        super(taskDescription);
        try {
            TaskList.addTodoTask(taskDescription);
            Storage.loadTasktoDataFile(taskList);
        } catch (InvalidCommandFormatException e) {
            Ui.printCorrectFormatText(AddTodoCommand.TODO_COMMAND);
        }
    }
}
