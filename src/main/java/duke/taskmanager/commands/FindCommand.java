package duke.taskmanager.commands;

import duke.UI;
import duke.taskmanager.Storage;
import duke.taskmanager.TaskList;

import java.util.regex.Pattern;

public class FindCommand extends Command{
    public FindCommand(String userInput, String firstWord) {
        super(userInput, firstWord);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        int DescStartIdx = userInput.indexOf(' ') + 1;
        String description = userInput.substring(DescStartIdx);
        ui.printMatchList(tasks, description);
    }
}
