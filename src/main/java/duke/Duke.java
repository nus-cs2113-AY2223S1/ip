package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.FileNotFoundException;

public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readFile());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.printGreeting();
        boolean isRun = true;
        while (isRun) {
            String line = ui.readCommand();
            Parser parser = new Parser(tasks, line, ui, storage);
            isRun = parser.handleCommand();
        }
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
