package duke.command;

import java.util.ArrayList;

import duke.data.task.Task;
import duke.data.tag.TaskList;

/**
 * Represent the parent class Command
 */
public abstract class Command {
    public String commandType;
    protected String message;
    protected TaskList taskList = new TaskList();
    protected ArrayList<Task> target = new ArrayList<>();

    protected Command(String commandType) {
        this.commandType = commandType;
    }

    /**
     * Execution of the command
     */
    public abstract CommandResult execute();

    /**
     * Check if the key is contained in the index of the targeted tasks
     */
    public boolean contains(final int[] array, final int key) {
        for (final int i : array) {
            if (i == key) {
                return true;
            }
        }
        return false;
    }

    /**
     * Set the tasklist that the command will act on
     */
    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Count the target list that the command act on
     */
    protected int targetCount() {
        return (int) target.stream().count();
    }

    /**
     * Print the plural or singular form based on target task count
     */
    protected String printTaskPlural() {
        return (targetCount() == 1 || targetCount() == 0) ? "task" : "tasks";
    }

}
