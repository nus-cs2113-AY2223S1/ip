package duke.commands;

import duke.Storage;
import duke.TaskList;

public class MarkCommand extends Command {

    protected int index;
    protected boolean isDone;

    public MarkCommand(String commandWord, int index, boolean isDone) {
        super(commandWord);
        this.index = index;
        this.isDone = isDone;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        try {
            tasks.markingAction(index, isDone, storage);
        } catch(IndexOutOfBoundsException e) {
            System.out.println("\tIndex entered is out of range, please re-enter your input.");
        }
    }
}
