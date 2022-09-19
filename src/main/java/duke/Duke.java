package duke;

import duke.command.Command;

/**
 * Duke is the main class in the duke application.
 */
public class Duke {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * Sets up the application state.
     */
    public Duke() {
        taskList = new TaskList();
        ui = new Ui();
        storage = new Storage();
    }

    /**
     * Runs the application.
     */
    public void run() {
        boolean isExit = false;
        ui.initialize();
        TaskList lastResults = taskList;
        while (!isExit) {
            String input = ui.getInput().trim();
            try {
                Command command = Parser.parseCommand(input);
                if (command.isExit()) {
                    isExit = true;
                }
                lastResults = command.execute(taskList, ui, storage, lastResults);
            } catch (DukeException e) {
                ui.displayMessage(e.getMessage());
            }
        }
        ui.cleanUp();
    }

    /**
     * Main function.
     * 
     * @param args No command line arguments are expected.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
