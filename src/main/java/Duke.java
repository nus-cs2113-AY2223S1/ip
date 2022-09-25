import consoleCommands.*;
import exception.TaskDoesNotExistException;
import exception.InvalidCommandException;
import exception.InvalidFileDataException;
import exception.InvalidArgumentsException;
import exception.NotEnoughArgumentsException;
import task.Task;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

/*
 * End programme: bye
 * List tasks: List
 * Add todo: todo (name)
 * Add deadline: deadline (name) /by (time)
 * Add event: event (name) /at (time)
 * Mark/Unmark: mark/unmark (task number)
 * Delete: delete (task number)
 * Find: find (input text)
 */

public class Duke {
    public static final String TASK_DOES_NOT_EXIST_ERROR_MESSAGE = "Task does not exist. Please try again.";
    public static final String COMMAND_DOES_NOT_EXIST_ERROR_MESSAGE = "Invalid command. Please try again.";
    public static final String INVALID_ARGUMENTS_ERROR_MESSAGE = "Invalid arguments detected. Please try again.";
    public static final String NOT_ENOUGH_ARGUMENTS_ERROR_MESSAGE = "Not enough arguments entered. Please try again.";
    public static final String INVALID_FILE_ERROR_MESSAGE = "Invalid file data detected. File data will not be read.";
    public static final String filePath = "data/duke.txt";
    public static final String tempFilePath = "data/temp.txt";

    private Ui ui;
    private Storage storage;
    //private TaskList tasks;
    private Parser parser;
    private ArrayList<Task> taskList = new ArrayList<>();
    private Command c;
    public Duke () {
        ui = new Ui();
        storage = new Storage();
        parser = new Parser();
        try {
            storage.readFromFile(filePath, taskList);
        } catch (InvalidFileDataException e) {
            ui.showError(e.getMessage());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
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
    public static void main (String[] args) throws IOException, InvalidFileDataException {
        new Duke().run();
    }
}
