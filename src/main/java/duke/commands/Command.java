package duke.commands;

import duke.Storage;
import duke.TaskList;

public class Command {
    protected String commandWord;

    public Command(String commandWord) {
        this.commandWord = commandWord;
    }

    public void execute(TaskList tasks, Storage storage) {}

    public boolean checkExit() {
        return false;
    }

}
