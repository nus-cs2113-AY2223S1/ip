package duke.userinterface;

/**
 * Stores arguments for delete command.
 */
public class ConsoleCommandDelete extends ConsoleCommand {
    private int taskNumber;

    /**
     * Initializes object with arguments for delete command.
     *
     * @param taskNumber Task number of the task as listed by the function {@link duke.task.TaskManager#printTasks()}.
     */
    public ConsoleCommandDelete(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public int getTaskNumber() {
        return taskNumber;
    }
}
