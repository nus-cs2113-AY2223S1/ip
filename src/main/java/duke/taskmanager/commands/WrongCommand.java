package duke.taskmanager.commands;

import duke.UI;
import duke.taskmanager.Storage;
import duke.taskmanager.TaskList;

public class WrongCommand extends Command {
    public WrongCommand(String userInput, String firstWord) {
        super(userInput, firstWord);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {

    }
}
