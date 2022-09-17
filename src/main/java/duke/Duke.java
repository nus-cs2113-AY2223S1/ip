package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UI;

public class Duke {

    private final UI ui;
    private TaskList taskList;
    private final Storage storage;
    private static final String EXIT_PREFIX = "bye";
    private boolean isActive = true;

    public Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            taskList = new TaskList();
        }
    }

    public void exit() {
        isActive = false;
    }

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

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
