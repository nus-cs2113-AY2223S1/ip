package duke.taskmanager.commands;

import duke.UI;
import duke.exceptions.NoBackslashException;
import duke.exceptions.WrongCommandException;
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
