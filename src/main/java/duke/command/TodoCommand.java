package duke.command;

import duke.data.TaskList;

import java.util.ArrayList;

import duke.data.task.*;

public class TodoCommand extends Command {
    public TodoCommand(String description) {
        super(COMMAND_NAME);
        this.description = description;
    }

    /*Variables*/
    public static final String COMMAND_NAME = "todo";
    public static final String SYNTAX = "Syntax for todo \n\t>>>todo <task>";
    public static final String MESSAGE_TOP = "Todo Added";


    public String description;
    public ArrayList<Task> target = new ArrayList<Task>();

    /*Non-static */
    @Override
    public CommandResult execute() {
        Task added = new Todo(this.description);
        TaskList.list.add(added);
        target.add(added);
        CommandResult result = new CommandResult(MESSAGE_TOP, target, TaskList.getTotalMessage());
        return result;
    }

}
