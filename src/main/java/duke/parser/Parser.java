package duke.parser;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import java.io.IOException;

/**
 * Represents an entity to handle and interpret user commands in order to execute the corresponding methods to reflect the program's intended behaviour.
 */
public class Parser {
    private Ui ui;
    private boolean isProgramFinished;

    public Parser(Ui ui) {
        this.ui = ui;
    }

    /**
     * Resolves the user command to interpret its type and executes the relevant operations to modify the user's list of tasks according to the type.
     *
     * @param command The full line of input that the user provided to the program.
     * @param taskList The task manager handling the user's current list of tasks.
     */
    public void handleInput(String command, TaskList taskList) {
        String[] text = command.split(" ");
        String type = text[0];
        try {
            switch (type) {
            case "mark":
                taskList.handleMarkAsDone(command);
                break;
            case "unmark":
                taskList.handleMarkAsUndone(command);
                break;
            case "delete":
                taskList.deleteTask(type, command);
                break;
            case "find":
                taskList.findTasks(text);
                break;
            default:
                taskList.addTask(type, command);
                break;
            }
        } catch (DukeException e) {
            e.printErrorMessage();
        }
        try {
            Storage.saveFile(taskList.getTasks());
        } catch (IOException e) {
            ui.showLoadingError(e.getMessage());
        }
    }

    /**
     * Detects if the user has indicated to terminate the program or print their current list of tasks.
     * If other types of user commands were given, the user command is resolved further and executed thereafter.
     *
     * @param command The full line of input that the user provided to the program.
     * @param taskList The task manager handling the user's current list of tasks.
     */
    public void parse(String command, TaskList taskList) {
        if(command.equals("bye")) {
            this.isProgramFinished = true;
        } else if(command.equals("list")) {
            ui.printTasks(taskList);
        } else {
            handleInput(command, taskList);
        }
    }

    public boolean getTerminationStatus() {
        return this.isProgramFinished;
    }
}
