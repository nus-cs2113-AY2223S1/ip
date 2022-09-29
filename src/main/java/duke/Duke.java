package duke;

import duke.exception.DukeException;
import duke.exception.UnknownCommandException;

import duke.util.*;
import duke.util.asset.*;
import duke.command.Command;


import java.util.List;

public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskManager taskList;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskManager();
        InputParser.init();

        loadPastSession();
    }

    public static void main(String[] args) {
        new Duke("data/userData.txt").run();
    }

    public void run() {
        ui.greetUser();
        boolean isExit = false;

        while (!isExit) {
            String command = ui.readCommand();

            try {
                Command c = InputParser.parse(command);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.displayMessage(e.getErrorMessage());
            } finally {
                ui.showLine();
            }
        }

        endProgram();
    }

    private void loadPastSession(){
        storage.loadData();
        List<String> pastData = storage.getData();

        for(String userInput: pastData) {
            try {
                Command c = InputParser.parse(userInput);
                c.execute(taskList, ui, storage);
            } catch (DukeException e) {
                ui.displayMessage( e.getErrorMessage() );
            }
        }

        storage.setLoadStatus(true);
    }

    private void endProgram() {
        ui.endMessage();

        try {
            storage.writeData(TaskManager.serialize());
        } catch (DukeException e) {
            ui.displayMessage(e.getErrorMessage());
        }

        closeUtil();
    }

    private void closeUtil() {
        ui.close();
        taskList.close();
        InputParser.close();
    }

}
