package duke.command;

import duke.task.List;
import duke.ui.UI;

public class ExitCommand extends Command {
    @Override
    public void execute(List list, UI ui) {
        ui.bye();
    }
}
