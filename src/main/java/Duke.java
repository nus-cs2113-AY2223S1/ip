import duke.Parser;
import duke.exception.AbsentArgsFlagException;
import duke.exception.DukeException;
import duke.exception.IllegalArgsNumException;
import duke.exception.IllegalArgsTypeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.task.TaskList;

import duke.Storage;
import duke.Ui;

import java.util.ArrayList;
import java.util.Scanner;


public class Duke {

    static private Ui ui;
    static private Storage storage;
    static private TaskList taskList;

    public static void main(String[] args) {
        new Duke().run(args);
    }

    public void run(String[] args) {
        start(args);
        runCmdLoopTillExit();
        exit();
    }

    private void start(String[] launchArgs) {
        this.ui = new Ui();
        this.storage = initStorage(launchArgs);
        this.taskList = storage.loadTask();
        ui.showLogo();
        ui.showWelcomeMsg();
    }

    private static void exit() {
        ui.showGoodbyeMsg();
        System.exit(0);
    }

    private void runCmdLoopTillExit() {
        boolean isExit = false;
        do {
            try {
                isExit = Parser.readUserCmd();
            } catch (DukeException e) {
                ui.showError(e);
            } finally {
                ui.showSeparator();
            }
        } while (!isExit);
    }

    private Storage initStorage(String[] launchArgs) {
        boolean isStorageFileSpecifiedByUser = launchArgs.length > 0;
        return isStorageFileSpecifiedByUser ? new Storage(launchArgs[0]) : new Storage();
    }

}
