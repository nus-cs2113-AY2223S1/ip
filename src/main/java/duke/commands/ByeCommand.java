package duke.commands;

import duke.common.InfoMessages;
import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.TextUi;

public class ByeCommand extends Command {
    public static final String COMMAND = "bye";

    public ByeCommand() {}

    @Override
    public void execute(TextUi ui, TaskList tasks, Storage storage) {
        ui.showCustomText(InfoMessages.MESSAGE_INFO_GOODBYE);
    }
}
