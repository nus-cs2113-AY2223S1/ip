package duke.command;

import duke.data.task.Todo;

public class TodoCommand extends Command {
    public static final String COMMAND_NAME = "todo";
    public static final String SYNTAX = "Syntax for todo \n\t>>>todo <description>";
    public static final String MESSAGE_TOP = "Todo added";
    public String description;

    public TodoCommand(String description) {
        super(COMMAND_NAME);
        this.description = description;
    }

    @Override
    public CommandResult execute() {
        Todo added = new Todo(this.description);
        this.taskList.data.add(added);
        target.add(added);
        return new CommandResult(MESSAGE_TOP, target);
    }

}
