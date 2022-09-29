package duke;

import static duke.common.Constants.FILE_NAME;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;


/**
 * The main Duke class.
 */
public class Duke {

    private final Ui ui;
    private final TaskList tasks;
    private final Storage storage;
    private final Parser parser;


    /**
     * Instantiates the classes required to run Duke.
     *
     * @param filename Relative filepath to load the Duke note database from.
     */
    public Duke(String filename) {
        ui = new Ui();
        ui.printWelcomeMessage();
        storage = new Storage(filename);
        tasks = storage.createOrLoad();
        parser = new Parser(ui, storage, tasks);
    }

    /**
     * Enters the command processing loop.
     */
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
