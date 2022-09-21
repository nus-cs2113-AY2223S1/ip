package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initialises UI, Storage and TaskList
     */
    public Duke() {
        ui = new Ui();
        try {
            storage = new Storage();
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getErrorMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs Duke
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // divider line
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getErrorMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Runs an instance of Duke
     *
     * @param args
     */
    public static void main(String[] args) {
        new Duke().run();
    }

}
