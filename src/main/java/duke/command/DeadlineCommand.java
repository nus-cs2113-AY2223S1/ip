package duke.command;

import duke.data.task.Deadline;

public class DeadlineCommand extends Command {
    public static final String COMMAND_NAME = "deadline";
    public static final String SYNTAX = "Syntax for deadline\n\t>>>deadline <description> / <date (can be in yyyy-mm-dd)>";
    public static final String MESSAGE = "Deadline added";
    public String description;
    public String date;

    public DeadlineCommand(String description, String date) {
        super(COMMAND_NAME);
        this.description = description;
        this.date = date;
    }


    @Override
    public CommandResult execute() {
        Deadline added = new Deadline(description, date);
        this.taskList.data.add(added);
        target.add(added);
        return new CommandResult(MESSAGE, target);
    }

}
