package DukePackage;

import CommandPackage.Command;
import ExceptionsPackage.DukeException;
import StoragePackage.Storage;
import TaskPackage.TaskList;
import UiPackage.Ui;
import ParserPackage.Parser;

/*
A Command-Line interface task manager app.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /*
    Initialisation of Ui, Storage and TaskList is done here
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = storage.retrieveTasks();
    }
/*
Running of commands is done here
 */
    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        String home = System.getProperty("user.home");
        String path = home + "/Desktop/tasks.txt";
        new Duke(path).run();
    }
}



