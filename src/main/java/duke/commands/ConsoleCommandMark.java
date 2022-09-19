package duke.commands;

/**
 * Stores arguments for mark command.
 */
@SuppressWarnings("FieldMayBeFinal")
public class ConsoleCommandMark extends ConsoleCommand {
    private int taskNumber;

    /**
     * Initializes object with arguments for mark command.
     *
     * @param taskNumber Task number of the task as listed by the function {@link duke.data.task.TaskManager#printTasks()}.
     */
    public ConsoleCommandMark(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public int getTaskNumber() {
        return taskNumber;
    }
}
