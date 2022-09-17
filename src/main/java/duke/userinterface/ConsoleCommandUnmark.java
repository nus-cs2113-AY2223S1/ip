package duke.userinterface;

public class ConsoleCommandUnmark extends ConsoleCommand {
    private int taskNumber;

    public ConsoleCommandUnmark(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public int getTaskNumber() {
        return taskNumber;
    }
}
