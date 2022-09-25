package duke.taskmanager.commands;

import duke.UI;
import duke.taskmanager.Storage;
import duke.taskmanager.TaskList;

public abstract class Command {
    public abstract void execute(TaskList tasks, UI ui, Storage storage);
    public boolean isExit = false;

    public String userInput;

    public String firstWord;
    public Command() {
        userInput = null;
    }

    public Command(String userInput,String firstWord) {
        this.userInput = userInput;
        this.firstWord = firstWord;
    }
}
