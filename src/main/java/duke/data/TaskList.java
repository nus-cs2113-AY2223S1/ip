package duke.data;

import duke.task.Task;

import java.util.ArrayList;


public class TaskList {
    public TaskList() {
    }

    public final int MAXIMUM_NUMBER_OF_TASKS = 100;
    public ArrayList<Task> list = new ArrayList<>(MAXIMUM_NUMBER_OF_TASKS);

}
