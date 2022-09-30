package duke.taskmanager.commands;

import duke.UI;
import duke.taskmanager.Storage;
import duke.taskmanager.TaskList;
import duke.taskmanager.tasks.Deadline;
import duke.taskmanager.tasks.Event;
import duke.taskmanager.tasks.Todo;

import static duke.taskmanager.TaskManager.toSave;

/**
 * Add a new <code>task</code> to the <code>TaskList</code>.
 */
public class AddCommand extends Command {
    public AddCommand(String userInput, String firstWord) {
        super(userInput, firstWord);
    }

    /**
     * Add a new <code>task</code> of the user specified type to the <code>TaskList</code>.
     * Checks for incorrect input.
     *
     * @param tasks   stores all the user's current tasks
     * @param ui      contains the formatted outputs
     * @param storage stores the tasks after the programme closes
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        switch (firstWord) {
        case "todo":
            tasks.add(new Todo(userInput, ' '));
            ui.printTask(tasks);
            toSave.append(userInput).append(System.lineSeparator());
            break;
        case "deadline":
            //Fallthrough
        case "event":
            addDeadlineOrEvent(tasks,ui,userInput,firstWord);
            toSave.append(userInput).append(System.lineSeparator());
            break;
        default:
            ui.printWrongCommandException();
        }
    }

    /**
     * Adds a <code>deadline</code> or <code>event</code> task. If the date and time specified
     * by the user is too short(not valid), append a string such that an IndexOutOfBoundsException
     * will not be thrown when searching for valid date and time.
     *
     * @param tasks     stores all the user's current tasks
     * @param ui        contains the formatted outputs
     * @param command   the <code>command</code> that the user input
     * @param firstWord first word of <code>command</code> input
     */
    private void addDeadlineOrEvent(TaskList tasks, UI ui, String command, String firstWord) {
        if (command.contains("/")){
            int dateTimeLength = command.substring(command.indexOf('/')).length();
            int minLength = "/at _".length();
            if (dateTimeLength < minLength) {
                command += "error";
            }
            switch (firstWord) {
            case "deadline":
                tasks.add(new Deadline(command, '/'));
                break;
            case "event":
                tasks.add(new Event(command, '/'));
                break;
            }
            ui.printTask(tasks);
        } else {
            addDeadlineOrEvent(tasks, ui, command + "/at error", firstWord);
        }
    }
}
