package duke.userinterface;

public class ConsoleCommandDelete extends ConsoleCommand {
    private int taskNumber;

    public ConsoleCommandDelete(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public int getTaskNumber() {
        return taskNumber;
    }
}
