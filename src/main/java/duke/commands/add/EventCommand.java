package duke.commands.add;

import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Adds an event task to the list.
 */
public class EventCommand extends AddCommand {
    public static final String COMMAND_WORD = "event";

    private final String eventTaskName;
    private final String eventTaskAtDate;
    private final String eventTaskAtTime;

    /**
     * Constructs constructor for Event command which stores the event task name, date and time.
     *
     * @param eventTaskName Name of event task.
     * @param eventTaskAtDate Date of event task.
     * @param eventTaskAtTime Time of event task.
     */
    public EventCommand(String eventTaskName, String eventTaskAtDate, String eventTaskAtTime) {
        this.eventTaskName   = eventTaskName;
        this.eventTaskAtDate = eventTaskAtDate;
        this.eventTaskAtTime = eventTaskAtTime;
    }

    /**
     * Adds an event task to the list, prints confirmation message and updates duke.txt if successful.
     * Otherwise, prints exception message if external error occurs.
     *
     * @param taskList Used to access taskList information.
     * @param ui Used to print confirmation or exception messages.
     * @param storage Used to update task information in duke.txt.
     */
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
