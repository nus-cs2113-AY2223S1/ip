package duke.command;

import duke.tasks.TaskList;

public class AddTodoCommand extends Command {
    public AddTodoCommand(TaskList taskList, String input) {
        super(taskList, input);
    }

    @Override
    public ExecutedCommand execute() throws Exception {
        String executionMessage = TaskList.addTodo2(input);

        return new ExecutedCommand(executionMessage);
    }
}
