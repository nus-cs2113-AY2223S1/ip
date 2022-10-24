package Duke.Parser;

import Duke.Exceptions.UnknownCommandException;
import Duke.Storage.Storage;
import Duke.Tasks.TaskManager;

import java.io.IOException;

/**
 * Parser class parses and processes the user inputs and implements the relevant objects.
 */
public class Parser {
    static boolean isFinished = true;

    /**
     * This function reads in and processes the user's input.
     *
     * @param command user input
     * @return User chooses to exit the program
     */
    public static boolean parseCommand(String command) {

        String[] splitCommand = command.split(" ");
        switch (splitCommand[0]) {
        case "bye":
            try {
                Storage.writeToFile();
            } catch (IOException e) {
                System.out.println("Error inputting data to file");
            }
            isFinished = false;
            return isFinished;
        case "mark":
            TaskManager.markTask(command);
            break;
        case "unmark":
            TaskManager.unmarkTask(command);
            break;
        case "list":
            TaskManager.printList();
            break;
        case "todo":
            TaskManager.addToDo(command);
            break;
        case "event":
            TaskManager.addEvent(command);
            break;
        case "deadline":
            TaskManager.addDeadline(command);
            break;
        case "delete":
            TaskManager.deleteTask(command);
            break;
        case "find":
            TaskManager.findTask(command);
            break;
        default:
            UnknownCommandDetection();
            break;
        }

        return isFinished;
    }

    /**
     * Throws an unknown command error if user input is not in a valid format.
     */
    private static void UnknownCommandDetection() {
        try {
            throw new UnknownCommandException();
        } catch (UnknownCommandException e) {
            e.UnknownCommandMessage();
        }
    }
}
