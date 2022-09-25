package duke;

import static duke.common.Constants.FILE_NAME;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class Duke {

    private final Ui ui;
    private final TaskList tasks;
    private final Storage storage;
    private final Parser parser;


    public Duke(String filename) {
        ui = new Ui();
        ui.printWelcomeMessage();
        storage = new Storage(filename);
        tasks = storage.createOrLoad();
        parser = new Parser(ui, storage, tasks);
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        while (true) {
            parser.runCommand(ui.getNextCommand());
        }
    }

    public static void main(String[] args) {
        new Duke(FILE_NAME).run();
    }
}
