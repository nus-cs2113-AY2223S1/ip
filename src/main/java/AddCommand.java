import com.sun.source.util.TaskListener;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(List list, UI ui) {
        list.add(task);
        ui.confirmAdd(task, list);
    }
}