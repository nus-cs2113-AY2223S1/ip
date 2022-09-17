package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UI;

/**
 * A chat bot named Duke.
 */
public class Duke {

    private final UI ui;
    private TaskList taskList;
    private final Storage storage;
    private static final String EXIT_PREFIX = "bye";
    private boolean isActive = true;

    /**
     * Constructor of Duke.
     *
     * @param filePath The file path that Duke stores its tasks.
     */
    public Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            taskList = new TaskList();
        }
    }

    /**
     * Exits the program.
     */
    public void exit() {
        isActive = false;
    }

    /**
     * Runs the program.
     */
    public void run() {
        ui.printGreetingMessage();
        while (isActive) {
            try {
                String input = ui.getUserInput();
                Command command = Parser.parseCommand(input);
                command.execute(taskList, ui);

                if (input.startsWith(EXIT_PREFIX)) {
                    storage.writeTasks(taskList);
                    exit();
                }
            } catch (DukeException e) {
                ui.printError(e.getMessage());
            }
        }
    }

    /**
     * Main entry method to run Duke.
     */
    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
