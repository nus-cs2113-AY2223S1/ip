package duke.command;

import duke.tasks.TaskList;

public class FindCommand extends Command{
    public FindCommand(TaskList taskList, String input){
        super(taskList,input);
    }

    @Override
    public ExecutedCommand execute() throws Exception {
        String executionMessage = TaskList.findTask(input);
        return new ExecutedCommand(executionMessage, taskList, false);
    }
}
