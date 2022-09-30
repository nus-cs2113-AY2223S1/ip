package duke;

import duke.exception.DukeException;

import duke.util.*;
import duke.command.Command;

import java.util.ArrayList;
import java.util.List;

public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList();
        Parser.init();

        loadPastSession();
    }

    public static void main(String[] args) {
        new Duke("data/userData.txt").run();
    }

    /**
     * To run the Duke Application
     */
    public void run() {
        ui.greetUser();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
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

    /**
     * To load the command from data file
     */
    private void loadPastSession(){
        List<String> pastData = new ArrayList<>();

        try {
            storage.loadDataFromFile();
            pastData = storage.getData();
        } catch (DukeException e) {
            ui.displayMessage(e.getErrorMessage());
        }

        for (String pastCommand: pastData) {
            try {
                Command c = Parser.parse(pastCommand);
                c.execute(taskList, ui, storage);
            } catch (DukeException e) {
                ui.displayMessage(e.getErrorMessage());
            }
        }

        storage.setLoadStatus(true);
    }

    /**
     * To clean up the application and record the existing tasks in the task list onto data file
     */
    private void endProgram() {
        ui.endMessage();

        try {
            storage.writeData(TaskList.serialize());
        } catch (DukeException e) {
            ui.displayMessage(e.getErrorMessage());
        }

        closeUtil();
    }

    private void closeUtil() {
        ui.close();
        taskList.close();
        Parser.close();
    }

}
