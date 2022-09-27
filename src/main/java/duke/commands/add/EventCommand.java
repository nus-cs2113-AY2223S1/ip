package duke.commands.add;

import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EventCommand extends AddCommand {
    public static final String COMMAND_WORD = "event";

    private final String eventTaskName;
    private final String eventTaskAtDate;
    private final String eventTaskAtTime;

    public EventCommand(String eventTaskName, String eventTaskAtDate, String eventTaskAtTime) {
        this.eventTaskName   = eventTaskName;
        this.eventTaskAtDate = eventTaskAtDate;
        this.eventTaskAtTime = eventTaskAtTime;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        LocalDate eventDate = LocalDate.parse(eventTaskAtDate);
        String date = eventDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        taskList.addEventTask(eventTaskName, eventDate, eventTaskAtTime);
        ui.printTaskAddedMessage(taskList);
        String addedTaskDescription = "E | 0 | " + eventTaskName + " | " + date;
        try {
            if (!eventTaskAtTime.isEmpty()) {
                addedTaskDescription += " | " + eventTaskAtTime;
            }
            storage.addTaskToDukeTextFile(addedTaskDescription);
        } catch (IOException e) {
            ui.showIOExceptionMessage();
        }
    }
}
