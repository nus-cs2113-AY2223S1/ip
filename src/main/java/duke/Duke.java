package duke;

import duke.command.Command;
import duke.data.TaskList;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.exception.DukeException;
import duke.ui.Ui;

/**
 * <code>Duke</code> is the main class of the application containing the main method.
 */
public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    /**
     * Constructor of <code>Duke</code>.
     * Initialise the program by creating a new storage, user interface and task list classes.
     *
     * @param filePath The relative file path used to store the list of tasks
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.initialize());
        } catch (DukeException exception) {
            ui.displayErrorMessage(exception.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * To execute the program in an infinite loop to received user's input,
     * parsing the user input into a valid command,
     * and execute the command until the user chooses to exit the program.
     * <pr>
     * Display the error message if an application exception is caught.
     */
    public void run() {
        ui.displayGreetingMessage();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException exception) {
                ui.displayErrorMessage(exception.getMessage());
            }
        }
    }

    /**
     * Main method of the application.
     *
     * @param args Unused
     */
    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }
}
