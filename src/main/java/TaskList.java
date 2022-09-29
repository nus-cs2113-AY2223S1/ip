import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Add item to list of tasks
     *
     * @param item tasks in the list
     */
    private void add(Task item) {
        tasks.add(item);
        System.out.format("added: %s%n%d items on list%n", item, tasks.size());
    }

    /**
     * List all items in storage
     */
    private void listAll() {
        System.out.format("There are %d tasks on the list now:%n", tasks.size());
        int i = 1;
        for (Task task : tasks) {
            System.out.format("%d.%s%n", i++, task);
        }
    }

    /**
     * mark a task as done
     *
     * @param i index of task
     */
    private void mark(int i) {
        try {
            tasks.get(i).setIsDone(true);
            System.out.format("Task %d is marked as done~%n%s%n", i + 1, tasks.get(i));
        } catch (IndexOutOfBoundsException e) {
            System.out.format("Exception: Unable to mark task %n" +
                    "There are only %d tasks now~%n", tasks.size());
        }
    }

    /**
     * mark a task as not done
     *
     * @param i index of task
     */
    private void unmark(int i) {
        try {
            tasks.get(i).setIsDone(false);
            System.out.format("Task %d is unmarked as not done~%n%s%n", i + 1, tasks.get(i));
        } catch (IndexOutOfBoundsException e) {
            System.out.format("Exception: Unable to mark task %n" +
                    "There are only %d tasks now~%n", tasks.size());
        }
    }

    /**
     * Delete a task from list
     *
     * @param i index of task
     */
    private void deleteTask(int i) {
        try {
            String removedTask = tasks.get(i).toString();
            tasks.remove(i);
            System.out.format("Task %d: %s is removed~%n", i + 1, removedTask);
            this.listAll();
        } catch (IndexOutOfBoundsException e) {
            System.out.format("Exception: Unable to delete task %n" +
                    "There are only %d tasks now~%n", tasks.size());
        }
    }

    
    /**
     * execute a command on the task list
     *
     * @param command the command to execute
     * @return whether the software will quit
     */
    public Boolean execute(Command command) {
        CommandType commandType = command.getCommandType();
        int index = command.getIndex();
        switch (commandType) {
        case TASK:
            this.add(command.getTask());
            break;
        case LIST:
            this.listAll();
            break;
        case MARK:
            this.mark(index);
            break;
        case UNMARK:
            this.unmark(index);
            break;
        case DELETE:
            this.deleteTask(index);

        case NULL:
        case ERROR:
            break;
        case BYE:
            return true;
        }
        return false;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
