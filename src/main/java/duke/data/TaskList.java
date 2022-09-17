package duke.data;

import java.util.ArrayList;

import duke.data.task.Task;


public class TaskList {
    public TaskList() {
    }
    public static final int MAXIMUM_NUMBER_OF_TASKS = 100;
    public static ArrayList<Task> list = new ArrayList<Task>(MAXIMUM_NUMBER_OF_TASKS);

}
