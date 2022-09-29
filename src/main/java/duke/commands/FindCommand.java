package duke.commands;

import duke.Storage;
import duke.TaskList;

/**
 * Represent a command with command word and content specified.
 * Used when command word is 'find' to find the tasks containing the content string in description.
 */
public class FindCommand extends Command {

    protected String content;

    public FindCommand(String commandWord, String content) {
        super(commandWord);
        this.content = content;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        tasks.find(content);
    }
}
