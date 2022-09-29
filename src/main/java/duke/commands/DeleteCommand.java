package duke.commands;

import duke.Storage;
import duke.TaskList;

/**
 * Represent a command with command word specified.
 * Used when command word is 'bye' to exit the program.
 */
public class DeleteCommand extends Command {

    protected int index;

    public DeleteCommand(String commandWord, int index) {
        super(commandWord);
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        try {
            tasks.delete(index, storage);
        } catch(IndexOutOfBoundsException e) {
            System.out.println("\tIndex entered is out of range, please re-enter your input.");
        }
    }
}
