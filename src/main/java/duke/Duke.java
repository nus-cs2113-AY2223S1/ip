package duke;

import duke.command.Command;
import duke.exception.FileCorruptedException;
import duke.exception.InvalidCommandException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.DukeUI;

import java.io.IOException;

public class Duke {

    private TaskList tasks;
    private DukeUI ui;
    private Storage storage;

    public Duke() {
        ui = new DukeUI();
        storage = new Storage();
        tasks = new TaskList();
    }

    public static void main(String[] args) {
        new Duke().startDuke();
    }

    /**
     * Retrieve and process user inputs depending on the commands
     */
    private void startDuke() {

        try {
            storage.loadDataFile(ui, tasks);
        } catch (IOException | FileCorruptedException e) {
            return;
        }

        ui.printGreetMessage();

        boolean isExit = false;
        while (!isExit) {
            String userInput = ui.getInput();
            try {
                Command command = Parser.extractCommand(userInput);
                command.execute(ui, storage, tasks);
                isExit = command.getExitStatus();
            } catch (InvalidCommandException e) {
                ui.printInvalidCommandMessage();
            }
        }
    }
}
