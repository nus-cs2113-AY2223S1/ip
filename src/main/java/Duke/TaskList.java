package Duke;

import java.util.ArrayList;

public class TaskList {
    public int noOfTasks;
    TaskList() {
        noOfTasks = 0;
    }
    ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Add event type task
     * @param description
     * @param at
     * @param mark
     */
    public void addEvent(String description, String at, boolean mark) {
        tasks.add(new Event(description, at, mark));
        noOfTasks++;
    }

    /**
     * Add deadline type task
     * @param description
     * @param by
     * @param mark
     */
    public void addDeadline(String description, String by, boolean mark) {
        tasks.add(new Deadline(description, by, mark));
        noOfTasks++;
    }

    /**
     * Add todo type task
     * @param description
     * @param mark
     */
    public void addTodo(String description, boolean mark) {
        tasks.add(new Todo(description, mark));
        noOfTasks++;
    }

    /**
     * Mark the task of given taskNo
     * @param taskNo
     * @param mark true to mark, false to unmark
     */
    public void mark(int taskNo, boolean mark) {
        tasks.get(taskNo).mark(mark);
    }

    /**
     * Delete the task of given taskNo
     * @param taskNo
     */
    public void delete(int taskNo) {
        tasks.remove(taskNo);
        noOfTasks--;
    }

    /**
     * Find all tasks containing the String search
     * @param taskNo
     * @param search
     * @return
     */
    public boolean findTask(int taskNo, String search) {
        if(tasks.get(taskNo).getDescription().contains(search)) {
            return true;
        }
        return false;
    }
}
