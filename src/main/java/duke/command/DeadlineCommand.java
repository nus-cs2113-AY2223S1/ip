package duke.command;

import duke.data.TaskList;
import java.util.ArrayList;

import duke.data.task.*;

public class DeadlineCommand extends Command {
    public DeadlineCommand(String description, String date) {
        super(COMMAND_NAME);
        this.description = description;
        this.date = date;
    }

    /*Variables*/
    public static final String COMMAND_NAME = "deadline";
    public static final String SYNTAX = "Syntax for deadline\n\t>>>deadline <task> / <date of deadline>";
    public static final String MESSAGE_TOP = "Deadline Added";


    public String description;
    public String date;
    public ArrayList<Task> target = new ArrayList<Task>();

    @Override
    public CommandResult execute() {
        Task added = new Deadline(description, date);
        TaskList.list.add(added);
        target.add(added);
        return new CommandResult(MESSAGE_TOP, target, TaskList.getTotalMessage());
    }

}
