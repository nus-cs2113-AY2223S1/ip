import controller.Parser;
import controller.Ui;
import controller.TaskList;
import controller.Storage;

/**
 * Main Duke class
 */
public class Duke {

    private Storage storage;
    private TaskList list;
    private Ui ui;
    private Parser parser;
    private String filepath = "../../data/stored.txt";
    private String folderpath = "../../data/";

    // CONSTRUCTOR
    public Duke() {
        ui = new Ui();
        // create simply a new storage object
        storage = new Storage(filepath, folderpath);
        try {
            // load the existing tasks from the storage and input it for construction
            list = new TaskList(storage.getMakeFile());
        } catch (Exception e) {
            ui.showLoadingError();
            list = new TaskList();
        }
        parser = new Parser(ui, storage, list);
    }

    /**
    * Runs the main routine for the program.
    * Greets the user, loads previous data, and expects commands from user
    * until user decides to terminate program with 'bye'. Changes are then saved.
    */
    public void run() {
        ui.greetUser();
        ui.getContinuousInput(parser);
        ui.printExitGreeting();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
