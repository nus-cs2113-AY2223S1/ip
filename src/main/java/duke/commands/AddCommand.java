package duke.commands;

/**
 * Parent class of commands that are the more detailed addition of tasks into task list.
 */
public class AddCommand extends Command {

    protected String taskLine;

    public AddCommand(String commandWord, String taskline) {
        super(commandWord);
        this.taskLine = taskline;
    }
}
