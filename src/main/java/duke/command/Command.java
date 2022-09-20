package duke.command;

import duke.data.tag.TaskList;
import java.util.ArrayList;
import duke.data.task.Task;

public abstract class Command {
    public String commandType;
    protected String message;
    protected TaskList taskList = new TaskList();
    protected ArrayList<Task> target = new ArrayList<>();

    protected Command(String commandType) {
        this.commandType = commandType;
    }

    public abstract CommandResult execute();

    public boolean contains(final int[] array, final int key) {
        for (final int i : array) {
            if (i == key) {
                return true;
            }
        }
        return false;
    }


    public void setTaskList(TaskList taskList){
        this.taskList = taskList;
    }

    protected int targetCount(){
        return (int) target.stream().count();
    }
    protected String printTaskPlural(){
        return (targetCount() == 1 || targetCount() == 0)? "task" : "tasks";
    }

}
