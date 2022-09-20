package duke;


import duke.ui.TextUi;
import duke.parser.Parser;
import duke.data.tag.TaskList;
import java.lang.RuntimeException;
import duke.data.task.Task;
import duke.command.*;
import duke.data.*;
import duke.storage.StorageFile;

/**
 * Entry point of the Duke application
 * Initializes the application and starts the interaction with the users
 */
public class Duke {
    private TextUi ui;
    private StorageFile storage; //TODO: Implement storage
    private TaskList<Task> taskList;

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
        this.storage = new StorageFile();
        this.taskList = new TaskList<>();
        ui.showWelcomeMessage();
        ui.showToUser(storage.load());
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
            command = new Parser().parseCommand(userCommandText);
            CommandResult result = executeCommand(command);
            ui.showResultToUser(result);

        } while (!ExitCommand.isExit(command));
    }



    /** Execute the command, save into the file, return the results */

    private CommandResult executeCommand(Command command) {
        try{
            command.setTaskList(taskList); // Give the taskList for the command to work ont
            Storage.save();
            return command.execute();
        } catch (Exception e){
            ui.showToUser(e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
