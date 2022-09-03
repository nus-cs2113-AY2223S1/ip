package duke;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(List list, UI ui) {
        list.unmarkDone(index);
        ui.confirmUnmark(list.findTask(index));
    }
}
