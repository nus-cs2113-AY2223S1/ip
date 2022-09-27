package duke.commands;

/**
 * Stores arguments for delete command.
 */
@SuppressWarnings("FieldMayBeFinal")
public class ConsoleCommandDelete extends ConsoleCommand {
    private int taskNumber;

    /**
     * Initializes object with arguments for delete command.
     *
     * @param taskNumber Task number of the task as listed by the function {@link duke.userinterface.ConsoleInterface#executeCommandList(ConsoleCommandList)}.
     */
    public ConsoleCommandDelete(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public int getTaskNumber() {
        return taskNumber;
    }
}
