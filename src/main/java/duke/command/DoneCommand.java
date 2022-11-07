package duke.command;

import duke.common.Message;
import duke.exception.EmptyFieldException;
import duke.exception.EmptyTaskException;
import duke.exception.InvalidFieldException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.DukeUI;

public class DoneCommand extends Command {

    private static final String FAILURE_MESSAGE_SET_DONE = Message.TEXT_INDENTATION
            + "Please specify a task with:\n"
            + Message.TEXT_INDENTATION + "\"" + Message.COMMAND_DONE + "\"\n";

    public DoneCommand(String userInput) {
        this.userInput = userInput;
        this.isExit = false;
    }

    @Override
    public void execute(DukeUI ui, Storage storage, TaskList tasks) {
        try {
            Task curTask = Parser.extractDoneInput(tasks, userInput);
            curTask.setDone();

            String doneMessage = Message.TEXT_INDENTATION + "Nice! I've marked this task as done:\n"
                    + Message.TEXT_INDENTATION + "  " + curTask.getTaskInfo() + "\n";
            ui.printMessage(doneMessage);
            storage.updateTasksToFile(ui, tasks);
        } catch (EmptyFieldException | InvalidFieldException e) {
            ui.printMessage(FAILURE_MESSAGE_SET_DONE);
        } catch (EmptyTaskException e) {
            ui.printEmptyTaskMessage();
        }
    }
}
