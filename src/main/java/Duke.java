
import consoleCommands.Command;
import consoleCommands.Parser;
import consoleCommands.Storage;
import consoleCommands.Ui;
import exception.NotEnoughArgumentsException;
import exception.InvalidFileDataException;
import exception.TaskDoesNotExistException;
import exception.DateParseException;
import exception.InvalidCommandException;
import exception.InvalidArgumentsException;
import task.Task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This is the main class of the programme, where the programme starts and ends, and exceptions are being handled.
 * It is also the base of the programme, where other classes are called from here.
 */
public class Duke {
    private static final String userDirectory = System.getProperty("user.dir");
    private static final java.nio.file.Path filePath = java.nio.file.Paths.get(userDirectory, "data", "duke.txt");
    private static final java.nio.file.Path tempFilePath = java.nio.file.Paths.get(userDirectory, "data", "temp.txt");
    private Ui ui;
    private Storage storage;
    private Parser parser;
    private ArrayList<Task> taskList = new ArrayList<>();
    private Command c;
    /**
     * Duke reads in data from text file to fill taskList array
     * @throws InvalidFileDataException if the file contains data that is wrong or not formatted correctly
     * @throws IOException if filePath is invalid or file cannot be found
    */
    public Duke() throws InvalidFileDataException, IOException {
        ui = new Ui();
        storage = new Storage();
        parser = new Parser();
        try {
            storage.readFromFile(filePath, taskList);
        } catch (InvalidFileDataException |
                 IOException |
                 DateParseException e) {
            ui.showError(e.getMessage());
        }
    }
    /**
     * Calls .start() to send introduction message
     * Runs the programme in a while-loop, until isExit is true
     * Catches the exception from .execute() and calls ui.showError() to print error message
     * After exiting while-loop, calls .writeToFile() to write data from taskList to text file
     * Calls .end() to send goodbye message and end programme
     * @throws IOException if filePath is invalid or file cannot be found
     */
    public void run() throws IOException {
        ui.start();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                c = parser.Parse(fullCommand);
                c.execute(taskList);
                isExit = c.isExit();
            } catch (NotEnoughArgumentsException |
                     TaskDoesNotExistException |
                     InvalidCommandException |
                     InvalidArgumentsException |
                     DateParseException e) {
                ui.showError(e.getMessage());
            }
        }
        try {
            storage.writeToFile(filePath, tempFilePath, taskList);
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        ui.end();
    }

    /**
     * @throws IOException if filePath is invalid or file cannot be found
     */
    public static void main(String[] args) throws IOException, InvalidFileDataException {
        new Duke().run();
    }
}
