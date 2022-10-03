package duke;

/**
 * The Duke program implements a task management application.
 * The core features include adding, deleting, viewing, marking and unmarking tasks
 * as well as finding tasks according to keywords. The tasks can either be a ToDo, Deadline or an Event task.
 *
 * @author  shengiv
 * @version 0.2
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    private static final String DUKE_DATA_FILE_PATH = "data/duke.txt";
    private static final String EXIT_COMMAND = "bye";

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTasks(), ui);
        parser = new Parser(ui, tasks);
    }

    /**
     * Allows the programme to run till exit command is received. Leads to termination of Duke
     * once exit command is received.
     */
    public void run() {
        ui.showWelcome();
        String lineInput;
        lineInput = ui.nextInputLine();
        while (!lineInput.equalsIgnoreCase(EXIT_COMMAND)) {
            parser.parse(lineInput);
            storage.saveTasksData(tasks.getTaskList());
            lineInput = ui.nextInputLine();
        }
        ui.showExit();
    }
    public static void main(String[] args) {
        new Duke(DUKE_DATA_FILE_PATH).run();
    }
}
