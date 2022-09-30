package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class HelpCommand extends Command {
    public static final String KEYWORD = "help";
    
    public HelpCommand(String input) {
        super(input);
    }

    @Override
    public TaskList execute(TaskList tasks, Storage storage, Ui ui, String fullCommand) {
        Ui.printHelp();
        return tasks;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

