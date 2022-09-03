package duke;

public class ExitCommand extends Command {
    @Override
    public void execute(List list, UI ui) {
        ui.bye();
    }
}
