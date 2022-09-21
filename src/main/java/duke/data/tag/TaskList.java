package duke.data.tag;

import java.util.ArrayList;

import duke.data.task.Task;

/* Represent the TaskList */
public class TaskList{
    
    public static final int MAXIMUM_NUMBER_OF_TASKS = 100;

    public ArrayList<Task> data = new ArrayList<>(MAXIMUM_NUMBER_OF_TASKS); 

    public TaskList() {
    }

}
