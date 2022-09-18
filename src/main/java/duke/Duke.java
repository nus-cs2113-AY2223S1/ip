package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * A task manager app that uses Command-Line Interface (CLI)
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    public Duke(String filePath) {
        ui  = new Ui();
        storage = new Storage(filePath);
        try {
            String fileContent = storage.loadFile();
            if(fileContent == "") {   //file is empty
                tasks = new TaskList();
            } else {                    //file has content
                tasks = new TaskList(fileContent);
            }
        } catch (DukeException | IOException e) {
            Ui.showLoadingError();
            tasks = new TaskList();     //file is corrupted so create new empty TaskList
        }
    }

    /**
     * Welcomes user and continuously reads in user input until user says "bye".
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.drawLine();
                Command c = new Command(Parser.parse(fullCommand));
                c.execute(tasks, ui, storage, fullCommand);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getErrorMessage());
            } catch (IOException e) {
                ui.showError(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                ui.showOutOfBoundsError();
            } finally {
                ui.drawLine();
            }
        }
        ui.showGoodbye();
    }

    /**
     * Starts the app and specifies the desired file location to save/load data.
     * @param args
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
