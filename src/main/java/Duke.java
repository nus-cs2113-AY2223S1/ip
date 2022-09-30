import Exception.DataCorruptedException;
import Exception.EmptyArgumentException;
import Exception.WrongArgumentException;
import Exception.InvalidCommandException;
import Tasks.TaskList;
import Ui.Ui;
import Storage.Storage;
import Parser.Parser;
import Parser.Command;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Represents the main class for the task chat bot Duke
 */
public class Duke {
    private static TaskList list = new TaskList();
    private static boolean toSave = true;
    private static Ui ui;
    private static Storage storage;

    /**
     * Entry point of the program.
     * Initialise the user interface and task list from file. It will continuously run until exit command is entered.
     * Saving feature will be default on unless the file is not found.
     * Program will exit immediately if user queried <code>exit</code> or have corrupted data file.
     * @param args Program execution arguments (not utilised).
     */
    public static void main(String[] args) {
        ui = new Ui();
        storage = new Storage();
        ui.printWelcomeMessage();
        boolean isInitialiseSuccessful = true;

        // Initialising local data storage and populate the list.
        try {
            int itemLen = storage.initialiseTaskFromFile(list);
            if (itemLen > 0) {
                ui.printAllTask(list);
            } else {
                storage.createNewFile();
            }
        } catch (FileNotFoundException e) {
            ui.printFileNotFound();
            toSave = false;
        } catch (DataCorruptedException e) {
            ui.printFileCorrupted();
            isInitialiseSuccessful = false;
        } catch (IOException e) {
            ui.printError("Something wrong happen, error data: " + e.getMessage());
            isInitialiseSuccessful = false;
        }

        if (isInitialiseSuccessful) {
            runLoopUntilExit();
        }

        ui.printExitMessage();
    }

    /**
     * Run the user input read in, parsing, executing and saving process in a loop.
     * Will exit the loop when user entered <code>exit</code>.
     */
    public static void runLoopUntilExit() {
        while (true) {
            String userInput = ui.getUserInput();
            try {
                Parser parser = new Parser();
                parser.parseCommand(userInput);
                if (parser.getCommand() == Command.EXIT) {
                    return;
                }
                if (parser.getCommand() == Command.LIST) {
                    ui.printAllTask(list);
                } else {
                    String item = list.executeCommand(parser.getCommand(), parser.getUserArgs());
                    ui.showResult(item, parser.getCommand(), list.getTaskListSize());
                    if (toSave) {
                        storage.updateWholeFile(list);
                    }
                }
            } catch (ArrayIndexOutOfBoundsException | EmptyArgumentException e) {
                ui.printEmptyActionArgs();
            } catch (WrongArgumentException e) {
                ui.printInvalidTaskNumber();
            } catch (InvalidCommandException e) {
                ui.printUnknownMessage();
            } catch (IOException e) {
                ui.printError("Something went wrong, error data: " + e.getMessage());
            }
        }
    }
}
