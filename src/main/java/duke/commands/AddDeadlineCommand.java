package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.tasks.Deadline;

/**
 * Represent a command with command word, description and time due specified.
 * There is also a Deadline initiated which is used in the execute method to be added to task list.
 * Used when command word is 'deadline'.
 */
public class AddDeadlineCommand extends AddCommand {

    protected String by;
    protected Deadline toAdd;

    public AddDeadlineCommand(String commandWord, String taskline, String by) {
        super(commandWord, taskline);
        this.by = by;
        this.toAdd = new Deadline(taskline, by);
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        tasks.addDeadline(toAdd, storage);
    }
}
