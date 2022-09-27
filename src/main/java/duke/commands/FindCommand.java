package duke.commands;

import duke.Storage;
import duke.TaskList;

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
