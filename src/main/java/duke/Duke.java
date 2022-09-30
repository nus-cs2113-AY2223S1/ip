package duke;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.common.ErrorMessages;
import duke.common.InfoMessages;
import duke.data.TaskList;
import duke.data.exception.InvalidCommandException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.TextUi;

import java.io.IOException;
import java.util.Objects;

/**
 * Duke Doraemon is a personal chat robot cat assistant that helps
 * a person such as Nobita to keep track of his todos, events and deadlines.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private TextUi ui;

    /**
     * Initialises new storage, tasks and user interface classes
     * in the current instance of Duke.
     */
    public Duke() {
        storage = new Storage();
        tasks = new TaskList();
        ui = new TextUi();

        try {
            // Initialises tasks with saved input from an existing file
            tasks = storage.readFromFile();
            ui.showCustomText(InfoMessages.MESSAGE_INFO_FILE_READ);
        } catch (IOException e) {
            ui.showCustomText(ErrorMessages.MESSAGE_ERROR_FILE_IO);
        }
    }

    /**
     * Runs Duke via commands entered by the user.
     */
    public void run() {
        ui.showWelcomeText();

        String input;
        do {
            input = ui.getUserInput();
            try {
                // Parses the user input and creates a corresponding command object
                Command command = Parser.parseCommand(input);
                // Executes the functions of the command
                command.execute(ui, tasks, storage);
            } catch (InvalidCommandException e) {
                ui.showCustomText(ErrorMessages.MESSAGE_ERROR_INVALID_COMMAND);
            }
            // Loops and continue to requests for user input if user has not exited
        } while (!Objects.equals(input, ByeCommand.COMMAND));
    }

    /**
     * Launches a runnable instance of Duke.
     *
     * @param args A list of unused arguments at runtime.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}