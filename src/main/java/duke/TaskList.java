package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {

    public static final String DIVIDER = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    public static final String SPACER = "  ";
    public ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public Task findTask(int index) {
        return taskList.get(index);
    }

    public void addTask(Task toAdd) {
        taskList.add(toAdd);
    }

    public void deleteTask(int index) {
        taskList.remove(index);
    }

    public int getSize() {
        return taskList.size();
    }

    public void markDone(int index) {
        taskList.get(index).markDone();
    }

    public void markUndone(int index) {
        taskList.get(index).markUndone();
    }

    public void listTask() {
        int counter = 1;
        System.out.println("come uncle show you your tasks");
        for (Task task : taskList) {
            if (task != null) {
                String toBePrinted = task.toString();
                System.out.println(counter + ". " + toBePrinted);
                counter++;
            }
        }
        System.out.println(DIVIDER);
    }
}
