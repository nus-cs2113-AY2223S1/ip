package duke;

import java.io.FileNotFoundException;

/**
 * Entry point of the Duke application
 * Initialises the application and starts the interaction with the user.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Runs the program until termination.
     */
    public void run() {
        start();
        runProgramUntilExitCommand();
        exit();
    }

    private void start() {
        this.ui = new Ui();
        this.storage = new Storage();
        storage.createFile();
        ui.printWelcomeMessage();
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.printLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Reads the user command and executes it, until the user issues the exit command.
     */
    private void runProgramUntilExitCommand() {
        Command command;
        do {
            Parser parser = new Parser();
            String userCommandText = ui.getUserCommand();
            command = parser.parseCommand(userCommandText);
            tasks.runTaskCommand(command, userCommandText);
        } while (command != Command.EXIT);
    }

    /**
     * Prints the Goodbye message and exits the program.
     */
    private void exit() {
        ui.printGoodbyeMessage();
        System.exit(0);
    }


}
