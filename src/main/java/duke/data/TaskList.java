package duke.data;

import java.util.ArrayList;

import duke.data.task.Task;


public class TaskList {
    public TaskList() {
    }
    public static final int MAXIMUM_NUMBER_OF_TASKS = 100;
    public static ArrayList<Task> list = new ArrayList<Task>(MAXIMUM_NUMBER_OF_TASKS);
    public static String getTotalMessage(){
        int count = TaskList.list.size();
        return "You have " + count + printTaskPlural(count);

    }
    public static String getMarkMessage(){
        int count = (int) TaskList.list.stream().filter(i -> i.isDone).count();
        return "You have " + count + " marked" + printTaskPlural(count);
    }
    public static String getUnmarkMessage(){
        int count = (int) TaskList.list.stream().filter(i -> !i.isDone).count();
        return "You have " + count + " unmarked" + printTaskPlural(count);
    }

    private static String  printTaskPlural(int number){
        return ((number == 1)? " task." : " tasks.");
    }
}
