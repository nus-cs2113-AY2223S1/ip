public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(List list, UI ui) {
        list.markDone(index);
        ui.confirmMark(list.findTask(index));
    }
}
