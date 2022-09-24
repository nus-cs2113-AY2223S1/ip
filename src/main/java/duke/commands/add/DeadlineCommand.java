package duke.commands.add;

import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class DeadlineCommand extends AddCommand {
    public static final String COMMAND_WORD = "deadline";

    private final String deadlineTaskName;
    private final String deadlineTaskBy;


    public DeadlineCommand(String deadlineTaskName, String deadlineTaskBy) {
        this.deadlineTaskName = deadlineTaskName;
        this.deadlineTaskBy   = deadlineTaskBy;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addDeadlineTask(deadlineTaskName, deadlineTaskBy);
        ui.printTaskAddedMessage(taskList);
        try {
            storage.addTaskToDukeTextFile("D | 0 | " + deadlineTaskName + " | " + deadlineTaskBy);
        } catch (IOException e) {
            ui.showIOExceptionMessage();
        }
    }
}
