package duke.taskmanager.commands;

import duke.UI;
import duke.exceptions.TaskOutOfBoundsException;
import duke.taskmanager.Storage;
import duke.taskmanager.TaskList;

/**
 * Mark a specified <code>task</code>
 */
public class MarkCommand extends Command {
    public MarkCommand(String userInput, String firstWord) {
        super(userInput, firstWord);
    }

    /**
     * Mark or unmark a <code>task</code> based on the user input
     *
     * @param tasks   stores all the user's current tasks
     * @param ui      contains the formatted outputs
     * @param storage stores the tasks after the programme closes
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        boolean isMarked = false, isValid = true;
        switch (firstWord) {
        case "mark":
            isMarked = true;
            break;
        case "unmark":
            break;
        default:
            isValid = false;
        }
        if (isValid) {
            mark(tasks,ui,userInput,isMarked);
        }
    }

    /**
     * Mark whether the <code>task</code> specified by user is done. Checks if the user input an existing task.
     *
     * @param tasks     stores all the user's current tasks
     * @param ui        contains the formatted outputs
     * @param userInput the <code>String</code> that the user input
     * @param done      whether the task is done
     */
    private void mark(TaskList tasks, UI ui, String userInput, boolean done) {
        try {
            int startIdx = userInput.substring(0, userInput.indexOf(' ') + 1).length();
            int pos = Integer.parseInt(userInput.substring(startIdx)) - 1;
            if (pos > tasks.size() || pos < 0) {
                throw new TaskOutOfBoundsException();
            }
            ui.printMark(tasks.get(pos), done);
        } catch (NumberFormatException e) {
            ui.printNumberFormatException();
        } catch (TaskOutOfBoundsException e) {
            ui.printTaskOutOfBoundsException();
        }
    }
}
