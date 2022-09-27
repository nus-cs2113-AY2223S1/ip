package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.tasks.Deadline;

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
