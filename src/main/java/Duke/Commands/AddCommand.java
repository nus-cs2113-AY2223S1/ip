package Duke.Commands;

/**
 * Represents all types of commands that involves the addition of task
 */
public abstract class AddCommand extends Command {
    protected String[] taskDescription;

    public AddCommand(String[] taskDescription) {
        this.taskDescription = taskDescription;
    }
}
