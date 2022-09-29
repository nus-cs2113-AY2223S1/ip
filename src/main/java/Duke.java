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


public class Duke {
    private static TaskList list = new TaskList();
    private static boolean toSave = true;
    private static Ui ui;
    private static Storage storage;

    private static final String SEPARATOR = "____________________________________________________________";

    public void run() {
        ui.printWelcomeMessage();
    }

    public static void main(String[] args) {
        ui = new Ui();
        storage = new Storage();
        ui.printWelcomeMessage();
        boolean isInitialiseSuccessful = true;

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


        while (isInitialiseSuccessful) {
            String userInput = ui.getUserInput();

            try {
                Parser parser = new Parser();
                parser.parseCommand(userInput);
                if (parser.getCommand() == Command.EXIT) {
                    break;
                }
                if (parser.getCommand() == Command.LIST) {
                    ui.printAllTask(list);
                } else {
                    String item = list.executeCommand(parser.getCommand(), parser.getUserArgs());
                    ui.showResult(item, parser.getCommand(), list.getTaskListSize());
                    if (toSave){
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
        ui.printExitMessage();
    }
}
