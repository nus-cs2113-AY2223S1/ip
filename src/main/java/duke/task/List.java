package duke.task;

import duke.task.Task;
import java.util.ArrayList;

public class List {
    //I will use ArrayList because the maximum size is only 100
    //In the future, it would be easier to implement DELETE
    private ArrayList<Task> list;

    public List() {
        this.list = new ArrayList<>();
    }

    public Task findTask(int index) {
        return list.get(index - 1);
    }

    public int getSize() {
        return list.size();
    }

    public void add(Task task) {
        list.add(task);
    }

    public void markDone(int index) {
        findTask(index).setDone(true);
    }

    public void unmarkDone(int index) {
        findTask(index).setDone(false);
    }

    @Override
    public String toString() {
        StringBuilder listString = new StringBuilder((list.size() == 0 ? "There is nothing in your list right now" : "Here are " + list.size() + " tasks in your list:"));
        int index = 1;
        for (Task task : list) {
            listString.append('\n').append("   ").append(index++).append(". ").append(task);
        }
        return String.valueOf(listString);
    }
}
