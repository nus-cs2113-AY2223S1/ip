package duke;

import duke.command.Command;
import duke.data.TaskList;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.ui.Ui;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.initialize());
        } catch (DukeException exception) {
            ui.displayErrorMessage(exception.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.displayGreetingMessage();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException exception) {
                ui.displayErrorMessage(exception.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }
}
