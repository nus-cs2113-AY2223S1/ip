package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.InvalidCommandFormatException;

/**
 * When executed, it adds a deadline task to taskList
 */
public class AddDeadlineCommand extends AddCommand {
    public static final String DEADLINE_COMMAND = "deadline";
    protected TaskList taskList;

    public AddDeadlineCommand(String[] taskDescription, TaskList taskList) {
        super(taskDescription);
        try {
            TaskList.addDeadlineTask(taskDescription);
            Storage.loadTasktoDataFile(taskList);
        } catch (InvalidCommandFormatException e) {
            Ui.printCorrectFormatText(AddDeadlineCommand.DEADLINE_COMMAND);
        }
    }
}
