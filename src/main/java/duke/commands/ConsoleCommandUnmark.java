package duke.commands;

/**
 * Stores arguments for unmark command.
 */
@SuppressWarnings("FieldMayBeFinal")
public class ConsoleCommandUnmark extends ConsoleCommand {
    private int taskNumber;

    /**
     * Initializes object with arguments for unmark command.
     *
     * @param taskNumber Task number of the task as listed by the function {@link duke.data.task.TaskManager#printTasks()}.
     */
    public ConsoleCommandUnmark(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public int getTaskNumber() {
        return taskNumber;
    }
}
