package duke.command;

import java.util.ArrayList;
import duke.data.task.*;

public class TodoCommand extends Command {
    public TodoCommand(String description) {
        super(COMMAND_NAME);
        this.description = description;
    }

    public static final String COMMAND_NAME = "todo";
    public static final String SYNTAX = "Syntax for todo \n\t>>>todo <task>";
    public static final String MESSAGE_TOP = "Todo added";


    public String description;
    public ArrayList<Task> target = new ArrayList<Task>();

    @Override
    public CommandResult execute() {
        Todo added = new Todo(this.description);
        this.taskList.data.add(added);
        target.add(added);
        return new CommandResult(MESSAGE_TOP, target);
    }

}
