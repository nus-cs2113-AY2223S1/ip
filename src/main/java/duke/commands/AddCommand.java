package duke.commands;

public class AddCommand extends Command {

    protected String taskLine;

    public AddCommand(String commandWord, String taskline) {
        super(commandWord);
        this.taskLine = taskline;
    }

}
