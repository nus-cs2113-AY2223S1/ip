package duke;


import duke.ui.TextUi;
import duke.parser.Parser;
import duke.data.tag.TaskList;
import java.lang.RuntimeException;
import duke.command.*;
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


    /** Run the program until termination */
    public void run() {
        start();
        runCommandLoopUntilExitCommand();
        exit();
    }

    /** Setup the required objects, loads the data from storage, print welcome message  */
    private void start() {
        this.ui = new TextUi();
        ui.showWelcomeMessage();
        this.storage = new StorageFile();
        try{
            this.taskList = storage.load();
            ui.showToUser(StorageFile.MESSAGE_LOADED);
        } catch (StorageException e) {
            ui.showToUser(e.getMessage());
            this.taskList = new TaskList();
        }
    }

    /** Print exit message and exits */
    private void exit() {
        ui.showExitMessage();
        ui.input.close();
        System.exit(0);
    }

    /** Read user command and execute until exit command */
    private void runCommandLoopUntilExitCommand() {
        Command command;
        do {
            String userCommandText = ui.getUserCommand();
            command = new Parser(taskList).parseCommand(userCommandText);
            CommandResult result = executeCommand(command);
            ui.showResultToUser(result);
            storage.save(taskList);
        } while (!ExitCommand.isExit(command));
    }



    /** Execute the command, save into the file, return the results */

    private CommandResult executeCommand(Command command) {
        try{
            command.setTaskList(taskList); // Give the taskList for the command to work ont
//            storage.save(taskList);
            return command.execute();
        } catch (Exception e){
            ui.showToUser(e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
