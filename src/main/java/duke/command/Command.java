package duke.command;

import duke.tasks.TaskList;

public abstract class Command {
    protected TaskList taskList;
    protected String input;

    protected Command(TaskList taskList, String input) {
        this.taskList = taskList;
        this.input = input;
    }

    public abstract ExecutedCommand execute() throws Exception;
}
