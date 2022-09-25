import consoleCommands.*;
import exception.TaskDoesNotExistException;
import exception.InvalidCommandException;
import exception.InvalidFileDataException;
import exception.InvalidArgumentsException;
import exception.NotEnoughArgumentsException;
import task.Task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This is the main class of the programme, where the programme starts and ends, and exceptions are being handled.
 * It is also the base of the programme, where other classes are called from here.
 */
public class Duke {
    public static final String filePath = "data/duke.txt";
    public static final String tempFilePath = "data/temp.txt";

    private Ui ui;
    private Storage storage;
    //private TaskList tasks;
    private Parser parser;
    private ArrayList<Task> taskList = new ArrayList<>();
    private Command c;
    /**
     * Duke reads in data from text file to fill taskList array
     * @throws InvalidFileDataException if the file contains data that is wrong or not formatted correctly
     * @throws IOException if filePath is invalid or file cannot be found
    */
    public Duke () {
        ui = new Ui();
        storage = new Storage();
        parser = new Parser();
        try {
            storage.readFromFile(filePath, taskList);
        } catch (InvalidFileDataException |
                 IOException e) {
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
    public void run () throws IOException {
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
                     InvalidArgumentsException e) {
                ui.showError(e.getMessage());
            }
        }
        try {
            storage.writeToFile(filePath,tempFilePath,taskList);
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        ui.end();
    }

    /**
     * @throws IOException if filePath is invalid or file cannot be found
     */
    public static void main (String[] args) throws IOException {
        new Duke().run();
    }
}
