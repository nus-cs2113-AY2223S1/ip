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
    public static void main (String[] args) throws IOException {
        new Duke().run();
    }
}
