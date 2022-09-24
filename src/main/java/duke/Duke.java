package duke;

import duke.ui.Ui;
import duke.parser.Parser;
import duke.commands.Command;
import duke.commands.ByeCommand;

import java.io.FileNotFoundException;
import java.io.IOException;
import duke.exception.MissingListIndexException;
import duke.exception.EmptyTaskDescriptionException;
import duke.exception.MissingDeadlineDateTimeReferenceException;
import duke.exception.MissingEventDateTimeReferenceException;

public class Duke {
    private static Ui ui;
    private static Storage storage;
    private static TaskList taskList;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        taskList = new TaskList();
        initializeFile();
        try {
            taskList.setCurrentListSize(storage.loadDukeTextFile(taskList));
        } catch (FileNotFoundException e) {
            ui.showDukeTextFileNotFoundMessage();
        }
    }

    private void initializeFile() {
        //Attempt to find 'data' directory; Directory created if not found
        try {
            boolean hasCreatedDataFile = storage.findDataFile();
            if (hasCreatedDataFile) {
                ui.showDataFileCreationMessage();
            }
        } catch (SecurityException e) {
            ui.showSecurityExceptionMessage();
        }

        //Attempt to find 'duke.txt' text file; Text file created if not found
        try {
            boolean hasCreatedDukeFile = storage.findDukeTextFile();
            if (hasCreatedDukeFile) {
                ui.showDukeFileCreationMessage();
            }
        } catch (IOException e) {
            ui.showIOExceptionMessage();
        }
    }

    public void run() {
        start();
        runCommandLoopUntilByeCommand();
        exit();
    }

    private void start() {
        ui.showWelcomeMessage();
    }

    private void exit() {
        System.exit(0);
    }

    private void runCommandLoopUntilByeCommand() {
        Command command;
        boolean isBye = false;
        do {
            String userCommandText = ui.getUserCommand();
            try {
                command = new Parser().parseCommand(userCommandText);
                command.execute(taskList, ui, storage);
                isBye = (command instanceof ByeCommand);
            } catch (MissingListIndexException e) {
                ui.showMissingTaskIndexMessage();
            } catch (IndexOutOfBoundsException e) {
                ui.showIndexOutOfBoundsExceptionMessage();
            } catch (NumberFormatException e) {
                ui.showNumberFormatExceptionMessage();
            } catch (EmptyTaskDescriptionException e) {
                ui.showEmptyToDoOrDeadlineOrEventDescriptionExceptionMessage();
            } catch (MissingDeadlineDateTimeReferenceException e) {
                ui.showMissingDeadlineDateTimeReferenceExceptionMessage();
            } catch (MissingEventDateTimeReferenceException e) {
                ui.showMissingEventDateTimeReferenceExceptionMessage();
            }
        } while (!isBye);
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
