package duke.command;

import duke.task.List;
import duke.ui.UI;

public class ListCommand extends Command {
    @Override
    public void execute(List list, UI ui) {
        ui.printList(list);
    }
}
