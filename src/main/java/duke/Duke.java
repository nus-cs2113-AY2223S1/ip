package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;

public class Duke {

    private static final String filePath = "data/duke.txt";
    private static final String fileFolder = "data";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initialises UI, Storage and TaskList
     *
     * @param fileFolder path to the file's folder
     * @param filePath path to the data source
     */
    public Duke(String fileFolder, String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(fileFolder, filePath);
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getErrorMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs Duke
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // divider line
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getErrorMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Runs an instance of Duke
     *
     * @param args
     */
    public static void main(String[] args) {
        new Duke(fileFolder,filePath).run();
    }

}
