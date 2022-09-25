package duke.taskmanager.commands;

import duke.UI;
import duke.taskmanager.Storage;
import duke.taskmanager.TaskList;

public abstract class Command {
    /**
     * Execute the <code>Command</code> base on the type.
     *
     * @param tasks   list that stores all the user's current tasks
     * @param ui      contains the formatted outputs
     * @param storage to store the tasks after the programme is closed
     */
    public abstract void execute(TaskList tasks, UI ui, Storage storage);
    public boolean isExit = false;

    protected String userInput;

    protected String firstWord;
    public Command() {
        userInput = null;
    }

    public Command(String userInput,String firstWord) {
        this.userInput = userInput;
        this.firstWord = firstWord;
    }
}
