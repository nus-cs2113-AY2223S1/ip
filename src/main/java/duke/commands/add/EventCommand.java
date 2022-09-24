package duke.commands.add;

import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class EventCommand extends AddCommand {
    public static final String COMMAND_WORD = "event";

    private final String eventTaskName;
    private final String eventTaskAt;

    public EventCommand(String eventTaskName, String eventTaskAt) {
        this.eventTaskName = eventTaskName;
        this.eventTaskAt   = eventTaskAt;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addEventTask(eventTaskName, eventTaskAt);
        ui.printTaskAddedMessage(taskList);
        try {
            storage.addTaskToDukeTextFile("E | 0 | " + eventTaskName + " | " + eventTaskAt);
        } catch (IOException e) {
            ui.showIOExceptionMessage();
        }
    }
}
