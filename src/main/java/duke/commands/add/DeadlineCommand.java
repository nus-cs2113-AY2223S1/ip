package duke.commands.add;

import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineCommand extends AddCommand {
    public static final String COMMAND_WORD = "deadline";

    private final String deadlineTaskName;
    private final String deadlineByDate;
    private final String deadlineByTime;


    public DeadlineCommand(String deadlineTaskName, String deadlineByDate, String deadlineByTime) {
        this.deadlineTaskName = deadlineTaskName;
        this.deadlineByDate = deadlineByDate;
        this.deadlineByTime = deadlineByTime;
    }

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
