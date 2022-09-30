package duke.commands;

import duke.common.ErrorMessages;
import duke.data.TaskList;
import duke.data.task.Task;
import duke.storage.Storage;
import duke.ui.TextUi;

/**
 * Represents an event command object that will execute the operations for Event command.
 */
public class EventCommand extends AddCommand {
    public static final String COMMAND = "event";
    private static final String DELIMITER = " /at ";

    /**
     * Initialises the variables of the EventCommand class.
     *
     * @param description A string that represents the title and event time of the task.
     * @param isDone A boolean that indicates whether the task is done or undone.
     */
    public EventCommand(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Executes the operations related to the command.
     *
     * @param ui An instance of the TextUi class.
     * @param tasks An instance of the TaskList class.
     * @param storage An instance of the Storage class.
     */
    @Override
    public void execute(TextUi ui, TaskList tasks, Storage storage) {
        try {
            String[] descriptionSplits = description.split(DELIMITER);
            // Adds the event task into the task list
            Task task = tasks.addEvent(descriptionSplits[0], descriptionSplits[1], isDone);
            // Shows information for the task that has been added into the program
            ui.showAddTaskInfo(task.getTaskDetails(), tasks.getTaskCount());
            // Writes each task from the updated task list into the file
            writeToFile(ui, tasks, storage);
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showCustomText(ErrorMessages.MESSAGE_ERROR_INVALID_EVENT_FORMAT);
        }
    }
}
