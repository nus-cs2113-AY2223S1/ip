import Exception.DataCorruptedException;
import Exception.EmptyArgumentException;
import Exception.WrongArgumentException;
import Tasks.TaskType;
import Tasks.TaskList;
import Ui.Ui;
import Storage.Storage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class Duke {
    private static TaskList list = new TaskList();
    private static boolean toSave = true;
    private static Ui ui;
    private static Storage storage;

    private static final String SEPARATOR = "____________________________________________________________";

    public Duke() {
        ui = new Ui();
    }

    public void run() {
        ui.printWelcomeMessage();
    }

    public static void main(String[] args) {
//        new Duke().run();

        ui = new Ui();
        storage = new Storage();
        ui.printWelcomeMessage();
        boolean isInitialiseSuccessful = true;

        try {
            int itemLen = storage.initialiseTaskFromFile(list);
            if (itemLen > 0) {
                ui.printToUser(list.getCompleteList());
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
            String lineInput = ui.getUserInput();
            String[] inputSplitted = lineInput.split(" ",2);

            if (inputSplitted[0].equals("bye")) {
                break;
            }

            switch (inputSplitted[0]) {
                case "list":
                    printOutput(list.getCompleteList());
                    break;
                case "mark":
                    try {
                        doMarkAction(inputSplitted[1]);
                    } catch (ArrayIndexOutOfBoundsException | EmptyArgumentException e) {
                        ui.printEmptyMarkUnmarkArgs();
                    } catch (WrongArgumentException e) {
                        ui.printInvalidTaskNumber();
                    }
                    break;
                case "unmark":
                    try {
                        doUnmarkAction(inputSplitted[1]);
                    } catch (ArrayIndexOutOfBoundsException | EmptyArgumentException e) {
                        ui.printEmptyMarkUnmarkArgs();
                    } catch (WrongArgumentException e) {
                        ui.printInvalidTaskNumber();
                    }
                    break;
                case "todo":
                    try {
                        doTodoAction(inputSplitted[1]);
                    } catch (ArrayIndexOutOfBoundsException | EmptyArgumentException e){
                        ui.printEmptyActionArgs();
                    }
                    break;
                case "deadline":
                    try {
                        doDeadlineAction(inputSplitted[1]);
                    } catch (ArrayIndexOutOfBoundsException | EmptyArgumentException e) {
                        ui.printEmptyActionArgs();
                    }
                    break;
                case "event":
                    try {
                        doEventAction(inputSplitted[1]);
                    } catch (ArrayIndexOutOfBoundsException | EmptyArgumentException e) {
                        ui.printEmptyActionArgs();
                    }
                    break;
                case "delete":
                    try {
                        deleteAction(inputSplitted[1]);
                    } catch (ArrayIndexOutOfBoundsException | EmptyArgumentException e) {
                        ui.printEmptyActionArgs();
                    } catch (WrongArgumentException e) {
                        ui.printInvalidTaskNumber();
                    }
                    break;
                default:
                    ui.printUnknownMessage();
                    break;
            }

        }
        ui.printExitMessage();
    }

    private static void printOutput(String message) {
        System.out.println(SEPARATOR);
        System.out.println(message);
        System.out.println(SEPARATOR);
        System.out.println("");
    }

    private static void printError(String message) {
        System.out.print("\u001b[31m"); // red font ANSI
        System.out.println(SEPARATOR);
        System.out.println(message);
        System.out.println(SEPARATOR);
        System.out.println("\u001b[0m"); // reset font ANSI
    }

    private static void doMarkAction(String lineInput)
            throws EmptyArgumentException, WrongArgumentException {
        if (lineInput.strip().isEmpty()) {
            throw new EmptyArgumentException();
        }
        int itemNumber = Integer.parseInt(lineInput);
        if (itemNumber > list.getTaskListSize()) {
            throw new WrongArgumentException();
        }

        list.markCompleted(itemNumber, true);
        String message = "Nice! I've marked this task as done:\n"
                + list.getItemFromList(itemNumber);
        printOutput(message);
        if (toSave) {
            try {
                list.updateWholeFile();
            } catch (IOException e) {
                printError("Something went wrong, error data: " + e.getMessage());
            }
        }
    }

    private static void doUnmarkAction(String lineInput)
            throws EmptyArgumentException, WrongArgumentException {
        if (lineInput.strip().isEmpty()) {
            throw new EmptyArgumentException();
        }
        int itemNumber = Integer.parseInt(lineInput);
        if (itemNumber > list.getTaskListSize()) {
            throw new WrongArgumentException();
        }

        list.markCompleted(itemNumber, false);
        String message = "OK, I've marked this task as not done yet:\n"
                + list.getItemFromList(itemNumber);
        printOutput(message);
        if (toSave) {
            try {
                list.updateWholeFile();
            } catch (IOException e) {
                printError("Something went wrong, error data: " + e.getMessage());
            }
        }
    }

    private static void doTodoAction(String lineInput)
            throws EmptyArgumentException {
        if (lineInput.strip().isEmpty()) {
            throw new EmptyArgumentException();
        }
        int index = list.addTaskToList(lineInput, TaskType.TODO, false, toSave);
        String output = "I got you, added a todo:\n"
                + list.getItemFromList(index + 1)
                + "\n Now you have " + (index + 1) + " tasks in the list.";
        printOutput(output);
    }

    private static void doDeadlineAction(String lineInput)
            throws EmptyArgumentException {
        if (lineInput.strip().isEmpty()) {
            throw new EmptyArgumentException();
        }
        int index = list.addTaskToList(lineInput, TaskType.DEADLINE, false, toSave);
        String output = "I got you, added a deadline:\n"
                + list.getItemFromList(index + 1)
                + "\n Now you have " + (index + 1) + " tasks in the list.";
        printOutput(output);
    }

    private static void doEventAction(String lineInput)
            throws EmptyArgumentException {
        if (lineInput.strip().isEmpty()) {
            throw new EmptyArgumentException();
        }
        int index = list.addTaskToList(lineInput, TaskType.EVENT, false, toSave);
        String output = "I got you, added a event:\n"
                + list.getItemFromList(index + 1)
                + "\n Now you have " + (index + 1) + " tasks in the list.";
        printOutput(output);
    }

    private static void deleteAction(String lineInput)
            throws EmptyArgumentException, WrongArgumentException {
        if (lineInput.strip().isEmpty()) {
            throw new EmptyArgumentException();
        }
        int index = Integer.parseInt(lineInput);
        if (index > list.getTaskListSize()) {
            throw new WrongArgumentException();
        }
        String description = "Noted. I've removed this task:\n";
        description += list.getItemFromList(index);
        list.deleteTaskFromList(index);
        description += "Now you have "
                + list.getTaskListSize()
                + " tasks in the list";
        printOutput(description);
        if (toSave) {
            try {
                list.updateWholeFile();
            } catch (IOException e) {
                printError("Something went wrong, error data: " + e.getMessage());
            }
        }
    }
}
