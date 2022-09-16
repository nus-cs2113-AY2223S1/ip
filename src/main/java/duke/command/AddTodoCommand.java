package duke.command;

import duke.tasks.TaskList;

public class AddTodoCommand extends Command {
    public AddTodoCommand(TaskList taskList, String input) {
        super(taskList, input);
    }

    @Override
    public ExecutedCommand execute() throws Exception {
        String executionMessage = TaskList.addTodo(input);

        return new ExecutedCommand(executionMessage, taskList, true);
    }
}
