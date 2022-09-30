package duke;


import duke.data.Storage;
import duke.exceptions.*;
import duke.exceptions.NumberFormatException;
import duke.ui.Ui;
import duke.parser.Parser;
import duke.tasklist.Tasklist;
import duke.command.Command;


public class Duke {

    private final Ui ui;
    private final Tasklist tasks;
    private final Storage storage;
    private final Parser parser;

    /**
     * Instantiates the classes required to run Duke.
     * tasks is being initialised to existing task list stored in a file
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        parser = new Parser();
        tasks = storage.loadTask();

    }

    /**
     * .start() to start the Duke program
     * Runs the program infinitely until isExit is true
     * Catches exceptions thrown from .execute() and call .showError() to display the error message
     * .end() to exit the Duke program
     */
    public void run() {
        ui.start();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = parser.parse(fullCommand);
                c.execute(tasks);
                isExit = c.isExit();
            } catch (MissingArgumentException e) {
                Ui.showError(e.getMessage());
            } catch (InvalidCommandException e) {
                Ui.showError(e.getMessage());
            } catch (NumberFormatException e) {
                Ui.showError(e.getMessage());
            } catch (MissingDateException e) {
                Ui.showError(e.getMessage());
            }
        }
        ui.end();
    }

    public static void main(String[] args) {
        new Duke().run();

    }
}
