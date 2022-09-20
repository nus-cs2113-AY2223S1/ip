package duke.command;

import duke.data.tag.TaskList;
import duke.data.task.Task;

public class Command {
    public String commandType;
    protected TaskList<Task> taskList;

    protected Command(String commandType) {
        this.commandType = commandType;
    }

    public CommandResult execute() {
        throw new UnsupportedOperationException("Method to be implemented in child classes");
    }

    public boolean contains(final int[] array, final int key) {
        for (final int i : array) {
            if (i == key) {
                return true;
            }
        }
        return false;
    }

    public void setTaskList(TaskList<Task> data){
        this.taskList = data;
    }
}
