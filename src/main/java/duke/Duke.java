package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

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
            } finally {
                ui.drawLine();
            }
        }
        ui.showGoodbye();
    }
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
