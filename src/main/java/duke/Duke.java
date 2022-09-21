package duke;

import java.lang.RuntimeException;

import duke.ui.TextUi;
import duke.parser.Parser;
import duke.data.tag.TaskList;
import duke.command.Command;
import duke.command.CommandResult;
import duke.command.ExitCommand;
import duke.storage.StorageFile;
import duke.storage.StorageFile.StorageException;

/**
 * Entry point of the Duke application
 * Initializes the application and starts the interaction with the users
 */
public class Duke {
    private TextUi ui;
    private StorageFile storage;
    private TaskList taskList;

    public static void main(String[] args) {
        new Duke().run();
    }


    /**
     * Run the program until termination
     */
    private void run() {
        start();
        runCommandLoopUntilExitCommand();
        exit();
    }

    /**
     * Setup the required objects, loads the data from storage, print welcome message
     */
    private void start() {
        this.ui = new TextUi();
        ui.showWelcomeMessage();
        this.storage = new StorageFile();
        try {
            this.taskList = storage.load();
            ui.showToUser(StorageFile.MESSAGE_LOADED);
        } catch (StorageException e) {
            ui.showToUser(e.getMessage());
            this.taskList = new TaskList();
        }
    }

    /**
     * Print exit message and exits
     */
    private void exit() {
        ui.showExitMessage();
        ui.input.close();
        System.exit(0);
    }

    /**
     * Read user command and execute until exit command
     */
    private void runCommandLoopUntilExitCommand() {
        Command command;
        do {
            String userCommandText = ui.getUserCommand();
            command = new Parser(taskList).parseCommand(userCommandText);
            CommandResult result = executeCommand(command);
            ui.showResultToUser(result);
            save();
        } while (!ExitCommand.isExit(command));
    }

    /**
     * Save and catch error
     */
    private void save() {
        try {
            storage.save(taskList);
        } catch (StorageException e) {
            ui.showToUser(e.getMessage());
        }
    }


    /**
     * Execute the command, save into the file, return the results
     */

    private CommandResult executeCommand(Command command) {
        try {
            command.setTaskList(taskList); // pass the main taskList instance for the command to work on
            return command.execute();
        } catch (Exception e) {
            ui.showToUser(e.getMessage());
            throw new RuntimeException(e);
        }
    }


}
