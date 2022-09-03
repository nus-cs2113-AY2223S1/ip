package duke.command;

import duke.task.List;
import duke.ui.UI;

public abstract class Command {
    public abstract void execute(List list, UI ui);
}
