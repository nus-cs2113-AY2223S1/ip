package duke.userinterface;

public class ConsoleCommandMark extends ConsoleCommand {
    private int taskNumber;

    public ConsoleCommandMark(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public int getTaskNumber() {
        return taskNumber;
    }
}
