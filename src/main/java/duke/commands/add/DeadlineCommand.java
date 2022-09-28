package duke.commands.add;

import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Adds a deadline task to the list.
 */
public class DeadlineCommand extends AddCommand {
    public static final String COMMAND_WORD = "deadline";

    private final String deadlineTaskName;
    private final String deadlineByDate;
    private final String deadlineByTime;

    /**
     * Constructs constructor for Deadline command which stores the deadline task name, date and time.
     *
     * @param deadlineTaskName Name of deadline task.
     * @param deadlineByDate Date of deadline task.
     * @param deadlineByTime Time of deadline task.
     */
    public DeadlineCommand(String deadlineTaskName, String deadlineByDate, String deadlineByTime) {
        this.deadlineTaskName = deadlineTaskName;
        this.deadlineByDate = deadlineByDate;
        this.deadlineByTime = deadlineByTime;
    }

    /**
     * Adds a deadline task to the list, prints confirmation message and updates duke.txt if successful.
     * Otherwise, prints exception message if external error occurs.
     *
     * @param taskList Used to access taskList information.
     * @param ui Used to print confirmation or exception messages.
     * @param storage Used to update task information in duke.txt.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        LocalDate deadlineDate = LocalDate.parse(deadlineByDate);
        String date = deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        taskList.addDeadlineTask(deadlineTaskName, deadlineDate, deadlineByTime);
        ui.printTaskAddedMessage(taskList);
        String addedTaskDescription = "D | 0 | " + deadlineTaskName + " | " + date;
        try {
            if (!deadlineByTime.isEmpty()) {
                addedTaskDescription += " | " + deadlineByTime;
            }
            storage.addTaskToDukeTextFile(addedTaskDescription);
        } catch (IOException e) {
            ui.showIOExceptionMessage();
        }
    }
}
