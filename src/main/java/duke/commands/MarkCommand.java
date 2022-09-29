package duke.commands;

import duke.Storage;
import duke.TaskList;

/**
 * Represent a command with command word, index and completion status specified.
 * Used when command word is 'mark' or 'unmark' to update the completion status of the task intended.
 */
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
