package duke.taskmanager.commands;

import duke.UI;
import duke.taskmanager.Storage;
import duke.taskmanager.TaskList;
import duke.taskmanager.tasks.Deadline;
import duke.taskmanager.tasks.Event;
import duke.taskmanager.tasks.Todo;

import static duke.taskmanager.TaskManager.toSave;

public class AddCommand extends Command {
    public AddCommand(String userInput, String firstWord) {
        super(userInput, firstWord);
    }

    /**
     * Add a new task of the user specified type to the <code>TaskList</code>
     * @param tasks   list that stores all the user's current tasks
     * @param ui      contains the formatted outputs
     * @param storage to store the tasks after the programme is closed
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        switch (firstWord) {
        case "todo":
            tasks.add(new Todo(userInput, ' '));
            ui.printTask(tasks.get(tasks.size()-1));
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

    private void addDeadlineOrEvent(TaskList tasks, UI ui, String command, String firstWord) {
        if (command.contains("/")){
            switch (firstWord) {
            case "deadline":
                tasks.add(new Deadline(command, '/'));
                break;
            case "event":
                tasks.add(new Event(command, '/'));
                break;
            }
            ui.printTask(tasks.get(tasks.size()-1));
        } else {
            ui.printNoBackslashException();
        }
    }
}
