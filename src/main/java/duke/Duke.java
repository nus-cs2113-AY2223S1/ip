package duke;


import duke.ui.TextUi;
import duke.parser.Parser;
import duke.data.TaskList;
import duke.command.*;
import duke.data.*;

/**
 * Entry point of the Duke application
 * Initializes the application and starts the interaction with the users
 */
public class Duke {
    private static final String DUKE_VERSION = "v1.0";
    private TextUi ui;
    private TaskList taskList = new TaskList();
    private Storage storage = new Storage(taskList);


    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        start();

        runMainLoop();
        exit();
    }

    private void start() {
        this.ui = new TextUi();
        ui.showToUserLn(storage.load());
        ui.showWelcomeMessage();
    }

    private void exit() {
        ui.showExitMessage();
        ui.input.close();
        System.exit(0);
    }

    private void runMainLoop() {
        Command command;
        do {
            String userCommandText = ui.getUserCommand();
            command = new Parser().parseCommand(userCommandText);
            CommandResult result = executeCommand(command);
            ui.showResultToUser(result);
            ui.showToUserLn(storage.save());
        } while (!ExitCommand.isExit(command));
    }

    private CommandResult executeCommand(Command command) {
        return command.execute();
    }

}
