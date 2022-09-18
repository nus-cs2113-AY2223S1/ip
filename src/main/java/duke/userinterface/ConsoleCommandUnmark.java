package duke.userinterface;

/**
 * Stores arguments for unmark command.
 */
public class ConsoleCommandUnmark extends ConsoleCommand {
    private int taskNumber;

    /**
     * Initializes object with arguments for unmark command.
     *
     * @param taskNumber Task number of the task as listed by the function {@link duke.task.TaskManager#printTasks()}.
     */
    public ConsoleCommandUnmark(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public int getTaskNumber() {
        return taskNumber;
    }
}
