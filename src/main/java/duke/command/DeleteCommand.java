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

public class DeleteCommand extends Command {

    private static final String FAILURE_MESSAGE_DELETE_TASK = Message.TEXT_INDENTATION
            + "Please provide a valid task number:\n"
            + Message.TEXT_INDENTATION + "\"" + Message.COMMAND_DELETE + "\"\n";

    public DeleteCommand(String userInput) {
        this.userInput = userInput;
        this.isExit = false;
    }

    @Override
    public void execute(DukeUI ui, Storage storage, TaskList tasks) {
        try {
            Task deletedTask = Parser.extractDeleteInput(tasks, userInput);
            String deletedTaskInfo = deletedTask.getTaskInfo();

            tasks.deleteTask(deletedTask);

            String deleteMessage = Message.TEXT_INDENTATION + "Noted. I've removed this task:\n"
                    + Message.TEXT_INDENTATION + "  " + deletedTaskInfo + "\n"
                    + Message.TEXT_INDENTATION + "You currently have ("
                    + tasks.getTaskSize() + ") tasks in your list\n";
            ui.printMessage(deleteMessage);
            storage.updateTasksToFile(ui, tasks);
        } catch (EmptyFieldException | InvalidFieldException e) {
            ui.printMessage(FAILURE_MESSAGE_DELETE_TASK);
        } catch (EmptyTaskException e) {
            ui.printEmptyTaskMessage();
        }
    }
}
