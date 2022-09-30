package duke;


import duke.data.Storage;
import duke.exceptions.*;
import duke.exceptions.NumberFormatException;
import duke.ui.Ui;
import duke.parser.Parser;
import duke.tasklist.Tasklist;
import duke.command.Command;

import java.io.IOException;

import java.util.Scanner;
import java.util.ArrayList;
public class Duke {

    private final Ui ui;
    private final Tasklist tasks;
    private final Storage storage;
    private final Parser parser;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        parser = new Parser();
        tasks = storage.loadTask();

    }

    public void run() {
        ui.start();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (MissingArgumentException e) {
                Ui.showError(e.getMessage());
            } catch (InvalidCommandException e) {
                Ui.showError(e.getMessage());
            } catch (NumberFormatException e) {
                Ui.showError(e.getMessage());
            } catch (MissingDateException e) {
                Ui.showError(e.getMessage());
            }
        }
        ui.end();
    }



    public static void main(String[] args) {
        new Duke().run();

    }
}
